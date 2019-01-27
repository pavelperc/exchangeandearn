package com.pavelperc.exchangeandearn.controller;

import com.pavelperc.exchangeandearn.dto.UserDto;
import com.pavelperc.exchangeandearn.model.Currency;
import com.pavelperc.exchangeandearn.model.Role;
import com.pavelperc.exchangeandearn.model.User;
import com.pavelperc.exchangeandearn.repo.CurrencyRepo;
import com.pavelperc.exchangeandearn.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.HashSet;
import java.util.Optional;

@RestController
public class PrincipalTestController {
    @Autowired
    UserRepo repo;

    @GetMapping("/test")
    public UserDto testing(Principal principal) {
        if (principal != null){
            Optional<User> op = repo.findByLogin(principal.getName());
            if (op.isPresent()){
                return new UserDto(op.get());
            }
        }
        return new UserDto(0,"noLogin",false," "," "," ", null, null);
    }

}
