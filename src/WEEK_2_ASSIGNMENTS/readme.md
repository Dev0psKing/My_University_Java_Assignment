# Library Management System

Welcome to the Library Management System! This Java program allows you to manage a library's inventory by adding, borrowing, and returning books. It's a simple yet effective tool for keeping track of books in your library.

## Features

- **Add Books**: You can add new books to the library, including the title, author, and quantity.
- **Borrow Books**: Borrow books from the library by specifying the title and the number of copies you want.
- **Return Books**: Return borrowed books back to the library.
- **Exit**: Quit the program.

## Getting Started

To use the Library Management System, follow these steps:

1. Clone or download the source code.
2. Compile the code using a Java compiler.
3. Run the compiled program.
4. Follow the on-screen instructions to perform various operations like adding, borrowing, or returning books.

## Concepts Explained

### Classes

- **Book**: Represents a book with attributes like title, author, and quantity.
- **LibraryManagementSystem**: Main class containing the program's entry point and methods to handle user input and library operations.

### Data Structures

- **HashMap**: Used to store books in the library, with titles as keys and Book objects as values. This allows efficient retrieval and modification of book information.

### Input Handling

- **Scanner**: Utilized for user input, enabling interaction with the program via the command line.

### Error Handling

- **NumberFormatException**: Handles invalid input by catching exceptions when parsing user input to integers.

## Usage

Upon running the program, you'll be presented with a menu displaying available operations. Simply enter the corresponding number to perform the desired action. Invalid inputs are gracefully handled, ensuring smooth user experience.

## Example

```java
public class Main {
    public static void main(String[] args) {
        LibraryManagementSystem.main(args);
    }
}

#### Contributing
Contributions are welcome! Feel free to submit issues or pull requests if you have any suggestions for improvements or feature additions.

#### This README.md file provides an overview of the Library Management System, explaining its features, usage, and underlying concepts. Enjoy managing your library efficiently with this simple yet powerful tool!