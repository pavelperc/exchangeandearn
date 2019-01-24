package com.pavelperc.exchangeandearn.model;


import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

@Entity
public class Admin extends User {
    
    
    public Admin() {
    }
    
    public Admin(String login, String password) {
        super(login, password);
    }
}
