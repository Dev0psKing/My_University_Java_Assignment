package WEEK_13_ASSIGNMENTS;

import java.io.*;
import java.net.*;
import java.util.*;

// ChatClient class to connect to the chat server and communicate with other clients
public class ChatClient {
    private static final int PORT = 5000; // Port number to connect to
    private static final String SERVER_ADDRESS = "localhost"; // Server address

    public static void main(String[] args) {
        // Initialize socket, input/output streams, and scanner
        try (Socket socket = new Socket(SERVER_ADDRESS, PORT);
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
             Scanner scanner = new Scanner(System.in)) {

            System.out.println("Connected to the chat server");

            // Start a new thread to listen for messages from the server
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

            // Continuously read messages from the user and send them to the server
            while (true) {
                String message = scanner.nextLine();
                out.println(message);
                if (message.equalsIgnoreCase("exit")) {
                    break; // Exit the loop if the user types "exit"
                }
            }
        } catch (IOException e) {
            System.out.println("Client error: " + e.getMessage());
        }
    }
}
