package com.pavelperc.exchangeandearn.model;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Data
@ToString(exclude = {"user"})
@EqualsAndHashCode(of = "id")
public class Account {
    @Id
    @GeneratedValue
    private long id;
    
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @ManyToOne
    private User user;
    
    /**
     * Номер счёта
     */
    @Column(unique = true)
    private String number;
    
    private Double money;
    
    @ManyToOne
    private Currency currency;
    
    public Account() {
    }
    
    
    public Account(User user, String number, Currency currency, double money) {
        this.user = user;
        this.number = number;
        this.currency = currency;
        this.money = money;
    }
    
    public void takeMoney(double delta) {
        if (money < delta)
            throw new IllegalStateException("Not enough money. Can't take " + delta + " from " + toString());
        money -= delta;
    }
    
    public void putMoney(double delta) {
        money += delta;
    }
}
