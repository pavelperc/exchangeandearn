package com.pavelperc.exchangeandearn.controller;

import com.pavelperc.exchangeandearn.model.Currency;
import com.pavelperc.exchangeandearn.model.Exchange;
import com.pavelperc.exchangeandearn.model.User;
import com.pavelperc.exchangeandearn.repo.ExchangeRepo;
import com.pavelperc.exchangeandearn.repo.UserRepo;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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

    @GetMapping("")
    public Func graphicBuilder(
            @RequestParam(value = "currency_id", required = false) Currency currency,
            Principal principal
    ) {
//        List<Exchange> ls= exchangeRepo.filterForeignIn()

        double[] a = {1.0, 2.0, 3.0};
        double[] b = {1.0, 2.0, 3.0};

        Func f = new Func(a, b);

        return f;
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
