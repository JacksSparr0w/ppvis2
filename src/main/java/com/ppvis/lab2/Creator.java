package com.ppvis.lab2;

import com.ppvis.lab2.bean.BankAccount;
import com.ppvis.lab2.bean.Card;
import com.ppvis.lab2.bean.Cash;
import com.ppvis.lab2.bean.PinCode;
import com.ppvis.lab2.ui.SupervisorMode;
import com.ppvis.lab2.ui.UserUI;

public class Creator {
    public static BankAccount getBankAccount(Integer number) {
        return new BankAccount(number);
    }

    public static Cash getCash(Integer amount) {
        return new Cash(amount);
    }

    static UserUI getUserUI(Card card) {
        return new UserUI(card);
    }

    static SupervisorMode getSupervisorMode() {
        return new SupervisorMode();
    }

    static Role getRole(String value) {
        return Role.valueOf(value.toUpperCase());
    }

    public static PinCode getPinCode(Integer value) {
        return new PinCode(value);
    }

    public static Card getCard(Integer number, Integer pincode){ return new Card(number, getPinCode(pincode));}
}
