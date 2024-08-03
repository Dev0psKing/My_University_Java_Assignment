package WEEK_12_ASSIGNMENTS;



import java.util.ArrayList;

import java.util.InputMismatchException; // Importing InputMismatchException

import java.util.List;

import java.util.Scanner;



// LibraryItem class representing an item in the library

class LibraryItem<T> {

    private String title;

    private String author;

    private T itemID;



    public LibraryItem(String title, String author, T itemID) {

        this.title = title;

        this.author = author;

        this.itemID = itemID;

    }



    // Getters and setters for the LibraryItem attributes

    public String getTitle() { return title; }

    public void setTitle(String title) { this.title = title; }

    public String getAuthor() { return author; }

    public void setAuthor(String author) { this.author = author; }

    public T getItemID() { return itemID; }

    public void setItemID(T itemID) { this.itemID = itemID; }



    @Override

    public String toString() {

        return "Title: " + title + ", Author: " + author + ", ID: " + itemID;

    }

}



// Catalog class to manage library items

class Catalog<T extends LibraryItem<?>> {

    private List<T> items;



    public Catalog() {

        this.items = new ArrayList<>();

    }



    // Method to add an item to the catalog

    public void addItem(T item) {

        items.add(item);

    }



    // Method to remove an item from the catalog

    public boolean removeItem(T item) {

        return items.remove(item);

    }



    // Method to get an item by index

    public T getItem(int index) {

        if (index >= 0 && index < items.size()) {

            return items.get(index);

        }

        return null;

    }



    // Method to get all items in the catalog

    public List<T> getAllItems() {

        return new ArrayList<>(items);

    }



    // Method to get the size of the catalog

    public int size() {

        return items.size();

    }

}



// Main application class to interact with the library catalog

public class LibraryCatalogApp {

    private static Catalog<LibraryItem<String>> catalog = new Catalog<>();

    private static Scanner scanner = new Scanner(System.in);



    public static void main(String[] args) {

        while (true) {

            try {

                System.out.println("\nLibrary Catalog Menu:");

                System.out.println("1. Add a new item");

                System.out.println("2. Remove an item");

                System.out.println("3. View catalog");

                System.out.println("4. Exit");

                System.out.print("Enter your choice: ");



                int choice = scanner.nextInt();

                scanner.nextLine(); // Consume newline



                switch (choice) {

                    case 1:

                        addItem();

                        break;

                    case 2:

                        removeItem();

                        break;

                    case 3:

                        viewCatalog();

                        break;

                    case 4:

                        System.out.println("Exiting...");

                        return;

                    default:

                        System.out.println("Invalid choice. Please try again.");

                }

            } catch (InputMismatchException e) {

                System.out.println("Invalid input. Please enter a number between 1 and 4.");

                scanner.nextLine(); // Clear the invalid input

            }

        }

    }



    // Method to add an item to the catalog

    private static void addItem() {

        System.out.print("Enter title: ");

        String title = scanner.nextLine();

        System.out.print("Enter author: ");

        String author = scanner.nextLine();

        System.out.print("Enter item ID: ");

        String itemID = scanner.nextLine();



        LibraryItem<String> item = new LibraryItem<>(title, author, itemID);

        catalog.addItem(item);

        System.out.println("Item added successfully.");

    }



    // Method to remove an item from the catalog with proper error handling

    private static void removeItem() {

        while (true) {

            try {

                System.out.print("Enter the index of the item to remove: ");

                int index = scanner.nextInt();

                scanner.nextLine(); // Consume newline



                LibraryItem<String> item = catalog.getItem(index);

                if (item != null) {

                    catalog.removeItem(item);

                    System.out.println("Item removed successfully.");

                    break; // Exit the loop if removal is successful

                } else {

                    System.out.println("Invalid index. Item not found. Please try again.");

                }

            } catch (InputMismatchException e) {

                System.out.println("Invalid input. Please enter a valid index.");

                scanner.nextLine(); // Clear the invalid input

            }

        }

    }



    // Method to view all items in the catalog

    private static void viewCatalog() {

        System.out.println("\nCurrent Catalog:");

        List<LibraryItem<String>> items = catalog.getAllItems();

        for (int i = 0; i < items.size(); i++) {

            System.out.println(i + ": " + items.get(i));

        }

    }

}

 