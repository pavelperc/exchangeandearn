package com.pavelperc.exchangeandearn.controller;

import com.pavelperc.exchangeandearn.model.Currency;
import com.pavelperc.exchangeandearn.model.User;
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


/**
 * arra
 */
@RestController
@RequestMapping("/api/stat")
public class StatisticController {

    @Autowired
    UserRepo userRepo;

    @GetMapping("")
    public Func graphicBuilder(
            @RequestParam(value = "from", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime timeFrom,
            @RequestParam(value = "to", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime timeTo,
            @RequestParam(value = "currency_id", required = false) Currency currency,
            Principal principal
    ) {
        if (timeFrom == null)
            timeFrom = LocalDateTime.of(1, 1, 1, 1, 1, 1, 1);
        if (timeTo == null)
            timeTo = LocalDateTime.of(3000, 1, 1, 1, 1, 1, 1);

        User currentUser = userRepo.findByLogin(principal.getName()).get();
        double[] a = {1.0};
        double[] b = {1.0};

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
