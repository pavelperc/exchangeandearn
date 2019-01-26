package com.pavelperc.exchangeandearn.repo;

import com.pavelperc.exchangeandearn.model.Account;
import com.pavelperc.exchangeandearn.model.Exchange;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ExchangeRepo extends JpaRepository<Exchange, Long> {
    
    List<Exchange> findAllByAccFrom_IdAndUsefulForPrediction(long accFromId, boolean isUsefulForPrediction);
}
