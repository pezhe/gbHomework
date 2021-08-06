package ru.geekbrains12.lesson8.client;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.function.Consumer;

public class SendActionListener implements ActionListener {

    private final JTextField inputField;
    private final JTextArea chatArea;
    private final Consumer<String> outboundMessageConsumer;

    public SendActionListener (JTextField inputField, JTextArea chatArea, Consumer<String> outboundMessageConsumer) {
        this.inputField = inputField;
        this.chatArea = chatArea;
        this.outboundMessageConsumer = outboundMessageConsumer;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (!inputField.getText().equals("")) {
            outboundMessageConsumer.accept(inputField.getText());
            chatArea.setCaretPosition(chatArea.getDocument().getLength());
            inputField.setText("");
        }
        inputField.requestFocus();
    }
}
