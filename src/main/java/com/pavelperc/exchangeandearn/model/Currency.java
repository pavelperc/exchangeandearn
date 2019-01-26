package com.pavelperc.exchangeandearn.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.Size;

@NoArgsConstructor
@Entity
@Data
public class Currency {

    public Currency(@Size(min = 3, max = 3) String name, String centralBankId) {
        this.name = name;
        this.centralBankId = centralBankId;
    }

    @Id
    @GeneratedValue
    private long id;

    /**
     * This is short name of currency.
     */
    @Size(min = 3, max = 3)
    private String name;

    private String centralBankId;
}
