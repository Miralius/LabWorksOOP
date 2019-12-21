package ru.ssau.tk.frolvas.labworksoop.ui;

import javax.swing.*;

public class Menu extends JFrame {
    private JFrame frame;
    private JButton inputButtonTable = new JButton("Создать табулированную функцию");
    private JButton inputButtonMath = new JButton("Создать простую функцию");
    private JButton inputButtonFactory = new JButton("Выбрать тип фабрики");
    private TabulatedFunctionFactory factory;

    public Menu() {
        setTitle("Функции");
        setBounds(300, 200, 800, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        actionPerformed();
        compose();
        this.factory = new ArrayTabulatedFunctionFactory();
    }

    public void actionPerformed() {
        inputButtonTable.addActionListener(event -> {
                    try {
                        TabulatedFunctionWindow.main(factory);
                    } catch (Exception e) {
                        new ErrorWindow(this, e);
                    }
                }
        );
        inputButtonMath.addActionListener(event -> {
            try {
                MathFunctionWindow.main(factory);
            } catch (Exception e) {
                new ErrorWindow(this, e);
            }
        });
    }


    void compose() {
        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);
        layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                .addGroup(layout.createSequentialGroup()
                        .addComponent(inputButtonFactory)
                        .addComponent(inputButtonMath)
                        .addComponent(inputButtonTable))

        );
        layout.setVerticalGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(inputButtonFactory)
                        .addComponent(inputButtonMath)
                        .addComponent(inputButtonTable))
        );
    }

    public static void main(String[] args) {
        Menu window = new Menu();
        window.setVisible(true);
    }
}
