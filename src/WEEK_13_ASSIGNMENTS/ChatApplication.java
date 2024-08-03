package WEEK_13_ASSIGNMENTS;

import java.io.*;
import java.net.*;
import java.util.*;
import java.util.concurrent.*;

public class ChatApplication {
    private static final int PORT = 5000;
    private static final String SERVER_ADDRESS = "localhost";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter 's' for server or 'c' for client: ");
        String choice = scanner.nextLine().trim().toLowerCase();

        try {
            if (choice.equals("s")) {
                runServer();
            } else if (choice.equals("c")) {
                runClient();
            } else {
                System.out.println("Invalid choice. Please run the program again.");
            }
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
    }

    private static void runServer() {
        Set<PrintWriter> clients = ConcurrentHashMap.newKeySet();
        int userIdCounter = 1;

        System.out.println("Chat Server is running...");
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            while (true) {
                new ServerClientHandler(serverSocket.accept(), clients, userIdCounter++).start();
            }
        } catch (IOException e) {
            System.out.println("Server error: " + e.getMessage());
        }
    }

    private static void runClient() {
        try (Socket socket = new Socket(SERVER_ADDRESS, PORT);
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
             Scanner scanner = new Scanner(System.in)) {

            System.out.println("Connected to the chat server");

            new Thread(() -> {
                try {
                    String message;
                    while ((message = in.readLine()) != null) {
                        System.out.println(message);
                    }
                } catch (IOException e) {
                    System.out.println("Disconnected from the server");
                }
            }).start();

            while (true) {
                String message = scanner.nextLine();
                out.println(message);
                if (message.equalsIgnoreCase("exit")) {
                    break;
                }
            }
        } catch (IOException e) {
            System.out.println("Client error: " + e.getMessage());
        }
    }

    private static class ServerClientHandler extends Thread {
        private final Socket socket;
        private PrintWriter out;
        private BufferedReader in;
        private final int userId;
        private final Set<PrintWriter> clients;

        public ServerClientHandler(Socket socket, Set<PrintWriter> clients, int userId) {
            this.socket = socket;
            this.clients = clients;
            this.userId = userId;
        }

        public void run() {
            try {
                in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                out = new PrintWriter(socket.getOutputStream(), true);

                clients.add(out);
                broadcast("User " + userId + " has joined the chat.");

                String message;
                while ((message = in.readLine()) != null) {
                    broadcast("User " + userId + ": " + message);
                }
            } catch (IOException e) {
                System.out.println("User " + userId + " connection error: " + e.getMessage());
            } finally {
                cleanup();
            }
        }

        private void broadcast(String message) {
            synchronized (clients) {
                for (PrintWriter client : clients) {
                    client.println(message);
                }
            }
        }

        private void cleanup() {
            if (out != null) {
                clients.remove(out);
            }
            broadcast("User " + userId + " has left the chat.");
            try {
                if (socket != null && !socket.isClosed()) {
                    socket.close();
                }
            } catch (IOException e) {
                System.out.println("Error closing socket for user " + userId + ": " + e.getMessage());
            }
        }
    }
}
