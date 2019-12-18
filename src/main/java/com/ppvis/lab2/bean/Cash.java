package com.ppvis.lab2.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Cash {
    private Long id;
    private String nominal;
    private Integer amount;

    public Cash(Integer amount){
        this.amount = amount;
        id = 1L;
        nominal = "";

    }
}
