package com.pavelperc.exchangeandearn.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
public class PrincipalTestController {

    @GetMapping("/test")
    public String testing (Principal principal){
        if (principal == null)
            return "Logout";
        return "Login";
    }

}
