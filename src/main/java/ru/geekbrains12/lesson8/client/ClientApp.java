package ru.geekbrains12.lesson8.client;

import java.util.function.Consumer;

public class ClientApp {

    private final ChatWindow frame;
    private final ClientCommunicator communicator;

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
                frame.getInboundMessageConsumer().accept(inboundMessage);
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
