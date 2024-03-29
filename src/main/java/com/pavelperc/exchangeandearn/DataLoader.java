package com.pavelperc.exchangeandearn;


import com.google.common.collect.ImmutableSet;
import com.pavelperc.exchangeandearn.model.*;
import com.pavelperc.exchangeandearn.model.Currency;
import com.pavelperc.exchangeandearn.repo.AccountRepo;
import com.pavelperc.exchangeandearn.repo.CurrencyRepo;
import com.pavelperc.exchangeandearn.repo.RateRepo;
import com.pavelperc.exchangeandearn.repo.UserRepo;
import com.pavelperc.exchangeandearn.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Array;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.*;

import static com.pavelperc.exchangeandearn.model.Role.ADMIN;
import static com.pavelperc.exchangeandearn.model.Role.USER;

/**
 * Loads the initial data to database.
 */
@Component
public class DataLoader implements ApplicationRunner {
    @Autowired
    UserRepo userRepo;
    @Autowired
    CurrencyRepo currencyRepo;
    @Autowired
    AccountRepo accountRepo;
    @Autowired
    RateRepo rateRepo;
    
    @Autowired
    AccountService accountService;
    
    @Transactional // FIXES something..
    @Override
    public void run(ApplicationArguments args) throws Exception {
        // SETUP H2 DATABASE TO START A SERVER!!!!!!!! (for debug with intellij idea)
        org.h2.tools.Server.createTcpServer().start();
        
        
        User user1 = new User("u", "p", ImmutableSet.of(Role.USER));
        User user2 = new User("a", "a", ImmutableSet.of(USER, ADMIN));
        
        user1 = userRepo.save(user1);
        user2 = userRepo.save(user2);
        
        Currency rub = new Currency("RUB", "0");
        Currency eur = new Currency("EUR", "R01239");
        Currency usd = new Currency("USD", "R01235");
        rub = currencyRepo.save(rub);
        eur = currencyRepo.save(eur);
        usd = currencyRepo.save(usd);

//        currencyRepo.saveAll(Arrays.asList(rub, eur, usd));

//        if (true) return;
        
        Account rubAcc = new Account(user1, "123", rub, 2000);
        Account eurAcc = new Account(user1, "456", eur, 0);
        Account usdAcc = new Account(user1, "789", usd, 0);
        
        rubAcc = accountRepo.save(rubAcc);
        eurAcc = accountRepo.save(eurAcc);
        usdAcc = accountRepo.save(usdAcc);
        
        // TODO add rates in param, because the time is fake!!
        
        LocalDateTime[] times = {
                LocalDateTime.of(2019, Month.JANUARY, 20, 0, 0, 0),
                LocalDateTime.of(2019, Month.JANUARY, 21, 0, 0, 0),
                LocalDateTime.of(2019, Month.JANUARY, 22, 0, 0, 0),
                LocalDateTime.of(2019, Month.JANUARY, 23, 0, 0, 0),
                LocalDateTime.of(2019, Month.JANUARY, 24, 0, 0, 0),
                LocalDateTime.of(2019, Month.JANUARY, 25, 0, 0, 0)
        };
        accountService.exchange(rubAcc.getId(), eurAcc.getId(), 30/*eur*/, times[1], new Rate(10));
        
        accountService.exchange(rubAcc.getId(), rubAcc.getId(), -10/*eur*/, times[2], new Rate(20));
        accountService.exchange(eurAcc.getId(), rubAcc.getId(), -10/*eur*/, times[3], new Rate(30));
        accountService.exchange(eurAcc.getId(), rubAcc.getId(), -10/*eur*/, times[4], new Rate(40));
        
    }
}
