package com.pavelperc.exchangeandearn.centralbankparser;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.ArrayList;

@AllArgsConstructor
@Getter
public class ExchangeRate {
    ArrayList<String> dates;
    ArrayList<String> values;
}
