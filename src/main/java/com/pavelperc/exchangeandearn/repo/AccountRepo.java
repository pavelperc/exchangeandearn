package com.pavelperc.exchangeandearn.repo;

import com.pavelperc.exchangeandearn.model.Account;
import com.pavelperc.exchangeandearn.model.Currency;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepo extends JpaRepository<Account, Long> {
    
}
