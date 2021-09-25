package ru.geekbrains12.lesson7.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.SocketTimeoutException;

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
                    if(authorize()) listenMessages();
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

    public boolean authorize() throws IOException {
        socket.setSoTimeout(120000);
        try {
            for (int i = 0; i < 3; i++) {
                sendMessage("Please log in. Type: -a [login] [password]. Number of attempts left: " + (3 - i));
                String strFromClient = input.readUTF();
                if (strFromClient.equals("-d")) {
                    sendMessage("-d");
                    return false;
                }
                if (strFromClient.startsWith("-a ")) {
                    String[] credentials = strFromClient.split("\\s");
                    if (credentials.length == 3) {
                        if (server.getAuthService().checkCredentials(credentials[1], credentials[2])) {
                            if (!server.isLoggedIn(credentials[1])) {
                                sendMessage("You have successfully logged in. Welcome");
                                login = credentials[1];
                                nickname = server.getAuthService().getNickname(login);
                                server.subscribe(this);
                                socket.setSoTimeout(0);
                                return true;
                            } else {
                                sendMessage("User " + credentials[1] + " is already logged in");
                            }
                        } else {
                            sendMessage("Wrong credentials");
                        }
                    } else {
                        sendMessage("Unknown command");
                    }
                } else {
                    sendMessage("Unknown command");
                }
            }
        } catch (SocketTimeoutException e) {
            sendMessage("Login time exceeded. Disconnected from server");
            sendMessage("-d");
            return false;
        }
        sendMessage("Number of login attempts exceeded. Disconnected from server");
        sendMessage("-d");
        return false;
    }

    public void listenMessages() throws IOException {
        String strFromClient;
        while (true) {
            strFromClient = input.readUTF();
            if (strFromClient.equals("-d")) {
                sendMessage("-d");
                return;
            }
            if (strFromClient.startsWith("/w ")) { // Обработка персональных сообщений
                String[] command = strFromClient.split("\\s");
                if (command.length >= 3) {
                    String targetNick = command[1];
                    String message = strFromClient.substring(strFromClient.indexOf(command[2]));
                    if (!server.sendTargetedMessage(targetNick, nickname + ": " + message)) {
                        sendMessage("User not found");
                    }
                } else {
                    sendMessage("Unknown command");
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
        System.out.println("Closing connection to" + socket);
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
