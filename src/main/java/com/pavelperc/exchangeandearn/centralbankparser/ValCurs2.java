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
    private String id;
    @XmlAttribute(name = "DateRange1")
    private String dataRange1;
    @XmlAttribute(name = "DateRange2")
    private String dataRange2;
    @XmlAttribute(name = "name")
    private String name;
    @XmlElement(name = "Record")
    private ArrayList<Record> records;
}
