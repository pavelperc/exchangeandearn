package com.pavelperc.exchangeandearn.repo;

import com.pavelperc.exchangeandearn.model.Account;
import com.pavelperc.exchangeandearn.model.Exchange;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface ExchangeRepo extends JpaRepository<Exchange, Long> {
    
    /** select all out transactions from accFrom account. in the given time range */
    List<Exchange> findAllByAccFrom_IdAndUsefulForPredictionAndTimeBetween(long accFromId, boolean isUsefulForPrediction, LocalDateTime start, LocalDateTime from);
    
    
    /** select all out transactions from accFrom account. in the given time range */
    List<Exchange> findAllByAccTo_IdAndUsefulForPredictionAndTimeBetween(long accToId, boolean isUsefulForPrediction, LocalDateTime start, LocalDateTime from);
}
