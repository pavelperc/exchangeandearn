package com.pavelperc.exchangeandearn.controller;

import com.pavelperc.exchangeandearn.model.Account;
import com.pavelperc.exchangeandearn.model.Exchange;
import com.pavelperc.exchangeandearn.model.User;
import com.pavelperc.exchangeandearn.repo.AccountRepo;
import com.pavelperc.exchangeandearn.repo.ExchangeRepo;
import com.pavelperc.exchangeandearn.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
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
        
        return  account;
    }
    
    @GetMapping("/{id}/out")
    List<Exchange> outTransactions(@PathVariable("id") Account account, Principal principal) {
        User currentUser = userRepo.findByLogin(principal.getName()).get();
        
        return exchangeRepo.findAllByAccFrom_IdAndUsefulForPrediction(account.getId(), true);
    }
    
    
    
    @GetMapping("/{id}/in")
    List<Exchange> outTransactions(@PathVariable("id") Account account, Principal principal) {
        User currentUser = userRepo.findByLogin(principal.getName()).get();
        
        return exchangeRepo.findAllByAccFrom_IdAndUsefulForPrediction(account.getId(), true);
    }
    
    
    
    
}
