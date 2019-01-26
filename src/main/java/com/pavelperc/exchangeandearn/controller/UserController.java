package com.pavelperc.exchangeandearn.controller;

import com.pavelperc.exchangeandearn.model.User;
import com.pavelperc.exchangeandearn.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping("api/user")
public class UserController {
     
    @Autowired
    UserRepo userRepo;
    
    
    
    @GetMapping()
    User getUser(Principal principal) {
        User currentUser = userRepo.findByLogin(principal.getName()).get();
        
        return  currentUser;
    }
    
    
    
}
