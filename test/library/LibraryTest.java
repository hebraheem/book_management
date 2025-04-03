package library;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class LibraryTest {

    BufferedReader bufferedReader;
    Library library;
    Book book;
    Student student;

    @BeforeEach
    void setUp() {
        bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        library = new Library(bufferedReader);
        book = new Book("Harry Potter", "J.K. Rowling", "123456789", 1997, "Fantasy");
        student = new Student("John Doe", 300, "CSC145");
        library.addBook(book);
    }

    @AfterEach
    void tearDown() throws IOException {
        bufferedReader.close();
        book= null;
        student=null;
        library.removeBook("Harry Potter");
    }

    @Test
    void removeBook() {
        library.removeBook("Harry Potter");
        assertFalse(library.books.contains(book));
    }

    @Test
    void returnBook() {
        String studentId = "CSC145";
        library.borrowBook("Harry Potter", studentId);
        Book returnedBook = library.returnBook("Harry Potter", studentId);
        assertNotNull(returnedBook);
        assertTrue(returnedBook.getIsAvailable());
        assertNull(returnedBook.getBorrowedBy());
    }

    @Test
    void getBooks() {
        assertTrue(library.books.contains(book));
    }

    @Test
    void getBookByCategory() {
        assertTrue(library.getBookByCategory("Fantasy").contains(book));
        assertFalse(library.getBookByCategory("Non-Fiction").contains(book));
    }

    @Test
    void borrowBook() {
        String studentId = "CSC145";
        Book borrowedBook = library.borrowBook("Harry Potter", studentId);
        assertNotNull(borrowedBook);
        assertFalse(borrowedBook.getIsAvailable());
        assertEquals(studentId, borrowedBook.getBorrowedBy());
    }

    @Test
    void getCategories() {
        ArrayList<Book> books = library.getBooks();
        assertTrue(library.getCategories(books).contains("Fantasy"));
        assertFalse(library.getCategories(books).contains("Non-Fiction"));
    }
}
