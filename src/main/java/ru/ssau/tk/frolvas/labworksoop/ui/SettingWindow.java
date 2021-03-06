package ru.ssau.tk.frolvas.labworksoop.ui;

import ru.ssau.tk.frolvas.labworksoop.functions.factory.*;

import javax.swing.*;
import java.util.*;

public class SettingWindow extends JDialog {
    private JLabel fontLabel = new JLabel("Выберите тип фабрики функции:");
    private Map<String, TabulatedFunctionFactory> nameFunctionMap = new HashMap<>();
    private JComboBox<String> functionComboBox = new JComboBox<>();
    private JButton okButton = new JButton("OK");
    TabulatedFunctionFactory factory;

    public SettingWindow(TabulatedFunctionFactory factory) {
        setModal(true);
        this.factory = factory;
        setTitle("Настройка");
        setSize(300, 100);
        fillMap();
        compose();
        addButtonListeners();
    }

    public static void main(TabulatedFunctionFactory factory) {
        SettingWindow frame = new SettingWindow(factory);
        frame.setVisible(true);
    }

    public void compose() {
        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);
        layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                .addComponent(fontLabel)
                .addGroup(layout.createSequentialGroup()
                        .addComponent(functionComboBox)
                        .addComponent(okButton))
        );
        layout.setVerticalGroup(layout.createSequentialGroup()
                .addComponent(fontLabel)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(functionComboBox)
                        .addComponent(okButton)
                ));
    }

    public void fillMap() {
        nameFunctionMap.put("Двусвязный список", new LinkedListTabulatedFunctionFactory());
        nameFunctionMap.put("Массив", new ArrayTabulatedFunctionFactory());
        String[] functions = new String[2];
        int i = 0;
        for (String string : nameFunctionMap.keySet()) {
            functions[i++] = string;
        }
        Arrays.sort(functions);
        for (String string : functions) {
            functionComboBox.addItem(string);
        }
    }

    public void addButtonListeners() {
        okButton.addActionListener(event -> {
            try {
                String func = (String) functionComboBox.getSelectedItem();
                this.factory = nameFunctionMap.get(func);
                this.dispose();
            } catch (Exception e) {
                ErrorWindow errorWindow = new ErrorWindow(this, e);
                errorWindow.showErrorWindow(this, e);
            }
        });
    }
}