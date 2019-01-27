package com.pavelperc.exchangeandearn.controller;

import com.pavelperc.exchangeandearn.dto.ValuteDto;
import com.pavelperc.exchangeandearn.exceptions.AccessForbiddenException;
import com.pavelperc.exchangeandearn.model.*;
import com.pavelperc.exchangeandearn.repo.AccountRepo;
import com.pavelperc.exchangeandearn.repo.ExchangeRepo;
import com.pavelperc.exchangeandearn.repo.UserRepo;
import com.pavelperc.exchangeandearn.service.AccountService;
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
    
    @Autowired
    AccountService accountService;
    
    @Autowired
    CBController cbController;
    
    @GetMapping("/{id}")
    Account getAccount(@PathVariable("id") Account account, Principal principal) {
        User currentUser = userRepo.findByLogin(principal.getName()).get();
        if (account.getUser() != currentUser && !currentUser.getRoles().contains(Role.ADMIN))
            throw new AccessForbiddenException("Account " + account.getId() + " is from user " + account.getUser().getLogin() + ", but expected from " + currentUser.getLogin());
        return account;
    }
    
    
    @GetMapping("/exchange/{id1}{id2}/{money}")
    Exchange transaction(@PathVariable("id1") Account rub,
                        @PathVariable("id2") Account foreign, 
                        @PathVariable("money") double money, 
                        Principal principal) {
        User currentUser = userRepo.findByLogin(principal.getName()).get();
        
        // EUR
        String currStr = foreign.getCurrency().getName();
        
        ValuteDto dto = cbController.allCurrentCourse().getValutes().stream()
                .filter(valuteDto -> valuteDto.getCharCode().equals(currStr))
                .findFirst().get();
        
        Rate rate = new Rate(dto.getValue());
        
        return accountService.exchange(rub.getId(), foreign.getId(), money, LocalDateTime.now(), rate);
    }
    
    
}
