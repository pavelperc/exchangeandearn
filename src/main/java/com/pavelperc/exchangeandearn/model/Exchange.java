package com.pavelperc.exchangeandearn.model;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.time.LocalDateTime;

/** History of transactions. */
public class Exchange {
    @Id
    @GeneratedValue
    private long id;
    
    @ManyToOne
    private Account account;
    
    private LocalDateTime time;
    
    @ManyToOne
    private Rate rate;
    
    /** Added money. could be negative. The currency is from account.*/
    private Double added;
}
