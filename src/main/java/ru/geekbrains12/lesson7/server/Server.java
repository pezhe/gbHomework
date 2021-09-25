package ru.geekbrains12.lesson7.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Server {

    private final List<ClientHandler> loggedClients = new ArrayList<>();
    private final AuthService authService = new AuthService();
    private ServerSocket server;

    public Server(int port) {
        Thread clientConnection = new Thread(() -> {
            try {
                server = new ServerSocket(port);
                while (true) {
                    System.out.println("Awaiting for client connection...");
                    Socket socket = server.accept();
                    System.out.println(socket + " connected");
                    new ClientHandler(this, socket);
                }
            } catch (SocketException e) {
                System.out.println("New connections are disabled");
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        clientConnection.start(); //Старт треда, подключающего клиентов
        Scanner scanner = new Scanner(System.in);
        scanner.nextLine(); //Ожидание нажатия ENTER
        //Отключение сервера
        try {
            server.close();
            clientConnection.join(); //Ожидание завершения треда, подключающего клиентов
        } catch (InterruptedException | IOException e) {
            e.printStackTrace();
        }
        broadcastMessage("Server termination. All connections are to be closed");
        System.out.println("Shutting down the server");
        broadcastMessage("-d");
    }

    public AuthService getAuthService() {
        return authService;
    }

    public synchronized void unsubscribe(ClientHandler client) {
        if (loggedClients.contains(client)) {
            loggedClients.remove(client);
            System.out.println(client.getLogin() + " logged out");
            broadcastMessage(client.getNickname() + " has left the chat");
        }
    }

    public synchronized void subscribe(ClientHandler client) {
        loggedClients.add(client);
        System.out.println(client.getLogin() + " logged in");
        broadcastMessage(client.getNickname() + " has entered the chat");
    }

    public synchronized boolean isLoggedIn(String login) {
        for (ClientHandler client : loggedClients) return client.getLogin().equals(login);
        return false;
    }

    public synchronized void broadcastMessage(String message) {
        for (ClientHandler client : loggedClients) client.sendMessage(message);
    }

    public synchronized boolean sendTargetedMessage (String targetNick, String message) {
        for (ClientHandler client : loggedClients) {
            if (client.getNickname().equals(targetNick)) {
                client.sendMessage(message);
                return true;
            }
        }
        return false;
    }

}
