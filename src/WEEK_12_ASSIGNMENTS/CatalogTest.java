package WEEK_12_ASSIGNMENTS;

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