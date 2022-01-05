package ru.gbcloud.lesson2;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.Set;
import java.util.stream.Collectors;

public class NioServer {

    private final ServerSocketChannel serverChannel;
    private final Selector selector;
    private final ByteBuffer buf;
    private Path currentDir;

    public NioServer(int port) throws IOException {
        currentDir = Paths.get("D:\\Проекты\\GB Java course\\java 2\\");
        buf = ByteBuffer.allocate(16);
        serverChannel = ServerSocketChannel.open();
        selector = Selector.open();
        serverChannel.bind(new InetSocketAddress(port));
        serverChannel.configureBlocking(false);
        serverChannel.register(selector, SelectionKey.OP_ACCEPT);

        while (serverChannel.isOpen()) {
            selector.select();
            Set<SelectionKey> keys = selector.selectedKeys();
            Iterator<SelectionKey> iterator = keys.iterator();
            try {
                while (iterator.hasNext()) {
                    SelectionKey key = iterator.next();
                    if (key.isAcceptable()) {
                        handleAccept();
                    }
                    if (key.isReadable()) {
                        handleRead(key);
                    }
                    iterator.remove();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) throws IOException {
        new NioServer(8189);
    }

    private void handleAccept() throws IOException {
        System.out.println("Client accepted...");
        SocketChannel socketChannel = serverChannel.accept();
        socketChannel.configureBlocking(false);
        socketChannel.register(selector, SelectionKey.OP_READ);
        socketChannel.write(ByteBuffer.wrap((
                "Welcome to pezhe terminal\n\rpezhe -> "
        ).getBytes(StandardCharsets.UTF_8)));
    }

    private void handleRead(SelectionKey key) throws IOException {
        SocketChannel channel = (SocketChannel) key.channel();
        StringBuilder msg = new StringBuilder();
        while (true) {
            int read = channel.read(buf);
            if (read == -1) {
                channel.close();
                return;
            }
            if (read == 0) {
                break;
            }
            buf.flip();
            while (buf.hasRemaining()) {
                msg.append((char) buf.get());
            }
            buf.clear();
        }
        processMessage(channel, msg.toString().trim());
    }

    private void processMessage(SocketChannel channel, String msg) throws IOException {
        String[] tokens = msg.split(" +");
        TerminalCommandType type;
        try {
            type = TerminalCommandType.byCommand(tokens[0]);
            switch (type) {
                case LS:
                    sendString(channel, getFilesList());
                    break;
                case CAT:
                    processCatCommand(channel, tokens);
                    break;
                case CD:
                    processCdCommand(channel, tokens);
                    break;
                case MKDIR:
                    createDirectory(channel, tokens);
                    break;
                case TOUCH:
                    createFile(channel, tokens);
            }
        } catch (RuntimeException e) {
            String response = "Command " + tokens[0] + " does not exist!\n\r";
            sendString(channel, response);
        }
    }

    private void createDirectory(SocketChannel channel, String[] tokens) throws IOException {
        if (tokens == null || tokens.length != 2) {
            sendString(channel, "Command MKDIR should have 2 args");
        } else {
            String dir = tokens[1];
            Files.createDirectory(currentDir.resolve(dir));
            channel.write(ByteBuffer.wrap("pezhe -> ".getBytes(StandardCharsets.UTF_8)));
        }
    }

    private void createFile(SocketChannel channel, String[] tokens) throws IOException {
        if (tokens == null || tokens.length != 2) {
            sendString(channel, "Command MKDIR should have 2 args");
        } else {
            String dir = tokens[1];
            Files.createFile(currentDir.resolve(dir));
            channel.write(ByteBuffer.wrap("pezhe -> ".getBytes(StandardCharsets.UTF_8)));
        }
    }

    private void processCdCommand(SocketChannel channel, String[] tokens) throws IOException {
        if (tokens == null || tokens.length != 2) {
            sendString(channel, "Command cat should have 2 args");
        } else {
            String dir = tokens[1];
            if (Files.isDirectory(currentDir.resolve(dir))) {
                currentDir = currentDir.resolve(dir);
                channel.write(ByteBuffer.wrap("pezhe -> ".getBytes(StandardCharsets.UTF_8)));
            } else {
                sendString(channel, "You cannot use cd command to FILE\n\r");
            }
        }
    }

    private void processCatCommand(SocketChannel channel, String[] tokens) throws IOException {
        if (tokens == null || tokens.length != 2) {
            sendString(channel, "Command cat should have 2 args");
        } else {
            String fileName = tokens[1];
            Path file = currentDir.resolve(fileName);
            if (!Files.isDirectory(file)) {
                String content = new String(Files.readAllBytes(file)) + "\n\r";
                sendString(channel, content);
            } else {
                sendString(channel, "You cannot use cat command to DIR\n\r");
            }
        }
    }

    private String getFilesList() throws IOException {
        return Files.list(currentDir)
                .map(p -> p.getFileName().toString() + " " + getFileSuffix(p))
                .collect(Collectors.joining("\n\r")) + "\n\r";
    }

    private String getFileSuffix(Path path) {
        if (Files.isDirectory(path)) {
            return "[DIR]";
        } else {
            return "[FILE] " + path.toFile().length() + " bytes";
        }
    }

    private void sendString(SocketChannel channel, String msg) throws IOException {
        channel.write(ByteBuffer.wrap(msg.getBytes(StandardCharsets.UTF_8)));
        channel.write(ByteBuffer.wrap("pezhe -> ".getBytes(StandardCharsets.UTF_8)));
    }

}
