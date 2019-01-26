package com.pavelperc.exchangeandearn.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;

@Data
@AllArgsConstructor
public class ValCursDTO {
    private ArrayList<ValuteDTO> valutes;
}
