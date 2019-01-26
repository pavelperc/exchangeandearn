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
    private Account accFrom;
    @ManyToOne
    private Account accTo;
    
    @OneToOne(cascade = {CascadeType.ALL})
    private Rate rateTo;
    
    @OneToOne(cascade = {CascadeType.ALL})
    private Rate rateFrom;
    
    private LocalDateTime time;
    
    /**
     * Added MONEY. ALWAYS POSITIVE. Currency is from accFrom,
     */
    @Min(value = 0L, message = "The value must be positive")
    private Double addedMoney;
    
    @JsonProperty
    double getMoneyInRubles() {
        if (accFrom.getCurrency().getName().equals("RUB")) {
            return addedMoney;
        } else if (accTo.getCurrency().getName().equals("RUB")) {
            return getAddedMoneyInFinalCurrency();
        } else {
            throw new IllegalStateException("No currency in rubles.");
        }
    }
    
    @JsonProperty
    double getMoneyForeign() {
        if (accFrom.getCurrency().getName().equals("RUB")) {
            return getAddedMoneyInFinalCurrency();
        } else {
            return addedMoney;
        }
    }
    
    @JsonProperty
    double getAddedMoneyInFinalCurrency() {
        // money in initial currency
        double money = addedMoney;
        
        // money in rubles
        money *= rateFrom.getSell();
    
        // money in final currency
        money /= rateTo.getBuy();
        
        return money;
    }
    
    private boolean usefulForPrediction = true;
    
    public Exchange(Account accFrom,
                    Account accTo,
                    Rate rateFrom,
                    Rate rateTo,
                    LocalDateTime time,
                    @Min(value = 0L, message = "The value must be positive") Double addedMoney,
                    boolean usefulForPrediction) {
        this.accFrom = accFrom;
        this.accTo = accTo;
        this.rateTo = rateTo;
        this.rateFrom = rateFrom;
        this.time = time;
        this.addedMoney = addedMoney;
        this.usefulForPrediction = usefulForPrediction;
    }
}
