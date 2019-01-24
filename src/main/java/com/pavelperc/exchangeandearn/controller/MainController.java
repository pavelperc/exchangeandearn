package com.pavelperc.exchangeandearn.controller;

import com.pavelperc.exchangeandearn.model.Admin;
import com.pavelperc.exchangeandearn.model.User;
import com.pavelperc.exchangeandearn.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api")
public class MainController {
    
    @Autowired
    UserRepo userRepo;
    
    @GetMapping
    List<User> allUsers() {
        return userRepo.findAll();
    }
    
    @GetMapping("/admins")
    List<User> allAdmins() {
        return userRepo.findAll().stream().filter(user -> user instanceof Admin).collect(Collectors.toList());
    }
    
    
}
