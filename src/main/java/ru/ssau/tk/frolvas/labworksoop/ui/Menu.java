package ru.ssau.tk.frolvas.labworksoop.ui;

import javax.swing.*;

public class Menu extends JFrame {
    private JFrame frame;
    private JButton inputButtonTable = new JButton("Create the table");

    public Menu() {
        setTitle("Menu");
        setBounds(300, 200, 200, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        actionPerformed();
        compose();
    }

    public void actionPerformed() {
        inputButtonTable.addActionListener(event -> {
                    try {
                        TabulatedFunctionWindow.main();
                    } catch (Exception e) {
                        new ErrorWindow(this, e);
                    }
                }
        );
    }

    void compose() {
        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);
        layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                .addGroup(layout.createSequentialGroup()
                        .addComponent(inputButtonTable))
        );
        layout.setVerticalGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(inputButtonTable))
        );
    }

    public static void main(String[] args) {
        Menu window = new Menu();
        window.setVisible(true);
    }
}