package com.javarush.test.level30.lesson15.big01;


import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by MVTitov on 26.08.2016.
 */
public class Server {
    private static Map<String, Connection> connectionMap = new ConcurrentHashMap<>();

    public static void sendBroadcastMessage(Message message) {
        for (Connection connection : connectionMap.values()) {
            try {
                connection.send(message);
            } catch (IOException e) {
                ConsoleHelper.writeMessage("Сообщение не отправлено");
            }
        }
    }

    private static class Handler extends Thread {
        private Socket socket;

        public Handler(Socket socket) {
            this.socket = socket;
        }

        public void run() {
            ConsoleHelper.writeMessage("Connection established. IP: "+socket.getRemoteSocketAddress());
            String userName = null;
            try (Connection connection = new Connection(socket)) {
                userName = serverHandshake(connection);
                sendBroadcastMessage(new Message(MessageType.USER_ADDED,userName));
                sendListOfUsers(connection,userName);
                serverMainLoop(connection,userName);
            } catch (IOException e) {
                ConsoleHelper.writeMessage("Error with IP: "+socket.getRemoteSocketAddress());
            } catch (ClassNotFoundException e) {
                ConsoleHelper.writeMessage("Error with IP: "+socket.getRemoteSocketAddress());
            }
            if (userName!=null) {
                connectionMap.remove(userName);
                sendBroadcastMessage(new Message(MessageType.USER_REMOVED,userName));
            }
            ConsoleHelper.writeMessage("Cоединение с удаленным адресом закрыто");
        }

        private String serverHandshake(Connection connection) throws IOException, ClassNotFoundException {
            while (true) {
                connection.send(new Message(MessageType.NAME_REQUEST));
                Message message = connection.receive();
                if (message == null || message.getType() != MessageType.USER_NAME) continue;
                String userName = message.getData();
                if (userName == null || userName.isEmpty() || connectionMap.containsKey(userName)) continue;
                connectionMap.put(userName, connection);
                connection.send(new Message(MessageType.NAME_ACCEPTED));
                return userName;
            }
        }

        private void sendListOfUsers(Connection connection, String userName) throws IOException {
            for (String s : connectionMap.keySet()) {
                if (!s.equals(userName)) connection.send(new Message(MessageType.USER_ADDED,s));
            }
        }
        private void serverMainLoop(Connection connection, String userName) throws IOException, ClassNotFoundException {
            while (true) {
                Message message = connection.receive();
                if (message.getType()==MessageType.TEXT) sendBroadcastMessage(new Message(MessageType.TEXT,userName+": "+message.getData()));
                else ConsoleHelper.writeMessage("Ошибка. Сообщение не текстовое");
            }
        }
    }

    public static void main(String[] args) {
        ConsoleHelper.writeMessage("Введите порт сервера:");
        ServerSocket sSocket = null;
        try {
            sSocket = new ServerSocket(ConsoleHelper.readInt());
            ConsoleHelper.writeMessage("Сервер запущен");
            while (true) {
                new Handler(sSocket.accept()).start();
            }
        } catch (Exception e) {
            ConsoleHelper.writeMessage("Ошибка сервера");
        } finally {
            try {
                sSocket.close();
            } catch (IOException e) {
            }
        }
    }
}
