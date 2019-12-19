package com.ppvis.lab2.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Data
@NoArgsConstructor
public class BankAccount {
    private List<Cash> cash;
    private Integer number;
    private Double balance;
    private List<Card> cards;

    public BankAccount(Integer number){
        cards = new ArrayList<>();
        this.number = number;
        balance = 0d;
    }

    public double lostOnBalance(){      //???!?!!?
        return getBalance();
    }

    public void addCard(Card card){
        cards.add(card);
    }

    public boolean takeCash(Cash cash){
        if (cash.getAmount() > balance){
            return false;
        }
        balance -= cash.getAmount();
        return true;
    }

    public void fillBalance(Cash cash){
        balance += cash.getAmount();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BankAccount that = (BankAccount) o;
        return number.equals(that.number);
    }

    @Override
    public int hashCode() {
        return Objects.hash(number);
    }
}
