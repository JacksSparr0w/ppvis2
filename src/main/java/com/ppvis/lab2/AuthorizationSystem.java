package com.ppvis.lab2;

import com.ppvis.lab2.bean.Card;
import com.ppvis.lab2.bean.PinCode;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class AuthorizationSystem {
    private static List<Card> cards;

    public AuthorizationSystem(){
        cards = new ArrayList<>();
    }

    public static Optional<Card> authorize(Integer number, Integer pinCodeValue) {
        PinCode pinCode = Creator.getPinCode(pinCodeValue);
        return cards.stream()
                .filter(card -> card.getNumber().equals(number) && card.getPinCode().equals(pinCode))
                .findFirst();
    }
}
