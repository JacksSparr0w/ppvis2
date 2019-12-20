package com.ppvis.lab2.ui;

import com.ppvis.lab2.Creator;
import com.ppvis.lab2.bean.ATM;
import com.ppvis.lab2.bean.Card;
import com.ppvis.lab2.bean.Cash;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SupervisorMode implements UserMode {
    private Card card;
    private JDialog frame;
    private Container container;

    public SupervisorMode(JFrame parent) {
        frame = new JDialog(parent, true);
        container = frame.getContentPane();
        createInterface();

        frame.setSize(new Dimension(400, 350));
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public void createInterface() {
        JPanel panel = new JPanel();

        JButton off = new JButton("Выключить");
        JButton on = new JButton("Включить");
        JButton report = new JButton("Отчет");
        JButton fill = new JButton("Заправить банкомат");
        JButton finish = new JButton("Завершить работу");

        off.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                offATM();
            }
        });
        on.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                onATM();
            }
        });

        report.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JDialog dialog = new JDialog(frame);
                JPanel panel1 = new JPanel();
                JButton ok = new JButton("ок");
                ok.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        dialog.dispose();
                    }
                });
                java.util.List<Cash> cashList = ATM.getInstance().report();
                if (cashList.isEmpty()){
                    panel1.add(new JLabel("деняк нет"));
                } else {
                    Integer sum = 0;
                    for (Cash cash : cashList) {
                        panel1.add(new JLabel("Номинал: " + cash.getNominal() + " BYN, Количество: " + cash.getAmount()));
                        sum += cash.getAmount() * cash.getNominal();
                    }
                    panel1.add(new JLabel("Итого: " + sum + " BYN"));
                }
                panel1.add(ok);
                dialog.add(panel1);
                dialog.setSize(250, 250);
                dialog.setLocationRelativeTo(frame);
                dialog.setVisible(true);
            }
        });
        fill.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                report.doClick();
                JDialog dialog = new JDialog(frame);
                JPanel panel1 = new JPanel();
                JLabel nominalLabel = new JLabel("Номинал");
                JTextField nominalField = new JTextField();
                nominalField.setColumns(15);
                JLabel amountLabel = new JLabel("Количество");
                JTextField amountfield = new JTextField();
                amountfield.setColumns(15);
                JButton ok = new JButton("готово");
                ok.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        Integer nominal = null;
                        Integer amount = null;
                        try {
                            nominal = Integer.valueOf(nominalField.getText());
                            amount = Integer.valueOf(amountfield.getText());
                            nominalField.setText("");
                            amountfield.setText("");
                        } catch (NumberFormatException ignored) {
                        }
                        if (nominal != null && amount != null && nominal > 0 && amount > 0) {
                            fillATM(Creator.getCash(amount, nominal));
                            dialog.dispose();
                            report.doClick();
                        }
                    }
                });
                panel1.add(nominalLabel);
                panel1.add(nominalField);
                panel1.add(amountLabel);
                panel1.add(amountfield);
                panel1.add(ok);
                dialog.add(panel1);
                dialog.setSize(250, 250);
                dialog.setLocationRelativeTo(frame);
                dialog.setVisible(true);

            }
        });
        finish.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
            }
        });
        panel.add(off);
        panel.add(on);
        panel.add(report);
        panel.add(fill);
        panel.add(finish);
        container.add(panel);
    }

    public void changeMode() {
        frame.dispose();
    }

    void report(Cash cash) {
        ATM.getInstance().report();
    }

    void fillATM(Cash cash) {
        ATM.getInstance().fillCash(cash);
    }

    void offATM() {
        ATM.getInstance().off();
    }

    void onATM() {
        ATM.getInstance().on();
    }

    void finish() {
        frame.dispose();
    }
}
