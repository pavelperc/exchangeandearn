package com.pavelperc.exchangeandearn;


import com.google.common.collect.ImmutableSet;
import com.pavelperc.exchangeandearn.model.Account;
import com.pavelperc.exchangeandearn.model.Currency;
import com.pavelperc.exchangeandearn.model.Role;
import com.pavelperc.exchangeandearn.model.User;
import com.pavelperc.exchangeandearn.repo.AccountRepo;
import com.pavelperc.exchangeandearn.repo.CurrencyRepo;
import com.pavelperc.exchangeandearn.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

import static com.pavelperc.exchangeandearn.model.Role.ADMIN;
import static com.pavelperc.exchangeandearn.model.Role.USER;

/** Loads the initial data to database.*/
@Component
public class DataLoader implements ApplicationRunner {
    @Autowired
    UserRepo userRepo;
    
    @Autowired
    CurrencyRepo currencyRepo;
    
    @Autowired
    AccountRepo accountRepo;
    
    
    
    @Transactional // FIXES something..
    @Override
    public void run(ApplicationArguments args) throws Exception {
        // SETUP H2 DATABASE TO START A SERVER!!!!!!!! (for debug with intellij idea)
        org.h2.tools.Server.createTcpServer().start();
        
        
        User user1 = new User("user", "password", ImmutableSet.of(Role.USER));
        User user2 = new User("admin", "admin", ImmutableSet.of(USER, ADMIN));
        
        user1 = userRepo.save(user1);
        user2 = userRepo.save(user2);
        
        Currency rub = new Currency("RUB");
        Currency eur = new Currency("EUR");
        Currency usd = new Currency("USD");
        currencyRepo.saveAll(Arrays.asList(rub, eur, usd));
        
        
        Account rubAcc = new Account(user1, "123", rub);
        Account eurAcc = new Account(user1, "456", eur);
        Account usdAcc = new Account(user1, "789", usd);
        
        rubAcc = accountRepo.save(rubAcc);
        eurAcc = accountRepo.save(eurAcc);
        usdAcc = accountRepo.save(usdAcc);
        
        
        
    }
}
