package com.pavelperc.exchangeandearn.form;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@ToString
public class RequestForm {
    String from;
    String to;
    String[] type;
}
