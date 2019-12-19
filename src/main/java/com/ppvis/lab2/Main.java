package com.ppvis.lab2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
        frame.setName("Вставьте карту");
        JLabel label1 = new JLabel("Номер карты");
        JLabel label2 = new JLabel("пин-код");

        JTextField numberOfCardField = new JTextField();
        numberOfCardField.setColumns(30);
        JTextField pinCodeField = new JTextField();
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
                if (new AuthorizationSystem(number).authorize(number, pinCode).isPresent()) {
                    Creator.getUserUI(Creator.getCard(number, pinCode));
                }
            }
        });
        panel.add(label1);
        panel.add(numberOfCardField);
        panel.add(label2);
        panel.add(pinCodeField);
        panel.add(button);
    }

    static void enterAsSupervisor() {
        Creator.getSupervisorMode();
    }
}
