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
    private String date;
    @XmlAttribute(name = "name")
    private String name;
    @XmlElement(name = "Valute")
    private ArrayList<Valute> valutes;
}
