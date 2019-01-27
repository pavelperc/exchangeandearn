package com.pavelperc.exchangeandearn.dto;

import lombok.*;

import javax.xml.bind.annotation.XmlAttribute;

@AllArgsConstructor
@Getter
@Setter
@ToString
public class ValuteDto {
    String CharCode;
    Double Value;
    char sign;
}
