package com.pavelperc.exchangeandearn.controller;

import com.pavelperc.exchangeandearn.exceptions.AccessForbiddenException;
import com.pavelperc.exchangeandearn.model.Account;
import com.pavelperc.exchangeandearn.model.Exchange;
import com.pavelperc.exchangeandearn.model.Role;
import com.pavelperc.exchangeandearn.model.User;
import com.pavelperc.exchangeandearn.repo.AccountRepo;
import com.pavelperc.exchangeandearn.repo.ExchangeRepo;
import com.pavelperc.exchangeandearn.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("api/account")
public class AccountController {
    
    @Autowired
    UserRepo userRepo;
    
    @Autowired
    AccountRepo accountRepo;
    
    @Autowired
    ExchangeRepo exchangeRepo;
    
    
    @GetMapping("/{id}")
    Account getAccount(@PathVariable("id") Account account, Principal principal) {
        User currentUser = userRepo.findByLogin(principal.getName()).get();
        if (account.getUser() != currentUser&& !currentUser.getRoles().contains(Role.ADMIN))
            throw new AccessForbiddenException("Account " + account.getId() + " is from user " + account.getUser().getLogin() + ", but expected from " + currentUser.getLogin());
        return account;
    }
    
}
