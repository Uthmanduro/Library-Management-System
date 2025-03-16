package models;

public class Book {
    private int book_id;
    private String title;
    private String author;
    private String genre;
    private int available_copies;

    // Initialize the Book Constructor
    public Book(int book_id, String title, String author, String genre, int available_copies) {
        this.book_id = book_id;
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.available_copies = available_copies;
    }

    // Declare the getters and setters methods
    public int getBook_id() {
        return book_id;
    }

    public void setBook_id(int book_id) {
        this.book_id = book_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public int getAvailable_copies() {
        return available_copies;
    }

    public void setAvailable_copies(int available_copies) {
        this.available_copies = available_copies;
    }

    // override the default tostring() method to display a well formatted output
    @Override
    public String toString() {
        return "{ " + getBook_id() + " " + getTitle() + " " + getAuthor() + " " + getGenre() + " " + getAvailable_copies() + " }";
    }
}
