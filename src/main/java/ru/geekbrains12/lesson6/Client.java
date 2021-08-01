package ru.geekbrains12.lesson6;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicBoolean;

public class Client {

    public Client(String host, int port) {

        try {
            //Создание соединения
            Socket socket = new Socket(host, port);
            System.out.println("Connected to server");
            //Получение потоков ввода-вывода
            DataInputStream input = new DataInputStream(socket.getInputStream());
            DataOutputStream output = new DataOutputStream(socket.getOutputStream());
            //Поток, слушающий входящие сообщения
            AtomicBoolean isConnected = new AtomicBoolean(true);
            new Thread(() -> {
                try {
                    String message;
                    while (!(message = input.readUTF()).equals("-d")) {
                        System.out.println("Server: " + message);
                    }
                    isConnected.set(false);
                    output.writeUTF("-d");
                    System.out.println("Disconnected from server. Press ENTER to exit...");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }).start();
            //Главный цикл, отправка сообщений
            Scanner scanner = new Scanner(System.in);
            while (isConnected.get()) {
                output.writeUTF(scanner.nextLine());
            }
            System.out.println("Client is shutting down");
            socket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
