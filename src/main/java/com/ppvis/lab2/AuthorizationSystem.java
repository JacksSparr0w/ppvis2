package com.ppvis.lab2;

import com.ppvis.lab2.bean.ATM;
import com.ppvis.lab2.bean.BankAccount;
import com.ppvis.lab2.bean.Card;
import com.ppvis.lab2.bean.PinCode;

import java.util.List;
import java.util.Optional;

public class AuthorizationSystem {
    private static List<Card> cards;

    public AuthorizationSystem(Integer number) {
        cards = ATM.getInstance().getCardByAccount(number);
    }

    public Optional<Card> authorize(Integer number, Integer pinCodeValue) {
        PinCode pinCode = Creator.getPinCode(pinCodeValue);
        return cards.stream()
                .filter(card -> card.getNumber().equals(number) && card.getPinCode().equals(pinCode))
                .findFirst();
    }
}
