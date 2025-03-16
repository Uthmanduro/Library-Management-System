package DAOTest;

import models.Book;
import services.BookService;
import java.util.*;

public class TestBook {

    public static void main(String[] args) {
        List<Book> booklist;
        BookService bookService = new BookService();

        // Add a new book to the database
         bookService.addBook(new Book(0, "Java for Beginners", "John Doe", "Programming", 5));
         bookService.addBook(new Book(1, "Introduction to Java", "Max Victor", "Programming", 20));


         //Get all the books in the database
         booklist = bookService.getAllBooks();
        System.out.println("=================== Book Database =================");
        for (Book book : booklist) {
            System.out.println(book.toString());
        }
        System.out.println("=================== End of Database =================");


        // Get a single book from the database
        Book singleBook = bookService.getBookById(1);

        // check if the book was present in the database before printing the result
        if (singleBook != null) {
            System.out.println(singleBook.toString());
        } else {
            System.out.println("Book cannot be found in the database");
        }


        if (singleBook != null) {
            // set the avalable copies of the book gotten from the database to a new value
            singleBook.setAvailable_copies(5);

            // update a book in the database
            bookService.updateBook(singleBook);

            // Get the updated single book from the database
            Book updatedSingleBook = bookService.getBookById(1);
            System.out.println(updatedSingleBook.toString());
        }


        // Delete book from the database
        bookService.deleteBook(1);
    }
}
