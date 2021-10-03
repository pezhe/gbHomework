package ru.geekbrains11.lesson8;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SqrtButtonActionListener implements ActionListener {
    private final JTextField textField;

    public SqrtButtonActionListener(JTextField textField) {
        this.textField = textField;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        textField.setText(Calculator.calculateSqrt(textField.getText()));
    }
}
