package com.pavelperc.exchangeandearn.repo;

import com.pavelperc.exchangeandearn.model.Account;
import com.pavelperc.exchangeandearn.model.Rate;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RateRepo extends JpaRepository<Rate, Long> {
    
}
