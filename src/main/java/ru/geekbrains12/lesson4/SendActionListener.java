package ru.geekbrains12.lesson4;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SendActionListener implements ActionListener {

    private final JTextField inputField;
    private final JTextArea chatArea;
    private final SimpleDateFormat sdf = new SimpleDateFormat("HH.mm.ss");

    public SendActionListener (JTextField inputField, JTextArea chatArea) {
        this.inputField = inputField;
        this.chatArea = chatArea;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (!inputField.getText().equals("")) {
            String timeStamp = sdf.format(new Date());
            chatArea.append("[" + timeStamp + "] Я: " + inputField.getText() + "\n");
            chatArea.setCaretPosition(chatArea.getDocument().getLength());
            inputField.setText("");
        }
        inputField.requestFocus();
    }
}
