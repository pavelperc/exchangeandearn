package com.pavelperc.exchangeandearn.form;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class RequestForm {
    String from;
    String to;
    String[] type;
}
