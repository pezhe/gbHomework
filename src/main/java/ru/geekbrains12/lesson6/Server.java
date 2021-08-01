package ru.geekbrains12.lesson6;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicBoolean;

public class Server {

    public Server(int port) {

        try {
            //Ожидание и создание соединения
            ServerSocket serverSocket = new ServerSocket(port);
            System.out.println("Waiting for connection...");
            Socket socket = serverSocket.accept();
            System.out.println(socket + " connected");
            //Получение потоков ввода-вывода
            DataInputStream input = new DataInputStream(socket.getInputStream());
            DataOutputStream output = new DataOutputStream(socket.getOutputStream());
            //Поток, слушающий входящие сообщения
            AtomicBoolean isConnected = new AtomicBoolean(true);
            new Thread(() -> {
                try {
                    String message;
                    while (!(message = input.readUTF()).equals("-d")) {
                        System.out.println("Client: " + message);
                    }
                    isConnected.set(false);
                    output.writeUTF("-d");
                    System.out.println("Client disconnected. Press ENTER to exit...");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }).start();
            //Главный цикл, отправка сообщений
            Scanner scanner = new Scanner(System.in);
            while (isConnected.get()) {
                output.writeUTF(scanner.nextLine());
            }
            System.out.println("Server is shutting down");
            socket.close();
            serverSocket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
