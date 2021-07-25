package ru.geekbrains12.lesson4;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class ChatWindow extends JFrame {

    private final JTextField inputField = new JTextField();
    private final JTextArea chatArea = new JTextArea();

    public ChatWindow() {
        setTitle("ChatBox v 0.1");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setBounds(300, 300, 400, 200);

        //Фокус на поле ввода при открытии окна
        addWindowListener(new WindowAdapter() {
            public void windowOpened(WindowEvent e){
                inputField.requestFocus();
            }
        });

        //Верхняя панель с текстовым полем чата
        JPanel topPanel = new JPanel();
        topPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        topPanel.setLayout(new BorderLayout());
        add(topPanel, BorderLayout.CENTER);

        //Текстовое поле чата с вертикальным скроллом
        chatArea.setEditable(false);
        chatArea.setBackground(Color.WHITE);
        chatArea.setLineWrap(true);
        chatArea.setWrapStyleWord(true);
        JScrollPane scroll = new JScrollPane (chatArea);
        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        topPanel.add(scroll, BorderLayout.CENTER);

        //Нижняя панель с полем ввода и кнопкой отправки
        JPanel bottomPanel = new JPanel();
        bottomPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        bottomPanel.setLayout(new BorderLayout(10, 10));
        add(bottomPanel, BorderLayout.SOUTH);

        //Универсальный слушатель (и на кнопку и на нажатие ENTER)
        ActionListener sendListener = new SendActionListener(inputField, chatArea);

        //Поле ввода
        inputField.addActionListener(sendListener);
        bottomPanel.add(inputField, BorderLayout.CENTER);

        //Кнопка отправки
        JButton sendButton = new JButton("Send");
        sendButton.addActionListener(sendListener);
        bottomPanel.add(sendButton, BorderLayout.EAST);

        setVisible(true);
    }

}
