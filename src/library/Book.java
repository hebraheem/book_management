package library;

public class Book {

   final private String title;
   final private String author;
   final private String isbn;
   final private int publicationYear;
   final private String category;
   private String borrowedBy = null;
   private boolean isAvailable = true;


    public Book(String title, String author, String isbn, int publicationYear, String category) {
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.publicationYear = publicationYear;
        this.category = category;

    }

    @Override
    public String toString() {
        return "Book{" +
                "title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", isbn='" + isbn + '\'' +
                ", publicationYear=" + publicationYear + '\''  +
                ", category=" + category +
                '}';
    }

    public String getCategory() {
        return  category;
    }

    public String getBorrowedBy() {
        return borrowedBy;
    }

    public boolean getIsAvailable() {
        return isAvailable;
    }

    public void setBorrowedBy(String borrowedBy) {
        this.borrowedBy = borrowedBy;
    }

    public void setIsAvailable(boolean isAvailable) {
        this.isAvailable = isAvailable;
    }

}
