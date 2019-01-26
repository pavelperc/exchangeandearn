package com.pavelperc.exchangeandearn.model;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;


/** One to one for each exchange. Курс */
@Entity
@Data
public class Rate {
    
    @Id
    @GeneratedValue
    private long id;
    
    private double sell;
    private double buy;
    
    
    public Rate() {
    }
    
    public Rate(double sell, double buy) {
        this.sell = sell;
        this.buy = buy;
    }
    
    
    public Rate(double sellAndBuy) {
        this.sell = sellAndBuy;
        this.buy = sellAndBuy;
    }
}
