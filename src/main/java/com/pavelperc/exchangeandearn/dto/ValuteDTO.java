package com.pavelperc.exchangeandearn.dto;

import lombok.*;

import javax.xml.bind.annotation.XmlAttribute;

@AllArgsConstructor
@Getter
@Setter
@ToString
public class ValuteDTO {
    String CharCode;
    Double Value;
}
