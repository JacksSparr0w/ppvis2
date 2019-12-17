package com.ppvis.lab2.ui;

import com.ppvis.lab2.AuthorizationSystem;
import com.ppvis.lab2.Creator;
import com.ppvis.lab2.bean.Card;
import com.ppvis.lab2.bean.Cash;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Optional;

public class UserUI implements UserMode {
    private Integer numberOfBankAccount;
    private Card card;
    private JFrame frame;
    private Container container;
    private JPanel mainPanel;

    public UserUI(Integer number){
        this.numberOfBankAccount = number;
        createInterface();
        frame = new JFrame();
        container = frame.getContentPane();
    }

    public void createInterface() {
        mainPanel = new JPanel();
        JButton takeCashButton = new JButton("Снять наличные");
        takeCashButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                JDialog dialog = new JDialog(frame, "Снять наличные");

            }
        });
        JButton fillBalanceButton = new JButton("Пополнить счет");
        fillBalanceButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        JButton viewBalanceButton = new JButton("Посмотреть баланс");
        viewBalanceButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        JButton takeOffCardButton = new JButton("Забрать карту");
        takeOffCardButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        mainPanel.add(takeCashButton);
        mainPanel.add(fillBalanceButton);
        mainPanel.add(viewBalanceButton);
        mainPanel.add(takeOffCardButton);
        container.add(mainPanel);
    }

    public void changeMode() {

    }

    void takeCash(Cash cash) {

    }

    void fillBalance(Cash cash) {

    }

    void viewBalance() {

    }

    void finish() {

    }

    private void authentificate(Integer number, Integer pinCode){
        JDialog dialog = new JDialog(frame, "Введите пин-код");
        JTextField pinCodeField = new JTextField("пин-код");
        JButton button = new JButton("ввод");
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                Integer pinCode = null;
                try {
                    pinCode = Integer.valueOf(pinCodeField.getText());
                } catch (NumberFormatException e) {
                    pinCodeField.setText("");
                }
                Optional<Card> optionalCard = AuthorizationSystem.authorize(number, pinCode);
                if (optionalCard.isPresent()) {
                    card = optionalCard.get();
                    dialog.dispose();
                } else {
                    pinCodeField.setText("");
                }
            }
        });

        dialog.setSize(250, 250);
        dialog.setVisible(true);
    }

}
