package com.pavelperc.exchangeandearn.model;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * History of transactions.
 */
@Entity
@Data
public class Exchange {
    @Id
    @GeneratedValue
    private long id;
    
    @ManyToOne
    private Account account;
    
    private LocalDateTime time;
    
    @OneToOne(cascade = {CascadeType.ALL})
    private Rate rate;
    
    /**
     * Added money. could be negative. The currency is from account.
     */
    private Double added;
    
    
    private boolean isUsefulForPrediction = true;
    
    public Exchange() {
    }
    
    public Exchange(Account account, LocalDateTime time, Double added, Rate rate) {
        this.account = account;
        this.time = time;
        this.added = added;
        this.rate = rate;
    }
}
