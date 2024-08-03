# Simple Chat Application

This is a simple chat application implemented in Java using socket programming. It consists of a server (`ChatServer`) that manages connections from multiple clients, and a client (`ChatClient`) that connects to the server to send and receive messages.

## Features

- Server assigns a unique user ID to each connected client.
- Clients can send messages to the server, which will broadcast them to all connected clients.
- Clients can receive messages from other users.
- A simple text-based user interface for message input and display.

## How to Run

### Prerequisites

- Java Development Kit (JDK) installed on your machine.
- An IDE (like IntelliJ IDEA or Eclipse) or a text editor and terminal/command prompt.

### Steps

1. **Compile the Java files:**

   Open a terminal/command prompt and navigate to the directory containing the `ChatServer.java` and `ChatClient.java` files. Run the following command to compile the Java files:

   ```sh
   javac ChatServer.java ChatClient.java
