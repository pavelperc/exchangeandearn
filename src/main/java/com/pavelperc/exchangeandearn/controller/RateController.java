package com.pavelperc.exchangeandearn.controller;

import com.pavelperc.exchangeandearn.model.Rate;
import com.pavelperc.exchangeandearn.model.User;
import com.pavelperc.exchangeandearn.repo.RateRepo;
import com.pavelperc.exchangeandearn.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

import static com.pavelperc.exchangeandearn.model.Role.ADMIN;

@RestController
@RequestMapping("api/rate")
public class RateController {
    
    @Autowired
    UserRepo userRepo;
    @Autowired
    RateRepo rateRepo;
    
    
    
    @GetMapping("/user")
    User getUser(Principal principal) {
        User currentUser = userRepo.findByLogin(principal.getName()).get();
        
        return  currentUser;
    }
    
    
    @GetMapping("/admins")
    List<User> allAdmins() {
        return userRepo.findAll().stream().filter(user -> user.getRoles().contains(ADMIN)).collect(Collectors.toList());
    }
    
    
    
    
}
