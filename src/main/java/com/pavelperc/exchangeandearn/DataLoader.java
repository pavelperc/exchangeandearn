package com.pavelperc.exchangeandearn;


import com.google.common.collect.ImmutableSet;
import com.pavelperc.exchangeandearn.model.Role;
import com.pavelperc.exchangeandearn.model.User;
import com.pavelperc.exchangeandearn.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

import static com.pavelperc.exchangeandearn.model.Role.ADMIN;
import static com.pavelperc.exchangeandearn.model.Role.USER;

@Component
public class DataLoader implements ApplicationRunner {
    @Autowired
    UserRepo userRepo;
    
    @Transactional // FIXES something
    @Override
    public void run(ApplicationArguments args) throws Exception {
    
        // SETUP H2 DATABASE TO START A SERVER!!!!!!!! (for debug with intellij idea)
        org.h2.tools.Server.createTcpServer().start();
        
        
        List<User> users = new ArrayList<User>(){{
            add(new User("pavel", "password", ImmutableSet.of(Role.USER)));
            add(new User("polly", "password", ImmutableSet.of(Role.USER)));
            add(new User("alex", "password", ImmutableSet.of(Role.USER)));
            add(new User("vlad", "password", ImmutableSet.of(USER)));
            
            add(new User("admin", "admin", ImmutableSet.of(USER, ADMIN)));
        }};
        
        userRepo.saveAll(users);
    }
}
