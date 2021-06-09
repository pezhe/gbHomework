package ru.geekbrains.lesson8;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CalcButtonActionListener implements ActionListener {
    private final JTextField textField;

    public CalcButtonActionListener(JTextField textField) {
        this.textField = textField;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String[] operands = textField.getText().split("\\+");
        int sum = 0;
        for (int i = 0; i < operands.length; i++) {
            sum += Integer.parseInt(operands[i]);
        }
        textField.setText(String.valueOf(sum));
    }
}
