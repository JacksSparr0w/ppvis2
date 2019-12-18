package com.ppvis.lab2;

import com.ppvis.lab2.bean.BankAccount;
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

    static UserUI getUserUI(Integer number) {
        return new UserUI(number);
    }

    static SupervisorMode getSupervisorMode() {
        return new SupervisorMode();
    }

    static Role getRole(String value) {
        return Role.valueOf(value.toUpperCase());
    }

    static PinCode getPinCode(Integer value) {
        return new PinCode(value);
    }
}
