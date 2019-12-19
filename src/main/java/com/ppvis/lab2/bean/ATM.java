package com.ppvis.lab2.bean;

import com.ppvis.lab2.Creator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ATM {
    private static ATM instance;
    private Map<Integer, BankAccount> all;

    private ATM() {
        all = new HashMap<>();
        dataInitialize();
    }

    private void dataInitialize() {
        for (int i = 1; i < 10; i++) {
            all.put(i, Creator.getBankAccount(i));
            for (int j = 1; j < 4; j++){
                all.get(i).addCard(Creator.getCard(i, j * 1111));
            }
        }
    }

    public static ATM getInstance() {
        if (instance == null) {
            instance = new ATM();
        }
        return instance;
    }

    public void addAccount(BankAccount bankAccount) {
        all.put(bankAccount.getNumber(), bankAccount);
    }

    public void addCardToAccount(Integer number, Card card) {
        all.get(number).addCard(card);
    }

    public List<Card> getCardByAccount(Integer number) {
        if (all.containsKey(number)){
            return all.get(number).getCards();
        } else {
            return new ArrayList<>();
        }
    }

    public Double getBalance(Integer number) {
        return all.get(number).getBalance();
    }

    public boolean takeCash(Integer number,  Cash cash){
        return all.get(number).takeCash(cash);
    }

    public void fillBalance(Integer number, Cash cash){
        all.get(number).fillBalance(cash);
    }
}
