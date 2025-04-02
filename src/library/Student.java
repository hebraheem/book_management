package library;

import java.util.ArrayList;

public class Student {

    private final String name;
    private final int level;
    private final String studentId;
    private final ArrayList<Book> books = new ArrayList<>();
    private static final ArrayList<Student> students = new ArrayList<>();
    private static int studentCount = 0;

    public Student(String name, int level, String studentId) {
        this.studentId = studentId;
        this.name= name;
        this.level= level;
        students.add(this);
        studentCount++;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", level=" + level +
                ", studentId='" + studentId + '\'' +
                ", books=" + books +
                '}';
    }

    public void updateBorrowedBooks(Book book) {
        books.add(book);
    }

    public void removeReturnedBooks(Book book) {
        books.remove(book);
    }

    public static int getStudentCount() {
        return studentCount;
    }

    public static Student getCurrentStudent(String studentId) {
        return students.stream()
                .filter(student -> student.studentId.equals(studentId)).findFirst()
                .orElse(null);

    }
}
