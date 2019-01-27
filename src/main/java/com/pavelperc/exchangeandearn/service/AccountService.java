package com.pavelperc.exchangeandearn.service;


import com.pavelperc.exchangeandearn.model.Account;
import com.pavelperc.exchangeandearn.model.Exchange;
import com.pavelperc.exchangeandearn.model.Rate;
import com.pavelperc.exchangeandearn.repo.AccountRepo;
import com.pavelperc.exchangeandearn.repo.CurrencyRepo;
import com.pavelperc.exchangeandearn.repo.ExchangeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Nullable;
import javax.transaction.Transactional;
import javax.validation.constraints.Min;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AccountService {
    
    @Autowired
    public ExchangeRepo exchangeRepo;
    
    @Autowired
    public AccountRepo accountRepo;
    
    @Autowired
    public CurrencyRepo currencyRepo;
    
    
    public @Nullable
    Double getProfit(Account rubAcc, Account foreignAcc, Rate rateForeign, double sumOfExchange/*in foreign*/) {
        
        if (sumOfExchange < 0) { // toRubles
            double diffInRubles = 0;
            
            // sorted by foreign rate ascending
            List<Exchange> history = 
                    exchangeRepo.findAllByAccForeign_Id(foreignAcc.getId())
                    .stream()
                    .sorted(Comparator.comparingDouble(o -> o.getRateForeign().getSell()))
                    .collect(Collectors.toList());
            
            if (history.isEmpty())
                return null;
            
            double ourSum = -sumOfExchange;
            
            for (Exchange exchange : history) {
                if (ourSum == 0)
                    break;
                if (exchange.getAddedMoney() <= ourSum) {
                    diffInRubles += (rateForeign.getSell() - exchange.getRateForeign().getSell()) * exchange.getAddedMoney();
                    
                    ourSum -= exchange.getAddedMoney();
                    exchange.setUsefulForPrediction(false);
                } else {
                    diffInRubles += ourSum * rateForeign.getSell() - ourSum * exchange.getRateForeign().getSell();
                    ourSum = 0;
                    exchange.setAddedMoney(exchange.getAddedMoney() - ourSum);
                    break;
                }
            }
            return diffInRubles / -sumOfExchange;
        } else { // to Foreign
    
            double diffInForeign = 0;
    
            // sorted by foreign rate descending
            List<Exchange> history =
                    exchangeRepo.findAllByAccForeign_Id(foreignAcc.getId())
                            .stream()
                            .sorted((o1, o2) -> Double.compare(o2.getRateForeign().getSell(), o1.getRateForeign().getSell()))
                            .collect(Collectors.toList());
    
            if (history.isEmpty())
                return null;
    
            double ourSum = -sumOfExchange;
    
            for (Exchange exchange : history) {
                if (ourSum == 0)
                    break;
                if (exchange.getAddedMoney() <= ourSum) {
                    diffInForeign += (- rateForeign.getSell() + exchange.getRateForeign().getSell()) * exchange.getAddedMoney();
            
                    ourSum -= exchange.getAddedMoney();
                    exchange.setUsefulForPrediction(false);
                } else {
                    diffInForeign += - ourSum * rateForeign.getSell() + ourSum * exchange.getRateForeign().getSell();
                    ourSum = 0;
                    exchange.setAddedMoney(exchange.getAddedMoney() - ourSum);
                    break;
                }
            }
            return diffInForeign / sumOfExchange;
        }
        
        
    }
    
    
    
    @Transactional
    public void exchange(Long accRubId,
                         Long accForeignId,
                         // in foreign
                         double initialMoney,
                         LocalDateTime time,
                         Rate rateForeign
    ) {
        
        Account accRub = accountRepo.findById(accRubId).get();
        Account accForeign = accountRepo.findById(accForeignId).get();
        
        // if initialMoney is positive, then we add to foreign account
        
        accRub.takeMoney(initialMoney * rateForeign.getSell());
        
        accForeign.putMoney(initialMoney);
        
//        Double profit = getProfit();
        Double profit = getProfit(accRub, accForeign, rateForeign, initialMoney);
        
        if (profit == null)
            profit = 0.0;
        
        Exchange exchange = new Exchange(accForeign, rateForeign, time, initialMoney, true, profit);
        
        exchangeRepo.save(exchange);
        accountRepo.save(accRub);
        accountRepo.save(accForeign);
        
        
    }
}
