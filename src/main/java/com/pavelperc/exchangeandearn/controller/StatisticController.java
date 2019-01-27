package com.pavelperc.exchangeandearn.controller;

import com.pavelperc.exchangeandearn.model.Account;
import com.pavelperc.exchangeandearn.model.Currency;
import com.pavelperc.exchangeandearn.model.Exchange;
import com.pavelperc.exchangeandearn.model.User;
import com.pavelperc.exchangeandearn.repo.ExchangeRepo;
import com.pavelperc.exchangeandearn.repo.UserRepo;
import com.pavelperc.exchangeandearn.service.AccountService;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.List;


/**
 * arra
 */
@RestController
@RequestMapping("/api/stat")
public class StatisticController {

    
    @Autowired
    ExchangeRepo exchangeRepo;
    
    @Autowired
    AccountService accountService;
    
    @Autowired
    UserRepo userRepo;
    
    @GetMapping("{account_id}")
    public Func graphicBuilder(
            @PathVariable("account_id") Account account,
            Principal principal
    ) {
    
        User currentUser = userRepo.findByLogin(principal.getName()).get();
        
        
        
//        List<Exchange> ls= exchangeRepo.filterForeignIn()
        
        
        
        
        return null;
    }


}

/**
 * class function special for front end
 */
@Data
@AllArgsConstructor
class Func {
    private double[] xArray;
    private double[] yArray;
}
