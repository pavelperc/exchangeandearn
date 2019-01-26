package com.pavelperc.exchangeandearn.model;

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
    
    
//    double getAddedMoneyInFinalCurrency() { // TODO getAddedMoneyInFinalCurrency
//        // in initial caurrency
//        double money = addedMoney;
//        money *= rateTo.getSell();
//        
//        
//        
//        return addedMoney / rate
//    }
    
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
