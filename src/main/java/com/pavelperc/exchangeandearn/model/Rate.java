package com.pavelperc.exchangeandearn.model;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
public class Rate {
    
    @Id
    @GeneratedValue
    private long id;
    
    private LocalDateTime time;
    
    @ManyToOne
    private Currency currency;
    
    private double sell;
    
    private double buy;
    
    
    
}
