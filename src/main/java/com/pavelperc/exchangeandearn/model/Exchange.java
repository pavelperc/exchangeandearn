package com.pavelperc.exchangeandearn.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Min;
import java.time.LocalDateTime;

/**
 * History of transactions. Операция перевода валюты.
 */
@NoArgsConstructor
@Entity
@Data
public class Exchange {
    @Id
    @GeneratedValue
    private long id;
    
    @ManyToOne
    private Account accForeign;
    
    
    @OneToOne(cascade = {CascadeType.ALL})
    private Rate rateForeign;
    
    private LocalDateTime time;
    
    private double profit;
    
    /** Added MONEY. Always in foreign, may be negative*/
    private Double addedMoney;
    
    @JsonProperty
    public double getMoneyInRubles() {
        return addedMoney * rateForeign.getSell();
    }
    
    private boolean usefulForPrediction = true;
    
    public Exchange(Account accForeign,
                    Rate rateForeign,
                    LocalDateTime time, 
                    Double addedMoney,
                    boolean usefulForPrediction,
                    double profit) {
        this.accForeign = accForeign;
        this.rateForeign = rateForeign;
        this.time = time;
        this.profit = profit;
        this.addedMoney = addedMoney;
        this.usefulForPrediction = usefulForPrediction;
    }
}
