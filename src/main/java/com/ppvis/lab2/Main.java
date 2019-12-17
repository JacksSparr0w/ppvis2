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
        JTextField numberOfCardField = new JTextField("Номер карты");
        JTextField pinCodeField = new JTextField("PIN CODE");
        JButton button = new JButton("Подтвердить");
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                Integer number = null;
                Integer pinCode = null;
                try {
                    number = Integer.valueOf(numberOfCardField.getText());
                    pinCode = Integer.valueOf(pinCodeField.getText());
                } catch (NumberFormatException e) {
                    numberOfCardField.setText("");
                    pinCodeField.setText("");
                }
                if (AuthorizationSystem.authorize(number, pinCode).isPresent()) {
                    Creator.getUserUI(number);
                } else {
                    numberOfCardField.setText("");
                    pinCodeField.setText("");
                }
            }
        });

        panel.add(numberOfCardField);
        panel.add(pinCodeField);
        panel.add(button);
    }

    static void enterAsSupervisor() {
        Creator.getSupervisorMode();
    }
}
