package com.ppvis.lab2.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@EqualsAndHashCode
public class Cash {
    private Long id;
    private String nominal;
    private Integer amount;

    public Cash(Integer amount){
        id = 1L;
        this.amount = amount;
        nominal = "1";
    }

    public Cash(Integer amount, Integer nominal){
        id = 1L;
        this.amount = amount;
        this.nominal = String.valueOf(nominal);
    }

    public Cash(Cash cash){
        this.amount = new Integer(cash.amount.intValue());
        this.nominal = cash.nominal;
        this.id = cash.id;
    }

    public void addAmount(Integer amount){
        this.amount += amount;
    }

    public Integer getNominal(){
        return Integer.valueOf(nominal);
    }


}
