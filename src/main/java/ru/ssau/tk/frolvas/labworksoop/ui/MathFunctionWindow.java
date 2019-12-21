package ru.ssau.tk.frolvas.labworksoop.ui;

import ru.ssau.tk.frolvas.labworksoop.functions.*;

import javax.swing.*;
import java.util.*;

public class MathFunctionWindow extends JFrame {
    private JComboBox<String> functionComboBox = new JComboBox<>();
    private JLabel fromLabel = new JLabel("РћС‚:");
    private JLabel toLabel = new JLabel("Р”Рѕ:");
    private JLabel countLabel = new JLabel("РљРѕР»РёС‡РµСЃС‚РІРѕ:");
    private JTextField countField = new JTextField();
    private JTextField fromField = new JTextField();
    private JTextField toField = new JTextField();
    private JButton okButton = new JButton("OK");
    private Map<String, MathFunction> nameFunctionMap = new HashMap<>();
    TabulatedFunction function;

    public static void main() {
        MathFunctionWindow app = new MathFunctionWindow();
        app.setVisible(true);
    }

    public static void main(TabulatedFunction function) {
        MathFunctionWindow app = new MathFunctionWindow(function);
        app.setVisible(true);
    }

    public MathFunctionWindow(TabulatedFunction function) {
        super("CreateFunc");
        this.function = function;
        this.setBounds(300, 200, 500, 150);
        fillMap();
        compose();
        addButtonListeners();
    }

    public MathFunctionWindow() {
        super("CreateFunc");
        this.setBounds(300, 200, 500, 150);
        fillMap();
        compose();
        addButtonListeners();
    }

    public void fillMap() {
        nameFunctionMap.put("РљРІР°РґСЂР°С‚РёС‡РЅР°СЏ С„СѓРЅРєС†РёСЏ", new SqrFunction());
        nameFunctionMap.put("РќСѓР»РµРІР°СЏ С„СѓРЅРєС†РёСЏ", new ZeroFunction());
        nameFunctionMap.put("Р•РґРёРЅРёС‡РЅР°СЏ С„СѓРЅРєС†РёСЏ", new UnitFunction());
        nameFunctionMap.put("РљСѓР±РёС‡РµСЃРєР°СЏ С„СѓРЅРєС†РёСЏ", new Cube());
        nameFunctionMap.put("РўРѕР¶РґРµСЃС‚РІРµРЅРЅР°СЏ С„СѓРЅРєС†РёСЏ", new IdentityFunction());
        nameFunctionMap.put("Р¤СѓРЅРєС†РёСЏ РєРІР°РґСЂР°С‚РЅРѕРіРѕ РєРѕСЂРЅСЏ", new SqrtFunction());
        String[] functions = new String[6];
        int i = 0;
        for (String string : nameFunctionMap.keySet()) {
            functions[i++] = string;
        }
        Arrays.sort(functions);
        for (String string : functions) {
            functionComboBox.addItem(string);
        }
    }

    public void compose() {
        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);
        layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                .addGroup(layout.createSequentialGroup()
                        .addComponent(fromLabel)
                        .addComponent(fromField)
                        .addComponent(toLabel)
                        .addComponent(toField)
                        .addComponent(countLabel)
                        .addComponent(countField))
                .addComponent(functionComboBox)
                .addComponent(okButton)
        );
        layout.setVerticalGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(fromLabel)
                        .addComponent(fromField)
                        .addComponent(toLabel)
                        .addComponent(toField)
                        .addComponent(countLabel)
                        .addComponent(countField))
                .addComponent(functionComboBox)
                .addComponent(okButton)
        );
    }

    public void addButtonListeners() {
        okButton.addActionListener(event -> {
            try {
                String func = (String) functionComboBox.getSelectedItem();
                MathFunction selectedFunction = nameFunctionMap.get(func);
                double from = Double.parseDouble(fromField.getText());
                double to = Double.parseDouble(toField.getText());
                int count = Integer.parseInt(countField.getText());
                function = new ArrayTabulatedFunction(selectedFunction, from, to, count);
                this.dispose();
            } catch (Exception e) {
                ErrorWindow errorWindow = new ErrorWindow(this, e);
                errorWindow.showErrorWindow(this, e);
            }
        });
    }
}