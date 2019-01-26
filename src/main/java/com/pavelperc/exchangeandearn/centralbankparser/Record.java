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
public class Record {
    @XmlAttribute(name = "Date")
    String date;
    @XmlAttribute(name = "Id")
    String id;
    String Nominal;
    String Value;
}
