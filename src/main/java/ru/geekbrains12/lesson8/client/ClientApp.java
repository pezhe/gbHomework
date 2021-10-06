package ru.geekbrains12.lesson8.client;

import ru.geekbrains13.lesson3.Logger;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.function.Consumer;

public class ClientApp {

    private final ChatWindow frame;
    private final ClientCommunicator communicator;
    private final SimpleDateFormat sdf = new SimpleDateFormat("HH.mm.ss");
    private Logger logger;

    public ClientApp() {
        communicator = new ClientCommunicator();

        Consumer<String> outboundMessageConsumer = communicator::sendMessage;

        frame = new ChatWindow(outboundMessageConsumer);

        Thread listener = new Thread(() -> {
            while (true) {
                String inboundMessage = communicator.receiveMessage();
                if (inboundMessage.equals("-d")) {
                    communicator.sendMessage("-d");
                    frame.getInboundMessageConsumer().accept("Disconnected from server. Close the window");
                    frame.deactivate();
                    break;
                }
                if (inboundMessage.startsWith("-login ")) { // Принимаем логин сессии
                    String[] command = inboundMessage.split("\\s");
                    logger = new Logger("D:/history_" + command[1] + ".txt"); // Создаём логгер
                    try {
                        String history = logger.readLog(100); // Вычитываем строку из лога
                        System.out.println(history);
                        frame.getInboundMessageConsumer().accept(history); // Пишем лог в окно
                    } catch (FileNotFoundException e) {
                        System.out.println("There's no log file for this user");
                    } catch (IOException e) {
                        System.out.println("Unknown error occurred while reading log");
                    }
                    continue;
                }
                String message = "[" + sdf.format(new Date()) + "] " + inboundMessage;
                frame.getInboundMessageConsumer().accept(message + "\n");
                if (logger != null) logger.writeLog(message); // Пишем сообщение в лог
            }
        });
        listener.start();
        try {
            listener.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        communicator.closeConnection();
    }

}
