package com.pavelperc.exchangeandearn.controller;

import com.pavelperc.exchangeandearn.model.Account;
import com.pavelperc.exchangeandearn.model.Exchange;
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
        
        return account;
    }
    
    private void fixTimeRange(LocalDateTime[] fromTo) {
        if (fromTo[0] == null)
            fromTo[0] = LocalDateTime.of(1, 1, 1, 1, 1, 1, 1);
        if (fromTo[1] == null)
            fromTo[1] = LocalDateTime.of(2020, 1, 1, 1, 1, 1, 1);
    }
    
    @GetMapping("/{id}/out")
    List<Exchange> outTransactions(
            Principal principal,
            @PathVariable("id") Account account,
            @RequestParam(value = "from", required = false) LocalDateTime timeFrom,
            @RequestParam(value = "to", required = false) LocalDateTime timeTo
    
    ) {
        User currentUser = userRepo.findByLogin(principal.getName()).get();
        
//        fixTimeRange(new LocalDateTime[]{timeFrom, timeTo});
        if (timeFrom == null)
            timeFrom = LocalDateTime.of(1,1,1,1,1,1,1);
        if (timeTo == null)
            timeTo = LocalDateTime.of(3000,1,1,1,1,1,1);
        
        
        return exchangeRepo.findAllByAccFrom_IdAndUsefulForPredictionAndTimeBetween(account.getId(), true, timeFrom, timeTo);
    }
    
    @GetMapping("/{id}/in")
    List<Exchange> inTransactions(
            Principal principal,
            @PathVariable("id") Account account,
            @RequestParam(value = "from", required = false) LocalDateTime timeFrom,
            @RequestParam(value = "to", required = false) LocalDateTime timeTo
    
    ) {
        User currentUser = userRepo.findByLogin(principal.getName()).get();
        
//        fixTimeRange(new LocalDateTime[]{timeFrom, timeTo});
        if (timeFrom == null)
            timeFrom = LocalDateTime.of(1,1,1,1,1,1,1);
        if (timeTo == null)
            timeTo = LocalDateTime.of(3000,1,1,1,1,1,1);
        
        return exchangeRepo.findAllByAccTo_IdAndUsefulForPredictionAndTimeBetween(account.getId(), true, timeFrom, timeTo);
    }
    
    
}
