package com.pavelperc.exchangeandearn.centralbankparser;

import lombok.*;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class ValCurs {
    @XmlAttribute(name = "Date")
    String date;
    @XmlAttribute(name = "name")
    String name;
    @XmlElement(name = "Valute")
    ArrayList<Valute> valutes;
}
