package com.pavelperc.exchangeandearn.service;


import com.pavelperc.exchangeandearn.model.Account;
import com.pavelperc.exchangeandearn.model.Rate;
import com.pavelperc.exchangeandearn.repo.ExchangeRepo;
import lombok.var;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class AccountService {
    
    @Autowired
    private ExchangeRepo exchangeRepo;
    
    // TODO block accounts some way...
    @Transactional
    public void exchange(Account accFrom, Account accTo, Rate rateFrom, Rate rateTo, double initialMoney) {
        
        accFrom.takeMoney(initialMoney);
        
       // money in initial currency 
        double money = initialMoney;
        
        // money in rubles
        money *= rateFrom.getSell();
        
        // money in final currency
        money /= rateTo.getBuy();
        
        accTo.putMoney(money);
        
        // TODO complete
    }
}
