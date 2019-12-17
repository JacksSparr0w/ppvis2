package com.ppvis.lab2;

import com.ppvis.lab2.bean.BankAccount;
import com.ppvis.lab2.bean.Cash;
import com.ppvis.lab2.bean.PinCode;
import com.ppvis.lab2.ui.SupervisorMode;
import com.ppvis.lab2.ui.UserUI;

public class Creator {
    static BankAccount getBankAccount() {
        return new BankAccount();
    }

    static Cash getCash() {
        return new Cash();
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
