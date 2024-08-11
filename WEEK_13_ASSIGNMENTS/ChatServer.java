package WEEK_13_ASSIGNMENTS;



import java.io.*;

import java.net.*;

import java.util.*;

import java.util.concurrent.*;



// ChatServer class to handle multiple client connections

public class ChatServer {

    private static final int PORT = 5000; // Port number to listen on

    private static Set<PrintWriter> clients = ConcurrentHashMap.newKeySet(); // Set to store client output streams

    private static int userIdCounter = 1; // Counter to assign unique user IDs



    public static void main(String[] args) {

        System.out.println("Chat Server is running...");



        // Create a server socket to accept client connections

        try (ServerSocket serverSocket = new ServerSocket(PORT)) {

            while (true) {

                // Accept a new client connection and handle it in a new thread

                new ServerClientHandler(serverSocket.accept(), clients, userIdCounter++).start();

            }

        } catch (IOException e) {

            System.out.println("Server error: " + e.getMessage());

        }

    }

}



// Thread class to handle individual client connections

class ServerClientHandler extends Thread {

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



    @Override

    public void run() {

        try {

            // Initialize input and output streams for the client

            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            out = new PrintWriter(socket.getOutputStream(), true);



            // Add the client's output stream to the set of clients

            clients.add(out);

            // Notify all clients that a new user has joined the chat

            broadcast("User " + userId + " has joined the chat.");



            String message;

            // Continuously read messages from the client and broadcast them

            while ((message = in.readLine()) != null) {

                broadcast("User " + userId + ": " + message);

            }

        } catch (IOException e) {

            System.out.println("User " + userId + " connection error: " + e.getMessage());

        } finally {

            // Clean up resources and notify all clients that the user has left the chat

            cleanup();

        }

    }



    // Broadcast a message to all connected clients

    private void broadcast(String message) {

        synchronized (clients) {

            for (PrintWriter client : clients) {

                client.println(message);

            }

        }

    }



    // Clean up resources when a client disconnects

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

