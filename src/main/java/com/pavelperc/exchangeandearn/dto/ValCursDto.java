package com.pavelperc.exchangeandearn.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;

@Data
@AllArgsConstructor
public class ValCursDto {
    private ArrayList<ValuteDto> valutes;
}
