package ru.geekbrains11.lesson8;

import javax.swing.*;
import java.awt.*;

public class CalculatorFrame extends JFrame {
    private JTextField inputArea;

    public CalculatorFrame() {
        setTitle("Calculator v2.0");
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
        bottom.setLayout(new GridLayout(5, 4));

        for (int i = 0; i < 10; i++) {
            JButton btn = new JButton(String.valueOf(i));
            btn.addActionListener(e -> inputArea.setText(inputArea.getText() + btn.getText()));
            bottom.add(btn);
        }

        JButton leftBracket = new JButton("(");
        leftBracket.addActionListener(e -> inputArea.setText(inputArea.getText() + "("));
        bottom.add(leftBracket);

        JButton rightBracket = new JButton(")");
        rightBracket.addActionListener(e -> inputArea.setText(inputArea.getText() + ")"));
        bottom.add(rightBracket);

        JButton plus = new JButton("+");
        plus.addActionListener(e -> inputArea.setText(inputArea.getText() + "+"));
        bottom.add(plus);

        JButton minus = new JButton("-");
        minus.addActionListener(e -> inputArea.setText(inputArea.getText() + "-"));
        bottom.add(minus);

        JButton multiply = new JButton("*");
        multiply.addActionListener(e -> inputArea.setText(inputArea.getText() + "*"));
        bottom.add(multiply);

        JButton divide = new JButton("/");
        divide.addActionListener(e -> inputArea.setText(inputArea.getText() + "/"));
        bottom.add(divide);

        JButton clear = new JButton("C");
        clear.addActionListener(e -> inputArea.setText(""));
        bottom.add(clear);

        JButton dot = new JButton(".");
        dot.addActionListener(e -> inputArea.setText(inputArea.getText() + "."));
        bottom.add(dot);

        JButton squareRoot = new JButton("sqrt");
        squareRoot.addActionListener(new SqrtButtonActionListener(inputArea));
        bottom.add(squareRoot);

        JButton calc = new JButton("=");
        calc.addActionListener(new CalcButtonActionListener(inputArea));
        bottom.add(calc);

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
