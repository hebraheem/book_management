package library;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Executor {

    public void execute() {
        System.out.println("Running the executor...");
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        Library library = new Library(bufferedReader);
        int action= 0;

        int studentCount =Student.getStudentCount();

        if(studentCount == 0) {
            System.out.println("No students available. Add your first student.");
            addStudent(bufferedReader);
        }
        do {
                System.out.println("********* WELCOME TO LIBRARY *********");
                System.out.println("Please select an action:");
                System.out.println("0. Add Student");
                System.out.println("1. Add Book");
                System.out.println("2. Get Book List");
                System.out.println("3. Remove Book");
                System.out.println("4. Get Book by Category");
                System.out.println("5. Get Books by Categories");
                System.out.println("6. Borrow Books");
                System.out.println("7. Return Borrow Books");
                System.out.println("8. Exit");
                System.out.println("10. Get Student Information");
                System.out.print("Enter the action you want to perform: ");

                try {
                    action = Integer.parseInt(bufferedReader.readLine());
                } catch (NumberFormatException e) {
                    System.out.println("Invalid input. Please enter a number.");
                    continue;
                } catch (IOException e) {
                    System.out.println("Error reading input. Please try again.");
                    continue;
                }

                switch (action) {
                    case 0:
                        addStudent(bufferedReader);
                        break;
                    case 10:{
                        System.out.print("Enter your studentId: ");
                        String studentId = null;
                        try {
                            studentId = bufferedReader.readLine();
                        } catch (IOException e) {
                            System.out.println("Error reading input. Please try again.");
                            continue;
                        }
                        Student student = Student.getCurrentStudent(studentId);
                        if (student != null) {
                            System.out.println("Student information: " + student);
                        } else {
                            System.out.println("Student not found.");
                        }
                        break;
                    }
                    case 1:
                        library.addBook();
                        break;
                    case 2:{
                        System.out.println("Books in the library: ");
                        ArrayList<Book> books = library.getBooks();
                        for (Book book : books) {
                            System.out.println(book);
                        }
                        break;
                    }
                    case 3:{
                        System.out.print("Enter the book name to remove: ");
                        String bookName = null;
                        try {
                            bookName = bufferedReader.readLine();
                        } catch (IOException e) {
                            System.out.println("Error reading input. Please try again.");
                            continue;
                        }
                        library.removeBook(bookName);
                        break;
                    }
                    case 4:{
                        System.out.print("Enter the category to get books: ");
                        String category = null;
                        try {
                            category = bufferedReader.readLine();
                        } catch (IOException e) {
                            System.out.println("Error reading input. Please try again.");
                            continue;
                        }
                        ArrayList<Book> booksByCategory = library.getBookByCategory(category);
                        System.out.println("Books in the category " + category + ": " + booksByCategory);
                        break;
                    }
                    case 5:{
                        ArrayList<String> categories = library.getCategories(library.getBooks());
                        if(categories.isEmpty()) {
                            System.out.println("No categories available.");
                        } else {
                            System.out.println("Available categories: " + categories);
                        }
                    }
                    case 6: {
                        String bookName = null;
                        String studentId = null;
                        try {
                            System.out.print("Enter the book name to borrow: ");
                            bookName = bufferedReader.readLine();
                            System.out.print("Enter your studentId: ");
                            studentId = bufferedReader.readLine();
                        } catch (IOException e) {
                            System.out.println("Error reading input. Please try again.");
                            continue;
                        }
                        Book book= library.borrowBook(bookName, studentId);
                        if (book != null) {
                            System.out.println("Book borrowed successfully: " + book);
                            Student student = Student.getCurrentStudent(studentId);
                            student.updateBorrowedBooks(book);
                        } else {
                            System.out.println("Book not available or already borrowed.");
                        }
                        break;
                    }
                    case 7: {
                        String bookName = null;
                        String studentId = null;
                        try {
                            System.out.print("Enter the book name to return: ");
                            bookName = bufferedReader.readLine();
                            System.out.print("Enter your studentId: ");
                            studentId = bufferedReader.readLine();
                        } catch (IOException e) {
                            System.out.println("Error reading input. Please try again.");
                            continue;
                        }
                        Book book= library.returnBook(bookName, studentId);
                        if (book != null) {
                            System.out.println("Book returned successfully: " + book);
                            Student student = Student.getCurrentStudent(studentId);
                            student.removeReturnedBooks(book);
                        } else {
                            System.out.println("Book not available or not borrowed by you.");
                        }
                        break;
                    }
                    case 8:
                        System.out.println("Exiting...");
                        try {
                            bufferedReader.close();
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                        break;
                    default:
                        System.out.println("Invalid action. Please try again.");
                }
            } while (action != 4);
    }

    private void addStudent(BufferedReader bufferedReader) {
        try {
            System.out.print("Enter the student name: ");
            String studentName = bufferedReader.readLine();
            System.out.print("Enter the student level: ");
            int studentLevel = Integer.parseInt(bufferedReader.readLine());
            System.out.print("Enter the student ID: ");
            String studentId = bufferedReader.readLine();
            Student student = new Student(studentName, studentLevel, studentId);
            System.out.println("Student added successfully: " + student);
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter a number.");
        } catch (IOException e) {
            System.out.println("Error reading input. Please try again.");
        }
    }
}
