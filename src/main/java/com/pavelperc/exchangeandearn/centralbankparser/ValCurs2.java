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
public class ValCurs2 {
    @XmlAttribute(name = "ID")
    String id;
    @XmlAttribute(name = "DateRange1")
    String dataRange1;
    @XmlAttribute(name = "DateRange2")
    String dataRange2;
    @XmlAttribute(name = "name")
    String name;
    @XmlElement(name = "Record")
    ArrayList<Record> records;
}
