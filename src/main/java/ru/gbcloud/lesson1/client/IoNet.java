package ru.gbcloud.lesson1.client;

import java.io.*;
import java.net.Socket;

public class IoNet implements Closeable {

    private final Callback callback;
    private final Socket socket;
    private final DataInputStream is;
    private final DataOutputStream os;

    public IoNet(Callback callback,
                 Socket socket) throws IOException {
        this.callback = callback;
        this.socket = socket;
        is = new DataInputStream(socket.getInputStream());
        os = new DataOutputStream(socket.getOutputStream());
        Thread readThread = new Thread(this::readMessages);
        readThread.setDaemon(true);
        readThread.start();
    }

    private void readMessages() {
        try {
            while (true) {
                String msg = is.readUTF();
                callback.onReceive(msg);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void writeUtf(String msg) throws IOException {
        os.writeUTF(msg);
        os.flush();
    }

    public void writeLong(long size) throws IOException {
        os.writeLong(size);
        os.flush();
    }

    public void writeBytes(byte[] bytes, int off, int cnt) throws IOException {
        os.write(bytes, off, cnt);
        os.flush();
    }

    @Override
    public void close() throws IOException {
        os.close();
        is.close();
        socket.close();
    }
}
