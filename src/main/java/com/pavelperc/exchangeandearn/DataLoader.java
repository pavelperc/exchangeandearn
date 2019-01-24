package com.pavelperc.exchangeandearn;


import com.pavelperc.exchangeandearn.model.Admin;
import com.pavelperc.exchangeandearn.model.User;
import com.pavelperc.exchangeandearn.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

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
            add(new User("pavel", "password"));
            add(new User("polly", "password"));
            add(new User("alex", "password"));
            add(new User("vlad", "password"));
            add(new Admin("admin", "admin"));
        }};
        
        userRepo.saveAll(users);
    }
}
