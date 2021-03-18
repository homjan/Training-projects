package com.javarush.task.task30.task3008.client;

import com.javarush.task.task30.task3008.Connection;
import com.javarush.task.task30.task3008.ConsoleHelper;
import com.javarush.task.task30.task3008.Message;
import com.javarush.task.task30.task3008.MessageType;

import java.io.IOException;
import java.net.Socket;

public class Client {

    protected Connection connection;
    private volatile boolean clientConnected = false;

    public class SocketThread extends Thread {

        protected void processIncomingMessage(String message) {
            ConsoleHelper.writeMessage(message);
            //  System.out.println(message);
        }

        protected void informAboutAddingNewUser(String userName) {
            ConsoleHelper.writeMessage(userName + " присоеденился к чату");
        }

        protected void informAboutDeletingNewUser(String userName) {
            ConsoleHelper.writeMessage(userName + " покинул чат");
        }

        protected void notifyConnectionStatusChanged(boolean clientConnected) {

            Client.this.clientConnected = clientConnected;

            synchronized (Client.this) {
                Client.this.notify();
            }

        }

        protected void clientHandshake() throws IOException, ClassNotFoundException {

            while (true) {
                Message message = connection.receive();
                if (message.getType() == MessageType.NAME_REQUEST) {
                    getUserName();
                    Message m2 = new Message(MessageType.USER_NAME, getUserName());
                    connection.send(m2);
                } else if (message.getType() == MessageType.NAME_ACCEPTED) {
                    notifyConnectionStatusChanged(true);
                    break;
                } else {
                    throw new IOException("Unexpected MessageType");
                }


            }
        }

        protected void clientMainLoop() throws IOException, ClassNotFoundException {

            while (true) {
                Message message = connection.receive();
                if (message.getType() == MessageType.TEXT) {
                    processIncomingMessage(message.getData());

                } else if (message.getType() == MessageType.USER_ADDED) {
                    informAboutAddingNewUser(message.getData());
                } else if (message.getType() == MessageType.USER_REMOVED) {
                    informAboutDeletingNewUser(message.getData());
                } else {
                    throw new IOException("Unexpected MessageType");
                }


            }

        }


        public void run()  {
            String adressServer = getServerAddress();
            int port = getServerPort();

            Socket socket = null;

            try {
                socket = new Socket(adressServer, port);
                Connection connection = new Connection(socket);
                Client.this.connection = connection;

                clientHandshake();
                clientMainLoop();
            } catch (ClassNotFoundException e) {
                    e.printStackTrace( );
                    notifyConnectionStatusChanged(false);
                } catch (IOException e) {
                e.printStackTrace( );
                notifyConnectionStatusChanged(false);
            }
        }


    }

    protected String getServerAddress() {
        return ConsoleHelper.readString();
    }

    protected int getServerPort() {
        return ConsoleHelper.readInt();
    }

    protected String getUserName() {
        return ConsoleHelper.readString();
    }

    protected boolean shouldSendTextFromConsole() {
        return true;
    }

    protected SocketThread getSocketThread() {
        return new SocketThread();
    }

    protected void sendTextMessage(String text) {
        try {
            connection.send(new Message(MessageType.TEXT, text));
        } catch (IOException e) {
            clientConnected = false;
        }
    }

    public void run() {
//        SocketThread thread = client.getSocketThread();
        SocketThread thread = getSocketThread();
        thread.setDaemon(true);
        thread.start();

        try {
            synchronized (this) {
                this.wait();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
            System.out.println("При работе клиента возникла ошибка");
        }

        if (shouldSendTextFromConsole()) {

            if (clientConnected) sendTextMessage("Соединение установлено. Для выхода наберите команду ‘exit’.");
            else System.out.println("Произошла ошибка во время работы клиента.");
        }

        while (clientConnected) {
            String line = ConsoleHelper.readString();
            if (shouldSendTextFromConsole()) sendTextMessage(line);
            if (line == "exit") clientConnected = false;
            // if (line.equals("exit")) break;
            // if (shouldSendTextFromConsole()) sendTextMessage(line);
        }


    }

    public static void main(String[] args) {
        //new Client().run();
        Client client = new Client();
        client.run();
    }
}
