package com.pavelperc.exchangeandearn;


import com.google.common.collect.ImmutableSet;
import com.pavelperc.exchangeandearn.model.*;
import com.pavelperc.exchangeandearn.model.Currency;
import com.pavelperc.exchangeandearn.repo.AccountRepo;
import com.pavelperc.exchangeandearn.repo.CurrencyRepo;
import com.pavelperc.exchangeandearn.repo.RateRepo;
import com.pavelperc.exchangeandearn.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.Month;
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
    @Autowired
    RateRepo rateRepo;
    
    
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
        rub = currencyRepo.save(rub);
        eur = currencyRepo.save(eur);
        usd = currencyRepo.save(usd);
        
//        currencyRepo.saveAll(Arrays.asList(rub, eur, usd));
        
//        if (true) return;
        
        Account rubAcc = new Account(user1, "123", rub, 200);
        Account eurAcc = new Account(user1, "456", eur, 0);
        Account usdAcc = new Account(user1, "789", usd, 0);
        
        rubAcc = accountRepo.save(rubAcc);
        eurAcc = accountRepo.save(eurAcc);
        usdAcc = accountRepo.save(usdAcc);
        
        
        
//        List<Exchange> exchanges = new ArrayList<Exchange>() {{
//            
//            add(new Exchange(rubAcc, LocalDateTime.of(2019, Month.JANUARY, 25, 0, 0, 0), 200))
//            
//            add(new Rate(LocalDateTime.of(2019, Month.JANUARY, 25, 0, 0, 0), eur, 50, 50));
//            add(new Rate(LocalDateTime.of(2019, Month.JANUARY, 26, 0, 0, 0), eur, 60, 60));
//            add(new Rate(LocalDateTime.of(2019, Month.JANUARY, 27, 0, 0, 0), eur, 70, 70));
//            
//            add(new Rate(LocalDateTime.of(2019, Month.JANUARY, 25, 0, 0, 0), usd, 40, 40));
//            add(new Rate(LocalDateTime.of(2019, Month.JANUARY, 26, 0, 0, 0), usd, 50, 50));
//            add(new Rate(LocalDateTime.of(2019, Month.JANUARY, 27, 0, 0, 0), usd, 60, 60));
//        }};
//        
//        rates = rateRepo.saveAll(rates);
//        
        
    }
}
