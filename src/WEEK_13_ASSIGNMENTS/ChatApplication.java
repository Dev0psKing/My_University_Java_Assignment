package WEEK_13_ASSIGNMENTS;

import java.io.*;
import java.net.*;
import java.util.*;

public class ChatApplication {
    private static final int PORT = 5000;
    private static final String SERVER_ADDRESS = "localhost";

    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter 's' for server or 'c' for client: ");
        String choice = scanner.nextLine().trim().toLowerCase();

        if (choice.equals("s")) {
            runServer();
        } else if (choice.equals("c")) {
            runClient();
        } else {
            System.out.println("Invalid choice. Please run the program again.");
        }
    }

    private static void runServer() throws Exception {
        Set<PrintWriter> clients = new HashSet<>();
        int userIdCounter = 1;

        System.out.println("Chat Server is running...");
        ServerSocket serverSocket = new ServerSocket(PORT);

        while (true) {
            new ServerClientHandler(serverSocket.accept(), clients, userIdCounter++).start();
        }
    }

    private static void runClient() throws IOException {
        Socket socket = new Socket(SERVER_ADDRESS, PORT);
        System.out.println("Connected to the chat server");

        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

        Scanner scanner = new Scanner(System.in);

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

        socket.close();
        scanner.close();
    }

    private static class ServerClientHandler extends Thread {
        private Socket socket;
        private PrintWriter out;
        private BufferedReader in;
        private int userId;
        private Set<PrintWriter> clients;

        public ServerClientHandler(Socket socket, Set<PrintWriter> clients, int userId) {
            this.socket = socket;
            this.clients = clients;
            this.userId = userId;
        }

        public void run() {
            try {
                in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                out = new PrintWriter(socket.getOutputStream(), true);

                synchronized (clients) {
                    clients.add(out);
                }

                broadcast("User " + userId + " has joined the chat.");

                String message;
                while ((message = in.readLine()) != null) {
                    broadcast("User " + userId + ": " + message);
                }
            } catch (IOException e) {
                System.out.println(e);
            } finally {
                if (out != null) {
                    synchronized (clients) {
                        clients.remove(out);
                    }
                }
                broadcast("User " + userId + " has left the chat.");
                try {
                    socket.close();
                } catch (IOException e) {
                    System.out.println(e);
                }
            }
        }

        private void broadcast(String message) {
            synchronized (clients) {
                for (PrintWriter client : clients) {
                    client.println(message);
                }
            }
        }
    }
}