package com.ppvis.lab2;

import com.ppvis.lab2.bean.ATM;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Main {
    private static JFrame frame;
    private static JPanel panel;

    public static void main(String... arg) {
        frame = new JFrame();
        Container container = frame.getContentPane();
        panel = new JPanel();
        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu("Menu");
        JMenuItem supervisor = new JMenuItem("Техподдержка");

        supervisor.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                enterAsSupervisor();
            }
        });
        enterAsUser();

        menu.add(supervisor);
        menuBar.add(menu);
        frame.setJMenuBar(menuBar);
        container.add(panel);

        frame.setSize(new Dimension(350, 350));
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    static void enterAsUser() {
        frame.addWindowFocusListener(new WindowFocusListener() {
            @Override
            public void windowGainedFocus(WindowEvent e) {
                if (ATM.getInstance().isWork()) {
                    frame.setName("Вставьте карту");
                    JLabel label1 = new JLabel("Номер карты");
                    JLabel label2 = new JLabel("пин-код");

                    JTextField numberOfCardField = new JTextField("");
                    numberOfCardField.setColumns(30);
                    JTextField pinCodeField = new JTextField("");
                    pinCodeField.setColumns(30);
                    JButton button = new JButton("Подтвердить");
                    button.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent actionEvent) {
                            Integer number = null;
                            Integer pinCode = null;
                            try {
                                number = Integer.valueOf(numberOfCardField.getText());
                                pinCode = Integer.valueOf(pinCodeField.getText());
                                numberOfCardField.setText("");
                                pinCodeField.setText("");
                            } catch (NumberFormatException ignored) {
                            }
                            if (number != null && pinCode != null && number > 0 && new AuthorizationSystem(number).authorize(number, pinCode).isPresent()) {
                                Creator.getUserUI(Creator.getCard(number, pinCode), frame);
                            }
                        }
                    });
                    panel.removeAll();
                    panel.add(label1);
                    panel.add(numberOfCardField);
                    panel.add(label2);
                    panel.add(pinCodeField);
                    panel.add(button);
                    panel.validate();
                    panel.repaint();
                } else {
                    panel.removeAll();
                    panel.add(new JLabel("Банкомат выключен"));
                    panel.validate();
                    panel.repaint();
                }
            }

            @Override
            public void windowLostFocus(WindowEvent e) {

            }
        });
    }

    static void enterAsSupervisor() {
        JFrame frame = new JFrame();
        Container container = frame.getContentPane();
        JPanel panel = new JPanel();
        frame.setName("Введите пин-код техподдержки");
        JLabel label2 = new JLabel("пин-код");

        JTextField pinCodeField = new JTextField();
        pinCodeField.setColumns(30);
        JButton button = new JButton("Подтвердить");
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                Integer pinCode = null;
                try {
                    pinCode = Integer.valueOf(pinCodeField.getText());
                    pinCodeField.setText("");
                } catch (NumberFormatException ignored) {
                }
                if (new AuthorizationSystem(-1).authorize(-1, pinCode).isPresent()) {
                    Creator.getSupervisorMode(frame);
                    frame.dispose();
                }
            }
        });
        panel.add(label2);
        panel.add(pinCodeField);
        panel.add(button);

        container.add(panel);

        frame.setSize(new Dimension(350, 350));
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
