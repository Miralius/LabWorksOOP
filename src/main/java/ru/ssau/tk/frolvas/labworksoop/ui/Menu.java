package ru.ssau.tk.frolvas.labworksoop.ui;

import ru.ssau.tk.frolvas.labworksoop.functions.*;
import ru.ssau.tk.frolvas.labworksoop.functions.factory.*;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.util.*;

public class Menu extends JFrame {
    private JFrame frame;
    private JButton inputButtonTable = new JButton("Создать табулированную функцию");
    private JButton inputButtonMath = new JButton("Создать простую функцию");
    private JButton inputButtonFactory = new JButton("Выбрать тип фабрики");
    private List<Double> xValues = new ArrayList<>();
    private List<Double> yValues = new ArrayList<>();
    private AbstractTableModel tableModel = new TableModel(xValues, yValues);
    private JTable table = new JTable(tableModel);
    private TabulatedFunctionFactory factory;
    private TabulatedFunction function;

    public Menu() {
        setTitle("Функции");
        setBounds(300, 200, 800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        actionPerformed();
        compose();
        this.factory = new ArrayTabulatedFunctionFactory();
    }

    public void wrapTable(int countOld, int countNew) {
        for (int i = 0; i < countOld; i++) {
            xValues.remove(countOld - i - 1);
            yValues.remove(countOld - i - 1);
            tableModel.fireTableDataChanged();
        }
        for (int i = 0; i < countNew; i++) {
            xValues.add(function.getX(i));
            yValues.add(function.getY(i));
        }
    }

    public void actionPerformed() {
        inputButtonTable.addActionListener(event -> {
                    try {
                        int countOld = xValues.size();
                        TabulatedFunctionWindow.main(factory, data -> this.function = data);
                        int countNew = function.getCount();
                        wrapTable(countOld, countNew);
                    } catch (Exception e) {
                        new ErrorWindow(this, e);
                    }
                }
        );
        inputButtonMath.addActionListener(event -> {
            try {
                int countOld = xValues.size();
                MathFunctionWindow.main(factory, data -> this.function = data);
                int countNew = function.getCount();
                wrapTable(countOld, countNew);
            } catch (Exception e) {
                new ErrorWindow(this, e);
            }
        });
        inputButtonFactory.addActionListener(event -> {
            try {
                SettingWindow.main(factory);
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
        JScrollPane tableScrollPane = new JScrollPane(table);
        layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                .addGroup(layout.createSequentialGroup()
                        .addComponent(inputButtonTable)
                        .addComponent(inputButtonMath)
                        .addComponent(inputButtonFactory))
                .addComponent(tableScrollPane)
        );
        layout.setVerticalGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(inputButtonTable)
                        .addComponent(inputButtonMath)
                        .addComponent(inputButtonFactory))
                .addComponent(tableScrollPane)
        );
    }

    public static void main(String[] args) {
        Menu window = new Menu();
        window.setVisible(true);
    }
}