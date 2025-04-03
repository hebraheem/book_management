package library;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BookTest {

    @Test
    void getCategory() {
        Book book = new Book("The Great Gatsby", "F. Scott Fitzgerald", "9780743273565", 1925, "Fiction");
        String expectedCategory = "Fiction";
        String actualCategory = book.getCategory();
        assertEquals(expectedCategory, actualCategory);
    }

}
