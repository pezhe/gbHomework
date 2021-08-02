package ru.geekbrains12.lesson7.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class ClientHandler {
    private final Server server;
    private final Socket socket;
    private final DataInputStream input;
    private final DataOutputStream output;

    private String login;
    private String nickname;

    public String getLogin() {
        return login;
    }

    public String getNickname() {
        return nickname;
    }

    public ClientHandler(Server server, Socket socket) {
        try {
            this.server = server;
            this.socket = socket;
            this.input = new DataInputStream(socket.getInputStream());
            this.output = new DataOutputStream(socket.getOutputStream());
            this.login = "";
            this.nickname = "";
            new Thread(() -> {
                try {
                    authorize();
                    readMessages();
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    closeConnection();
                }
            }).start();
        } catch (IOException e) {
            throw new RuntimeException("Error during establishing client connection", e);
        }
    }

    public void authorize() throws IOException {
        while (true) {
            sendMessage("Please log in. Type: -a [login] [password]");
            String str = input.readUTF();
            if (str.startsWith("-a ")) {
                String[] credentials = str.split("\\s");
                if (server.getAuthService().checkCredentials(credentials[1], credentials[2])) {
                    if (!server.isLoggedIn(credentials[1])) {
                        sendMessage("You have successfully logged in. Welcome");
                        login = credentials[1];
                        nickname = server.getAuthService().getNickname(login);
                        server.subscribe(this);
                        server.broadcastMessage(nickname + " has entered the chat");
                        return;
                    } else {
                        sendMessage("User " + credentials[1] + " is already logged in");
                    }
                } else {
                    sendMessage("Wrong login/password");
                }
            }
        }
    }

    public void readMessages() throws IOException {
        String strFromClient;
        while (true) {
            strFromClient = input.readUTF();
            if (strFromClient.equals("-d")) {
                output.writeUTF("-d");
                return;
            }
            if (strFromClient.startsWith("/w ")) { // Обработка персональных сообщений
                int messagePosition = strFromClient.indexOf(" ",3);
                String targetNick = strFromClient.substring(3, messagePosition);
                String message = strFromClient.substring(messagePosition + 1);
                if (!server.sendTargetedMessage(targetNick, nickname + ": " + message)) {
                    output.writeUTF("User not found");
                }
                continue;
            }
            server.broadcastMessage(nickname + ": " + strFromClient);
        }
    }

    public void sendMessage(String message) {
        try {
            output.writeUTF(message);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void closeConnection() {
        server.unsubscribe(this);
        server.broadcastMessage(nickname + " has left the chat");
        try {
            input.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            output.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
