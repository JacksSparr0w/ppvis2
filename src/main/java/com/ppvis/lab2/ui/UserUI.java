package com.ppvis.lab2.ui;

import com.ppvis.lab2.Creator;
import com.ppvis.lab2.bean.ATM;
import com.ppvis.lab2.bean.Card;
import com.ppvis.lab2.bean.Cash;
import lombok.SneakyThrows;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UserUI implements UserMode {
    private Card card;
    private JDialog frame;
    private Container container;

    public UserUI(Card card, JFrame parent) {
        this.card = card;
        frame = new JDialog(parent, true);
        container = frame.getContentPane();
        createInterface();

        frame.setSize(new Dimension(350, 350));
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public void createInterface() {
        JPanel mainPanel = new JPanel();
        JButton takeCashButton = new JButton("Снять наличные");
        takeCashButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JDialog dialog = new JDialog(frame, "Снять наличные");
                JPanel panel = new JPanel();
                JLabel label = new JLabel("Введите пин-код");
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
                            dialog.dispose();
                        }
                        if (card.getPinCode().equals(Creator.getPinCode(pinCode))) {
                            panel.remove(label);
                            panel.remove(pinCodeField);
                            panel.remove(button);

                            JTextField summa = new JTextField();
                            summa.setColumns(15);
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
                                    dialog.dispose();
                                }
                            });

                            panel.add(new JLabel("Сумма"));
                            panel.add(summa);
                            panel.add(label);
                            panel.add(button);

                            dialog.validate();
                            dialog.repaint();
                        } else {
                            dialog.dispose();
                        }
                    }
                });
                panel.add(label);
                panel.add(pinCodeField);
                panel.add(button);

                dialog.add(panel);
                dialog.setSize(250, 250);
                dialog.setLocationRelativeTo(frame);

                dialog.setVisible(true);
            }
        });
        JButton fillBalanceButton = new JButton("Пополнить счет");
        fillBalanceButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JDialog dialog = new JDialog(frame, "Пополнить счет");
                JPanel panel = new JPanel();
                JLabel label = new JLabel("Введите пин-код");
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
                            dialog.dispose();
                        }
                        if (card.getPinCode().equals(Creator.getPinCode(pinCode))) {
                            panel.remove(label);
                            panel.remove(pinCodeField);
                            panel.remove(button);

                            JTextField summa = new JTextField();
                            summa.setColumns(15);
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
                                    dialog.dispose();
                                }
                            });
                            panel.add(new JLabel("Сумма"));
                            panel.add(summa);
                            panel.add(button);

                            dialog.validate();
                            dialog.repaint();
                        } else {
                            dialog.dispose();
                        }
                    }
                });
                panel.add(label);
                panel.add(pinCodeField);
                panel.add(button);

                dialog.add(panel);
                dialog.setSize(250, 250);
                dialog.setLocationRelativeTo(frame);

                dialog.setVisible(true);
            }
        });
        JButton viewBalanceButton = new JButton("Посмотреть баланс");
        viewBalanceButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JDialog dialog = new JDialog(frame, "Снять наличные");
                JPanel panel = new JPanel();
                JLabel label = new JLabel("Введите пин-код");
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
                            dialog.dispose();
                        }
                        if (card.getPinCode().equals(Creator.getPinCode(pinCode))) {
                            panel.remove(label);
                            panel.remove(pinCodeField);
                            panel.remove(button);
                            panel.add(new JLabel("Доступно: " + ATM.getInstance().getBalance(card.getNumber()) + " BYN"));
                            JButton continueButton = new JButton("Продолжить");
                            continueButton.addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {
                                    dialog.dispose();
                                }
                            });
                            panel.add(continueButton);
                            dialog.validate();
                            dialog.repaint();
                        } else {
                            dialog.dispose();
                        }
                    }
                });
                panel.add(label);
                panel.add(pinCodeField);
                panel.add(button);

                dialog.add(panel);
                dialog.setSize(250, 250);
                dialog.setLocationRelativeTo(frame);

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
        frame.dispose();
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
}
