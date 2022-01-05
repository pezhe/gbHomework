package ru.gbcloud.lesson1.server;

import java.io.*;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Handler implements Runnable {

    private static final int SIZE = 8192;
    private final Path serverDir;
    private boolean running;
    private final byte[] buf;
    private final DataInputStream is;
    private final DataOutputStream os;
    private final Socket socket;

    public Handler(Socket socket) throws IOException {
        running = true;
        buf = new byte[8192];
        this.socket = socket;
        serverDir = Paths.get("D:\\Проекты\\GB Java course\\java 2\\java 2 lesson 1\\server");
        is = new DataInputStream(socket.getInputStream());
        os = new DataOutputStream(socket.getOutputStream());
    }

    public void stop() {
        running = false;
    }

    @Override
    public void run() {
        try {
            while (running) {
                // вкрутить логику с получением файла от клиента
                String command = is.readUTF();
                if (command.equals("quit")) {
                    os.write("Client disconnected\n".getBytes(StandardCharsets.UTF_8));
                    close();
                    break;
                } else if (command.equals("#file#")) {
                    String fileName = is.readUTF();
                    long size = is.readLong();
                    try (FileOutputStream fos = new FileOutputStream(
                            serverDir.resolve(fileName).toFile())) {
                        os.writeUTF("File " + fileName + " created");
                        for (long i = 0, n = (size + SIZE - 1) / SIZE; i < n; i++) {
                            int read = is.read(buf);
                            fos.write(buf, 0 , read);
                            os.writeUTF("Uploaded " + (i + 1) + " batch");
                        }
                    }
                    os.writeUTF("File successfully uploaded.");
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void close() throws IOException {
        os.close();
        is.close();
        socket.close();
    }
}
