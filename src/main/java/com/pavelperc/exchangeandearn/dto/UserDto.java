package com.pavelperc.exchangeandearn.dto;

import com.pavelperc.exchangeandearn.model.Account;
import com.pavelperc.exchangeandearn.model.Role;
import com.pavelperc.exchangeandearn.model.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class UserDto {
    private long id;
    private String login;
    private Boolean active = true;
    private String firstName = "";
    private String middleName = "";
    private String lastName = "";
    private Set<Role> roles;
    private Set<Account> accounts;

    public UserDto(User user){
        this.id = user.getId();
        this.login = user.getLogin();
        this.active = user.getActive();
        this.firstName = user.getFirstName();
        this.middleName = user.getMiddleName();
        this.lastName = user.getLastName();
        this.roles = user.getRoles();
        this.accounts = user.getAccounts();
    }
}
