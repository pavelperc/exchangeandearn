package com.pavelperc.exchangeandearn.controller;

import com.pavelperc.exchangeandearn.model.Account;
import com.pavelperc.exchangeandearn.model.Exchange;
import com.pavelperc.exchangeandearn.model.User;
import com.pavelperc.exchangeandearn.repo.ExchangeRepo;
import com.pavelperc.exchangeandearn.repo.UserRepo;
import com.pavelperc.exchangeandearn.service.AccountService;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;


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
    
    @GetMapping("profits/{account_id}")
    public Object[] graphicBuilder(
            @PathVariable("account_id") Account accForeign,
            Principal principal
    ) {
        User currentUser = userRepo.findByLogin(principal.getName()).get();
        
        List<Exchange> history = exchangeRepo.findAllByAccForeign_Id(accForeign.getId());
        
        history = history.stream().sorted(Comparator.comparing(Exchange::getTime)).collect(Collectors.toList());
        
        List<Double> profits = history.stream().map(exchange -> exchange.getProfit()).collect(Collectors.toList());
        
        List<LocalDateTime> dates = history.stream().map(exchange -> exchange.getTime()).collect(Collectors.toList());
        
        
        return new Object[] {profits, dates};
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
