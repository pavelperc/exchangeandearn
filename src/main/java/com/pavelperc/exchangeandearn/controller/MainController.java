package com.pavelperc.exchangeandearn.controller;

import com.pavelperc.exchangeandearn.exceptions.NotFoundException;
import com.pavelperc.exchangeandearn.model.User;
import com.pavelperc.exchangeandearn.repo.UserRepo;
import lombok.var;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.security.RolesAllowed;
import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

import static com.pavelperc.exchangeandearn.model.Role.ADMIN;

@RestController
@RequestMapping("api")
public class MainController {
    
    @Autowired
    UserRepo userRepo;
    
    @GetMapping
//    @RolesAllowed("ADMIN") // doesn't work!!!!!
    List<User> allUsers() {
        return userRepo.findAll();
    }
    
    
    @GetMapping("/user/{login}")
    User getUser(@PathVariable String login, Principal principal) {
        System.out.println("Current User: " + principal.getName());
        User currentUser = userRepo.findByLogin(principal.getName()).get();
        if (!currentUser.getRoles().contains(ADMIN) && !currentUser.getLogin().equals(login))
            throw new NotFoundException("NO PERMISSION TO SEE THAT USER!!");
        
        return userRepo.findByLogin(login).orElseThrow(() -> new NotFoundException("User " + login + " not found!!!!"));
    }
    
    
    @GetMapping("/admins")
    List<User> allAdmins() {
        return userRepo.findAll().stream().filter(user -> user.getRoles().contains(ADMIN)).collect(Collectors.toList());
    }
    
    
}
