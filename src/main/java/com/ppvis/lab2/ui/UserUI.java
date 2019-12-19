package com.ppvis.lab2.ui;

import com.ppvis.lab2.Creator;
import com.ppvis.lab2.bean.ATM;
import com.ppvis.lab2.bean.Card;
import com.ppvis.lab2.bean.Cash;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UserUI implements UserMode {
    private Card card;
    private JFrame frame;
    private Container container;
    private JPanel mainPanel;
    private volatile Boolean authentificate = Boolean.FALSE;

    public UserUI(Card card) {
        this.card = card;
        frame = new JFrame();
        container = frame.getContentPane();
        createInterface();

        frame.setSize(new Dimension(350, 350));
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public void createInterface() {
        mainPanel = new JPanel();
        JButton takeCashButton = new JButton("Снять наличные");
        takeCashButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                authentificate();
                if (!authentificate) {
                    return;
                }
                JDialog dialog = new JDialog(frame, "Снять наличные");
                JTextField summa = new JTextField("Сумма");
                JLabel label = new JLabel("Доступно: " + ATM.getInstance().getBalance(card.getNumber()) + " BYN"); //TODO
                JButton button = new JButton("Подтвердить");
                button.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent actionEvent) {
                        Integer amount = null;
                        try {
                            amount = Integer.valueOf(summa.getText());
                        } catch (NumberFormatException e) {
                            dialog.dispose();
                        }
                        takeCash(Creator.getCash(amount));
                    }
                });

                dialog.setSize(250, 250);
                dialog.setVisible(true);
            }
        });
        JButton fillBalanceButton = new JButton("Пополнить счет");
        fillBalanceButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                authentificate();
                if (!authentificate) {
                    return;
                }
                JDialog dialog = new JDialog(frame, "Пополнить наличные");
                JTextField summa = new JTextField("Сумма");
                JButton button = new JButton("Подтвердить");
                button.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent actionEvent) {
                        Integer amount = null;
                        try {
                            amount = Integer.valueOf(summa.getText());
                        } catch (NumberFormatException e) {
                            dialog.dispose();
                        }
                        fillBalance(Creator.getCash(amount));
                    }
                });

                dialog.add(summa);
                dialog.add(button);
                dialog.setSize(250, 250);
                dialog.setVisible(true);
            }
        });
        JButton viewBalanceButton = new JButton("Посмотреть баланс");
        viewBalanceButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                authentificate();

                if (!authentificate) {
                    return;
                }
                JDialog dialog = new JDialog(frame, "Снять наличные");
                JPanel panel = new JPanel();
                JLabel label = new JLabel("Доступно: " + ATM.getInstance().getBalance(card.getNumber()) + " BYN");

                panel.add(label);
                dialog.add(panel);
                dialog.setSize(250, 250);
                dialog.setVisible(true);
            }
        });
        JButton takeOffCardButton = new JButton("Забрать карту");
        takeOffCardButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                finish();
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
        ATM.getInstance().takeCash(card.getNumber(), cash);
    }

    void fillBalance(Cash cash) {
        ATM.getInstance().fillBalance(card.getNumber(), cash);
    }

    void viewBalance() {
        ATM.getInstance().getBalance(card.getNumber());
    }

    void finish() {
        frame.dispose();
    }

    private boolean authentificate() {
        JDialog dialog = new JDialog(frame, "Введите пин-код");
        JPanel panel = new JPanel();
        JTextField pinCodeField = new JTextField();
        pinCodeField.setColumns(15);
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
                if (card.getPinCode().equals(pinCode)) {
                    authentificate = true;
                }
                dialog.dispose();
            }
        });
        panel.add(pinCodeField);
        panel.add(button);
        dialog.add(panel);

        dialog.setSize(250, 250);
        dialog.setVisible(true);
        return authentificate;
    }

}
