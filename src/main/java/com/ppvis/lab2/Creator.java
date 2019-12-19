package com.ppvis.lab2;

import com.ppvis.lab2.bean.BankAccount;
import com.ppvis.lab2.bean.Card;
import com.ppvis.lab2.bean.Cash;
import com.ppvis.lab2.bean.PinCode;
import com.ppvis.lab2.ui.SupervisorMode;
import com.ppvis.lab2.ui.UserUI;

import javax.swing.*;

public class Creator {
    public static BankAccount getBankAccount(Integer number) {
        return new BankAccount(number);
    }

    public static Cash getCash(Integer amount) {
        return new Cash(amount);
    }

    public static Cash getCash(Integer amount, Integer nominal){
        return new Cash(amount, nominal);
    }

    static UserUI getUserUI(Card card, JFrame frame) {
        return new UserUI(card, frame);
    }

    static SupervisorMode getSupervisorMode(JFrame frame) {
        return new SupervisorMode(frame);
    }

    static Role getRole(String value) {
        return Role.valueOf(value.toUpperCase());
    }

    public static PinCode getPinCode(Integer value) {
        return new PinCode(value);
    }

    public static Card getCard(Integer number, Integer pincode){ return new Card(number, getPinCode(pincode));}
}
