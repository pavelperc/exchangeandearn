package com.pavelperc.exchangeandearn.repo;

import com.pavelperc.exchangeandearn.model.Account;
import com.pavelperc.exchangeandearn.model.Exchange;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;

public interface ExchangeRepo extends JpaRepository<Exchange, Long> {
    
    /** select all out transactions from accFrom account. in the given time range */
    List<Exchange> findAllByAccFrom_IdAndUsefulForPredictionAndTimeBetween(long accFromId, boolean isUsefulForPrediction, LocalDateTime start, LocalDateTime from);
    
    
    /** select all out transactions from accFrom account. in the given time range */
    List<Exchange> findAllByAccTo_IdAndUsefulForPredictionAndTimeBetween(long accToId, boolean isUsefulForPrediction, LocalDateTime start, LocalDateTime from);
    
    @Query("select e\n" +
            "from ACCOUNT a\n" +
            "join EXCHANGE e on a.ID = e.ACC_TO_ID\n" + // filter by rub account
            "join RATE r on e.RATE_TO_ID = r.ID\n" + // euro to rub
            "where a.ID = :accForeignId and e.USEFUL_FOR_PREDICTION = true\n" +
            "order by r.SELL")
    List<Exchange> filterForeignIn(long accForeignId);
    
    @Query("select e\n" +
            "from ACCOUNT a\n" +
            "join EXCHANGE e on a.ID = e.ACC_FROM_ID \n" + // filter by euro account (example)
            "join RATE r on e.RATE_FROM_ID = r.ID\n" + // euro to rub
            "where a.ID = accForeignId and e.USEFUL_FOR_PREDICTION = true\n" +
            "order by r.SELL\n")
    List<Exchange> filterForeignOut(long accForeignId);
    
    
}
