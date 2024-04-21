package WEEK_2_ASSIGNMENTS;



import java.util.HashMap;

import java.util.Map;

import java.util.Scanner;



class Book {

    String title;

    String author;

    int quantity;



    public Book(String title, String author, int quantity) {

        this.title = title;

        this.author = author;

        this.quantity = quantity;

    }



    @Override

    public boolean equals(Object obj) {

        if (obj instanceof Book) {

            Book other = (Book) obj;

            return title.equals(other.title) && author.equals(other.author);

        }

        return false;

    }



    @Override

    public int hashCode() {

        return (title + author).hashCode();

    }

}



public class LibraryManagementSystem {

    private static final Map<Book, Integer> library = new HashMap<>();

    private static final Scanner scanner = new Scanner(System.in);



    public static void main(String[] args) {

        boolean exit = false;

        while (!exit) {

            displayMenu();

            int choice = getChoice();

            handleChoice(choice);

            exit = choice == 4;

        }

    }



    private static void displayMenu() {

        System.out.println("\nLibrary Management System");

        System.out.println("1. Add Books");

        System.out.println("2. Borrow Books");

        System.out.println("3. Return Books");

        System.out.println("4. Exit");

        System.out.print("Enter your choice: ");

    }



    private static int getChoice() {

        int choice = -1;

        try {

            choice = Integer.parseInt(scanner.nextLine());

        } catch (NumberFormatException e) {

            System.out.println("Invalid input. Please enter a number.");

        }

        return choice;

    }



    private static void handleChoice(int choice) {

        switch (choice) {

            case 1:

                addBooks();

                break;

            case 2:

                borrowBooks();

                break;

            case 3:

                returnBooks();

                break;

            case 4:

                System.out.println("Exiting the program...");

                break;

            default:

                System.out.println("Invalid choice. Please try again.");

        }

    }



    private static void addBooks() {

        System.out.print("Enter book title: ");

        String title = scanner.nextLine();

        System.out.print("Enter book author: ");

        String author = scanner.nextLine();

        System.out.print("Enter book quantity: ");

        int quantity = getIntInput();



        Book book = new Book(title, author, quantity);

        if (library.containsKey(book)) {

            library.put(book, library.get(book) + quantity);

            System.out.println("Book quantity updated successfully.");

        } else {

            library.put(book, quantity);

            System.out.println("Book added successfully.");

        }

    }



    private static void borrowBooks() {

        System.out.print("Enter book title: ");

        String title = scanner.nextLine();

        System.out.print("Enter book author: ");

        String author = scanner.nextLine();

        System.out.print("Enter number of books to borrow: ");

        int numToBorrow = getIntInput();



        Book book = new Book(title, author, 0);

        if (library.containsKey(book)) {

            int availableQuantity = library.get(book);

            if (availableQuantity >= numToBorrow) {

                library.put(book, availableQuantity - numToBorrow);

                System.out.println("Books borrowed successfully.");

            } else {

                System.out.println("Sorry, not enough books available.");

            }

        } else {

            System.out.println("Sorry, this book is not in our library.");

        }

    }



    private static void returnBooks() {

        System.out.print("Enter book title: ");

        String title = scanner.nextLine();

        System.out.print("Enter book author: ");

        String author = scanner.nextLine();

        System.out.print("Enter number of books to return: ");

        int numToReturn = getIntInput();



        Book book = new Book(title, author, 0);

        if (library.containsKey(book)) {

            library.put(book, library.get(book) + numToReturn);

            System.out.println("Books returned successfully.");

        } else {

            System.out.println("Sorry, this book is not in our library.");

        }

    }



    private static int getIntInput() {

        int input = -1;

        try {

            input = Integer.parseInt(scanner.nextLine());

        } catch (NumberFormatException e) {

            System.out.println("Invalid input. Please enter a number.");

        }

        return input;

    }

}