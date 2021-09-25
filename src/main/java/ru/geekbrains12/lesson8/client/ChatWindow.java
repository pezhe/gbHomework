package ru.geekbrains12.lesson8.client;
// Доработанное окно чата, разработанное мной в ДЗ 4 урока. Оно мне нравится.
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.function.Consumer;

public class ChatWindow extends JFrame {

    private final JTextField inputField = new JTextField();
    private final JTextArea chatArea = new JTextArea();
    private final Consumer<String> inboundMessageConsumer;
    private final Consumer<String> outboundMessageConsumer;
    private final ActionListener sendListener;
    private final JButton sendButton;
    private final SimpleDateFormat sdf = new SimpleDateFormat("HH.mm.ss");

    public ChatWindow(Consumer<String> outboundMessageConsumer) {
        //Связь окна с клиентским приложением
        this.outboundMessageConsumer = outboundMessageConsumer;
        inboundMessageConsumer = createInboundMessageConsumer();

        setTitle("ChatBox v 0.1");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setBounds(300, 300, 500, 200);

        //Адаптер для выполнения операций при открытии и закрытии окна
        MyWindowAdapter adapter = new MyWindowAdapter();
        addWindowListener(adapter);

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
        sendListener = new SendActionListener(inputField, chatArea, this.outboundMessageConsumer);

        //Поле ввода
        inputField.addActionListener(sendListener);
        bottomPanel.add(inputField, BorderLayout.CENTER);

        //Кнопка отправки
        sendButton = new JButton("Send");
        sendButton.addActionListener(sendListener);
        bottomPanel.add(sendButton, BorderLayout.EAST);

        setVisible(true);
    }

    private Consumer<String> createInboundMessageConsumer() {
        return inboundMessage -> {
            String timeStamp = sdf.format(new Date());
            chatArea.append("[" + timeStamp + "] " + inboundMessage + "\n");
            chatArea.setCaretPosition(chatArea.getDocument().getLength());
        };
    }

    public Consumer<String> getInboundMessageConsumer() {
        return inboundMessageConsumer;
    }
    //Деактивация окна после дисконнекта со стороны сервера
    public void deactivate() {
        inputField.setEditable(false);
        inputField.removeActionListener(sendListener);
        sendButton.removeActionListener(sendListener);
    }

    public class MyWindowAdapter extends WindowAdapter {
        //Дисконнект при закрытии окна
        @Override
        public void windowClosing(WindowEvent e) {
            outboundMessageConsumer.accept("-d");
        }
        //Установка курсора в поле ввода при открытии окна
        @Override
        public void windowOpened(WindowEvent e) {
            inputField.requestFocus();
        }

    }

}
