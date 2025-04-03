package library;

import java.io.*;
import java.util.ArrayList;

public class Library {

    ArrayList<Book> books= new ArrayList<>();
    BufferedReader bufferedReader;
    public Library(BufferedReader bufferedReader) {
        this.bufferedReader = bufferedReader;
    }

    public void addBook(Book book) {
        books.add(book);
    }

    public void addBook() {
        try{
            System.out.print("Enter the book title: ");
            String bookName=  bufferedReader.readLine();
            System.out.print("Enter the book author: ");
            String author=  bufferedReader.readLine();
            System.out.print("Enter the book isbn: ");
            String isbn=  bufferedReader.readLine();
            System.out.print("Enter the book category: ");
            String category=  bufferedReader.readLine();
            System.out.print("Enter the book publication year: ");
            int publicationYear =  bufferedReader.read();

            Book book = new Book(bookName, author, isbn, publicationYear,category);
            addBook(book);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void removeBook(String bookName) {

        for (Book book : books) {
            if (book.toString().contains(bookName)) {
                books.remove(book);
                System.out.println("Book removed successfully.");
                return;
            }
        }
    }

    public Book returnBook(String bookName, String studentId) {
        System.out.println(books);
        for (Book book : books) {
            if (book.toString().contains(bookName)) {
                if (book.getBorrowedBy() != null && book.getBorrowedBy().equals(studentId)) {
                    book.setIsAvailable(true);
                    book.setBorrowedBy(null);
                    System.out.println("Book returned successfully.");
                    return book;
                } else {
                    System.out.println("This book is not borrowed by you.");
                    return null;
                }
            }
        }
        System.out.println("Book not found.");
        return null;
    }

    public ArrayList<Book> getBooks() {
        return books;
    }

    public ArrayList<Book> getBookByCategory(String category) {
        ArrayList<Book> booksByCategory = new ArrayList<>();
        for (Book book : books) {
            if (book.getCategory().equalsIgnoreCase(category)) {
                booksByCategory.add(book);
            } else {
                System.out.println("No books found in this category.");
            }
        }
        return booksByCategory;
    }

    public Book borrowBook(String bookName, String studentId) {
        for (Book book : books) {
            if (book.toString().contains(bookName)) {
                if (book.getIsAvailable()) {
                    Student student = Student.getCurrentStudent(studentId);
                    System.out.println("Student: " + student);
                    if(student == null) {
                        System.out.println("Student not found.");
                        return null;
                    }
                    book.setBorrowedBy(studentId);
                    book.setIsAvailable(false);
                    return book;
                } else {
                    if(book.getBorrowedBy().equals(studentId)) {
                        System.out.println("Book is already borrowed by you.");
                        return book;
                    }
                    System.out.println("Book is already borrowed.");
                    return null;
                }
            }
        }
        System.out.println("Book not found.");
        return null;
    }

    public ArrayList<String> getCategories(ArrayList<Book> books) {
        ArrayList<String> categories = new ArrayList<>();
        for ( Book entry : books) {
            categories.add(entry.getCategory());
        }
        return categories;
    }

}
