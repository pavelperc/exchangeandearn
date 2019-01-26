package com.pavelperc.exchangeandearn.model;


import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.Size;

@Entity
@Data
public class Currency {
    
    /**
     * This is short name of currency.
     */
    @Id
    @Size(min = 3, max = 3)
    private String idName;
    
    public Currency() {
    }
    
    public Currency(String idName) {
        this.idName = idName;
    }
}
