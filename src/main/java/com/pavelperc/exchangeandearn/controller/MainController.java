package com.pavelperc.exchangeandearn.controller;

import com.pavelperc.exchangeandearn.exceptions.AccessForbiddenException;
import com.pavelperc.exchangeandearn.model.User;
import com.pavelperc.exchangeandearn.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.List;

import static com.pavelperc.exchangeandearn.model.Role.ADMIN;

@RestController
@RequestMapping("api")
public class MainController {
    
    @Autowired
    UserRepo userRepo;
    
    @GetMapping
//    @RolesAllowed({"ROLE_USER", "ROLE_ADMIN"}) // doesn't work!!!!!
    List<User> allUsers(Principal principal) {
        User currentUser = userRepo.findByLogin(principal.getName()).get();
        
        if (!currentUser.getRoles().contains(ADMIN))
            throw new AccessForbiddenException("No permission to see all users");
        
        return userRepo.findAll();
    }
}
