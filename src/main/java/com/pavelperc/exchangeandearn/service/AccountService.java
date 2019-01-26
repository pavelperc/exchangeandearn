package com.pavelperc.exchangeandearn.service;


import com.pavelperc.exchangeandearn.model.Account;
import com.pavelperc.exchangeandearn.model.Exchange;
import com.pavelperc.exchangeandearn.model.Rate;
import com.pavelperc.exchangeandearn.repo.AccountRepo;
import com.pavelperc.exchangeandearn.repo.CurrencyRepo;
import com.pavelperc.exchangeandearn.repo.ExchangeRepo;
import lombok.var;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import javax.validation.constraints.Min;
import java.time.LocalDateTime;

@Service
public class AccountService {
    
    @Autowired
    public ExchangeRepo exchangeRepo;
    
    @Autowired
    public AccountRepo accountRepo;
    
    @Autowired
    public CurrencyRepo currencyRepo;
    
    
    @Transactional
    public void exchange(Long accFromiD,
                         Long accToId,
                         @Min(value = 0L, message = "The value must be positive") double initialMoney,
                         LocalDateTime time,
                         Rate rateFrom,
                         Rate rateTo
    ) {
        
        Account accFrom = accountRepo.findById(accFromiD).get();
        Account accTo = accountRepo.findById(accToId).get();
//        
//        Rate rateFrom = new Rate(1, 100); // TODO get currency
//        
//        Rate rateTo = new Rate(100, 100);
        
        accFrom.takeMoney(initialMoney);
        
        // money in initial currency 
        double money = initialMoney;
        
        // money in rubles
        money *= rateFrom.getSell();
        
        // money in final currency
        money /= rateTo.getBuy();
        
        accTo.putMoney(money);
        
        Exchange exchange = new Exchange(accFrom, accTo, rateFrom, rateTo, time, initialMoney, true);
        
        exchange = exchangeRepo.save(exchange);
        accFrom = accountRepo.save(accFrom);
        accTo = accountRepo.save(accTo);
        
        
    }
}
