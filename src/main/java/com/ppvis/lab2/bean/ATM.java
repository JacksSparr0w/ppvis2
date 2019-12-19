package com.ppvis.lab2.bean;

import com.ppvis.lab2.Creator;

import java.util.*;

public class ATM {
    private static ATM instance;
    private Map<Integer, BankAccount> all;
    private boolean isWork;
    private List<Cash> cash;

    private ATM() {
        all = new HashMap<>();
        dataInitializeForUser();
        dataInitializeForSuperVisor();
        cash = new ArrayList<>();
    }

    private void dataInitializeForUser() {
        for (int i = 1; i < 10; i++) {
            all.put(i, Creator.getBankAccount(i));
            for (int j = 1; j < 4; j++){
                all.get(i).addCard(Creator.getCard(i, j * 1111));
            }
        }
    }

    private void dataInitializeForSuperVisor() {
        int number = -1;
        all.put(number, Creator.getBankAccount(number));
        all.get(number).addCard(Creator.getCard(number, 000));
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
        Cash tempCash = new Cash(cash);
        List<Cash> tempCashList = new ArrayList<>(this.cash);
        tempCashList.sort(new Comparator<Cash>() {
            @Override
            public int compare(Cash o1, Cash o2) {
                return o2.getNominal() - o1.getNominal();
            }
        });
        for (Cash cash2 : tempCashList) {
            while (cash2.getAmount() > 0 && cash2.getNominal() <= tempCash.getAmount()){
                tempCash.setAmount(tempCash.getAmount() - cash2.getNominal());
                cash2.setAmount(cash2.getAmount()-1);
            }
        }
        if (tempCash.getAmount().equals(0)){
            this.cash = tempCashList;
            validateCash();
            return all.get(number).takeCash(cash);
        } else {
            validateCash();
            return false;
        }

    }

    private void validateCash() {
        cash.removeIf(cash1 -> cash1.getAmount().equals(0));
    }

    public void fillBalance(Integer number, Cash cash){
        all.get(number).fillBalance(cash);
    }

    public void on(){
        isWork = true;
    }

    public void off(){
        isWork = false;
    }

    public boolean isWork(){
        return isWork;
    }

    public void fillCash(Cash cash){
        for (Cash c : this.cash){
            if (c.getNominal().equals(cash.getNominal())){
                c.addAmount(cash.getAmount());
                return;
            }
        }
        this.cash.add(cash);
    }

    public List<Cash> report(){
        return cash;
    }
}
