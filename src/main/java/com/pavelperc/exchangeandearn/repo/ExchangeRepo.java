package com.pavelperc.exchangeandearn.repo;

import com.pavelperc.exchangeandearn.model.Account;
import com.pavelperc.exchangeandearn.model.Exchange;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;

public interface ExchangeRepo extends JpaRepository<Exchange, Long> {
    
    List<Exchange> findAllByAccForeign_Id(long accForeignId);
    
    
//    
//    /** Sorted by foreign rate ascending. Сначала самые дешёвые покупки зарубежной валюты. */
//    @Query("select e\n" +
//            "from ACCOUNT a\n" +
//            "join EXCHANGE e on a.ID = e.ACC_FOREIGN\n" + // filter by rub account
//            "join RATE r on e.RATE_TO_ID = r.ID\n" + // euro to rub
//            "where a.ID = :accForeignId and e.USEFUL_FOR_PREDICTION = true\n" +
//            "order by r.BUY")
//    List<Exchange> filterForeignIn(long accForeignId);
//    
//    @Query("select e\n" +
//            "from ACCOUNT a\n" +
//            "join EXCHANGE e on a.ID = e.ACC_FROM_ID \n" + // filter by euro account (example)
//            "join RATE r on e.RATE_FROM_ID = r.ID\n" + // euro to rub
//            "where a.ID = accForeignId and e.USEFUL_FOR_PREDICTION = true\n" +
//            "order by r.SELL\n")
//    List<Exchange> filterForeignOut(long accForeignId);
//    
    
}
