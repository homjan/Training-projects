package com.javarush.task.task30.task3008;

import com.sun.javafx.collections.MappingChange;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Server {

    private static Map<String, Connection> connectionMap = new ConcurrentHashMap<>();

    public static void sendBroadcastMessage(Message message) {
        for (String name : connectionMap.keySet()) {
            try {
                connectionMap.get(name).send(message);
            } catch (IOException e) {
                ConsoleHelper.writeMessage(String.format("Can't send the message to %s", name));
            }
        }
    }

    private static class Handler extends Thread {
        public Handler(Socket socket) {
            this.socket = socket;
        }

        private Socket socket;

        private String serverHandshake(Connection connection) throws IOException, ClassNotFoundException {
            Message m1;
            while (true) {
                connection.send(new Message(MessageType.NAME_REQUEST));

                m1 = connection.receive();
                if (m1.getType() == MessageType.USER_NAME && !m1.getData().isEmpty() && !connectionMap.containsKey(m1.getData())) {
                    connectionMap.put(m1.getData(), connection);
                    connection.send(new Message(MessageType.NAME_ACCEPTED));
                    break;

                }

            }
            return m1.getData();
        }

        private void notifyUsers(Connection connection, String userName) throws IOException {

            for (String name : connectionMap.keySet()) {
                if (!name.equals(userName)) {

                    connection.send(new Message(MessageType.USER_ADDED, name));
                }
            }

        }




        private void serverMainLoop(Connection connection, String userName) throws IOException, ClassNotFoundException {
            Message m3;
            String text_message;
            while (true) {
                m3 = connection.receive();
                if (m3.getType() == MessageType.TEXT) {
                    text_message = userName + ": " + m3.getData();
                    Server.sendBroadcastMessage(new Message(MessageType.TEXT, text_message));
                } else {
                    ConsoleHelper.writeMessage("Error!");
                }

            }

        }

        public void run() {
            ConsoleHelper.writeMessage(socket.getRemoteSocketAddress().toString());
            String nameUser = null;
            try {
                Connection connection = new Connection(socket);
                try {
                    nameUser = serverHandshake(connection);
                    sendBroadcastMessage(new Message(MessageType.USER_ADDED, nameUser));
                    notifyUsers(connection, nameUser);
                    serverMainLoop(connection, nameUser);
                } catch (ClassNotFoundException e) {
                    e.printStackTrace( );
                    ConsoleHelper.writeMessage("ошибка при обмене данными с удаленным адресом");
                }
            } catch (Exception e) {
                e.printStackTrace( );
                ConsoleHelper.writeMessage("ошибка при обмене данными с удаленным адресом");
            }
            finally {
                if (nameUser != null){
                    connectionMap.remove(nameUser);
                    sendBroadcastMessage(new Message(MessageType.USER_REMOVED,nameUser));
                }
            }
            ConsoleHelper.writeMessage("соединение с удаленным адресом закрыто");


        }


    }

    public static void main(String[] args) throws IOException {

        int n = ConsoleHelper.readInt();
        ServerSocket serverSocket = new ServerSocket(n);
        System.out.println("Server started..");

        try {
            int portNumber = ConsoleHelper.readInt();
            serverSocket = new ServerSocket(portNumber);
            System.out.printf("Сервер запущен на порту %d", portNumber);

            while (true) {
                Socket socket = serverSocket.accept();
                Handler handler = new Handler(socket);
                handler.start();
            }
        } catch (IOException e) {
            System.out.println("Произошла ошибка.");
            serverSocket.close();
        }
    }


}

