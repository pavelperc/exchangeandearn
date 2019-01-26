package com.pavelperc.exchangeandearn.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.FORBIDDEN)
public class AccessForbidden extends RuntimeException {
    public AccessForbidden(String message) {
        super(message);
    }
}
