package com.pavelperc.exchangeandearn.controller;

import com.pavelperc.exchangeandearn.dto.ValuteDto;
import com.pavelperc.exchangeandearn.model.Account;
import com.pavelperc.exchangeandearn.model.Exchange;
import com.pavelperc.exchangeandearn.model.Rate;
import com.pavelperc.exchangeandearn.model.User;
import com.pavelperc.exchangeandearn.repo.ExchangeRepo;
import com.pavelperc.exchangeandearn.repo.UserRepo;
import com.pavelperc.exchangeandearn.service.AccountService;
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
    
    @Autowired
    CBController cbController;
    
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

    
    @GetMapping("predict/{acc_rub_id}/{acc_foreign_id}/{money}")
    public Double predict(
            @PathVariable("acc_rub_id") Account accRub,
            @PathVariable("acc_foreign_id") Account accForeign,
            @PathVariable("money") Double money,
            Principal principal
    ) {
        User currentUser = userRepo.findByLogin(principal.getName()).get();
    
        String currStr = accForeign.getCurrency().getName();
        Rate rate = getLastRate(currStr);
        
        return accountService.getProfit(accRub, accForeign,  rate, money, false);
    }
    
    @GetMapping("predict_max/{acc_rub_id}/{acc_foreign_id}")
    public Double predict(
            @PathVariable("acc_rub_id") Account accRub,
            @PathVariable("acc_foreign_id") Account accForeign,
            Principal principal
    ) {
        User currentUser = userRepo.findByLogin(principal.getName()).get();
        
    
        List<Exchange> history = exchangeRepo.findAllByAccForeign_Id(accForeign.getId());
        
        double sum = 0;
        for (Exchange exchange : history) {
            sum += exchange.getAddedMoney();
        }
        return sum;
    }
    
    Rate getLastRate(String currStr) {
        ValuteDto dto = cbController.allCurrentCourse().getValutes().stream()
                .filter(valuteDto -> valuteDto.getCharCode().equals(currStr))
                .findFirst().get();
        return new Rate(dto.getValue());
    }
}