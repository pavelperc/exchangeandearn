package com.pavelperc.exchangeandearn.model;


import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Account {
    
    @Id
    @GeneratedValue
    private long id;
    
    @ManyToOne
    private User user;
    
    /**
     * Номер счёта
     */
    @Column(unique = true)
    private String number;
    
    @ManyToOne
    private Currency currency;
    
    public Account() {
    }
    
    
    public Account(User user, String number, Currency currency) {
        this.user = user;
        this.number = number;
        this.currency = currency;
    }
}
