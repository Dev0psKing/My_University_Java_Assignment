package WEEK_12_ASSIGNMENTS;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// LibraryItem class
class LibraryItem<T> {
    private String title;
    private String author;
    private T itemID;

    public LibraryItem(String title, String author, T itemID) {
        this.title = title;
        this.author = author;
        this.itemID = itemID;
    }

    // Getters and setters
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

// Catalog class
class Catalog<T extends LibraryItem<?>> {
    private List<T> items;

    public Catalog() {
        this.items = new ArrayList<>();
    }

    public void addItem(T item) {
        items.add(item);
    }

    public boolean removeItem(T item) {
        return items.remove(item);
    }

    public T getItem(int index) {
        if (index >= 0 && index < items.size()) {
            return items.get(index);
        }
        return null;
    }

    public List<T> getAllItems() {
        return new ArrayList<>(items);
    }

    public int size() {
        return items.size();
    }
}

// Main application class
public class LibraryCatalogApp {
    private static Catalog<LibraryItem<String>> catalog = new Catalog<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
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
        }
    }

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

    private static void removeItem() {
        System.out.print("Enter the index of the item to remove: ");
        int index = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        LibraryItem<String> item = catalog.getItem(index);
        if (item != null) {
            catalog.removeItem(item);
            System.out.println("Item removed successfully.");
        } else {
            System.out.println("Invalid index. Item not found.");
        }
    }

    private static void viewCatalog() {
        System.out.println("\nCurrent Catalog:");
        List<LibraryItem<String>> items = catalog.getAllItems();
        for (int i = 0; i < items.size(); i++) {
            System.out.println(i + ": " + items.get(i));
        }
    }
}

// Test class (remove if not needed)
// Note: This requires JUnit to run
/*
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CatalogTest {
    private Catalog<LibraryItem<String>> catalog;

    @BeforeEach
    void setUp() {
        catalog = new Catalog<>();
    }

    @Test
    void testAddItem() {
        LibraryItem<String> item = new LibraryItem<>("Test Book", "Test Author", "123");
        catalog.addItem(item);
        assertEquals(1, catalog.size());
        assertEquals(item, catalog.getItem(0));
    }

    @Test
    void testRemoveItem() {
        LibraryItem<String> item = new LibraryItem<>("Test Book", "Test Author", "123");
        catalog.addItem(item);
        assertTrue(catalog.removeItem(item));
        assertEquals(0, catalog.size());
    }

    @Test
    void testRemoveNonExistentItem() {
        LibraryItem<String> item = new LibraryItem<>("Test Book", "Test Author", "123");
        assertFalse(catalog.removeItem(item));
    }

    @Test
    void testGetItemOutOfBounds() {
        assertNull(catalog.getItem(0));
    }
}
*/