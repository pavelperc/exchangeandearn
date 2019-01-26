package com.pavelperc.exchangeandearn.centralbankparser;

import lombok.*;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;

@XmlAccessorType(XmlAccessType.FIELD)
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Valute {
    @XmlAttribute(name = "ID")
    String id;
    String NumCode;
    String CharCode;
    String Nominal;
    String Name;
    String Value;
}
