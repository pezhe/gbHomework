package ru.geekbrains.lesson8;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CalculatorFrame extends JFrame {
    private JTextField inputArea;

    public CalculatorFrame() {
        setTitle("Calculator v1.0");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        setBounds(100, 100, 300, 500);

        setJMenuBar(createMenuBar());

        setLayout(new BorderLayout());

        add(createTopPanel(), BorderLayout.NORTH);
        add(createBottomPanel(), BorderLayout.CENTER);

        setVisible(true);
    }

    private JPanel createTopPanel() {
        JPanel top = new JPanel();
        top.setLayout(new BorderLayout());
        inputArea = new JTextField();
        inputArea.setEditable(false);
        top.add(inputArea, BorderLayout.CENTER);

        return top;
    }

    private JPanel createBottomPanel() {
        JPanel bottom = new JPanel();
        bottom.setLayout(new GridLayout(5, 3));

        DigitButtonActionListener digitButtonActionListener = new DigitButtonActionListener(inputArea);

        for (int i = 0; i < 10; i++) {
            JButton btn = new JButton(String.valueOf(i));
            btn.addActionListener(digitButtonActionListener);
            bottom.add(btn);
        }

        JButton clear = new JButton("C");
        clear.addActionListener(e -> inputArea.setText(""));
        bottom.add(clear);

        JButton calc = new JButton("=");
        calc.addActionListener(new CalcButtonActionListener(inputArea));
        bottom.add(calc);

        JButton plus = new JButton("+");
        plus.addActionListener(e -> inputArea.setText(inputArea.getText() + "+"));
        bottom.add(plus);

        return bottom;
    }

    private JMenuBar createMenuBar() {
        JMenuBar menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("File");

        JMenuItem openItem = new JMenu("Open");
        fileMenu.add(openItem);

        JMenuItem exitItem = new JMenu("Exit");
        fileMenu.add(exitItem);

        menuBar.add(fileMenu);

        return menuBar;
    }
}
