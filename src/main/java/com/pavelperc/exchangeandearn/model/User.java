package com.pavelperc.exchangeandearn.model;

import lombok.Data;

import javax.annotation.Generated;
import javax.persistence.*;

@Entity
@Data
@Inheritance(strategy = InheritanceType.JOINED)
public class User {
    @Id
    @GeneratedValue
    private long id;
    private String login;
    private String password;
    
    public User() {
    }
    
    public User(String login, String password) {
        this.login = login;
        this.password = password;
    }
}
