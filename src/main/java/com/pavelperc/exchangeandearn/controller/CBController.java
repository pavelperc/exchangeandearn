package com.pavelperc.exchangeandearn.controller;

import com.pavelperc.exchangeandearn.centralbankparser.*;
import com.pavelperc.exchangeandearn.dto.ValuteDto;
import com.pavelperc.exchangeandearn.form.RequestForm;
import com.pavelperc.exchangeandearn.model.Currency;
import com.pavelperc.exchangeandearn.repo.CurrencyRepo;
import lombok.var;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.pavelperc.exchangeandearn.dto.ValCursDto;

import javax.xml.bind.JAXB;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api/currency")
public class CBController {
    @Value("${cb.daily}")
    private String UrlCBDaily;
    @Value("${cb.dynamic}")
    private String UrlCBDynamic;
    @Autowired
    CurrencyRepo currencyRepo;

    @GetMapping("")
    public List<Currency> allCurrency() {
        return currencyRepo.findAll();
    }


    @GetMapping("/cbcurrentcourse")
    public ValCursDto allCurrentCourse(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate today = LocalDate.now();
        LocalDate yesterday = today.minusDays(1);

        ValCurs exchangeRateToday = JAXB.unmarshal(UrlCBDaily + "date_req=" + today.format(formatter), ValCurs.class);
        ValCurs exchangeRateYesterday = JAXB.unmarshal(UrlCBDaily + "date_req=" + yesterday.format(formatter), ValCurs.class);

        ArrayList<ValuteDto> valutes = new ArrayList<>();
        ArrayList<Valute> valsT = exchangeRateToday.getValutes();
        ArrayList<Valute> valsY = exchangeRateYesterday.getValutes();
        for (int i = 0; i< valsT.size(); i++){
            Valute valuteT = valsT.get(i);
            Valute valuteY = valsY.get(i);

            if (currencyRepo.findByName(valuteT.getCharCode()).isPresent()){
                double valueT = 1.0 * Double.parseDouble(valuteT.getValue().replace(",","."))/Double.parseDouble(valuteT.getNominal());
                char sign = '+';
                if (valuteT.getCharCode().equals(valuteY.getCharCode())) {
                    double valueY = 1.0 * Double.parseDouble(valuteY.getValue().replace(",", ".")) / Double.parseDouble(valuteY.getNominal());
                    if (valueT-valueY < 0)
                        sign = '-';
                }
                valutes.add(new ValuteDto(valuteT.getCharCode(), valueT,sign));
            }
        }

        return new ValCursDto(valutes);
    }

    @GetMapping("/cbcurrencydynamics")
    public ExchangeRate currencyDynamics(RequestForm form){
        String url = UrlCBDynamic + "date_req1=" + form.getFrom() + "&date_req2=" + form.getTo() + "&VAL_NM_RQ=";
        ArrayList<ValCurs2> dynamic = new ArrayList<>();

        ArrayList<String> dates = new ArrayList<>();
        ArrayList<String> values = new ArrayList<>();
        var name = currencyRepo.findByName(form.getType());
        if(name.isPresent()) {
            ValCurs2 a = JAXB.unmarshal(UrlCBDynamic + "date_req1=" + form.getFrom() + "&date_req2=" + form.getTo() + "&VAL_NM_RQ=" + name.get().getCentralBankId(), ValCurs2.class);
            for (Record record : a.getRecords()){
                dates.add(record.getDate());
                values.add(record.getValue());
            }
        }

        return new ExchangeRate(dates, values);
    }
}
