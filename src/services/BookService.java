package services;

import models.Book;
import dao.BookDAO;
import utils.DBConnection;

import javax.swing.plaf.nimbus.State;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import utils.FileLogger;

public class BookService implements BookDAO {
    // Instantiate the file logger class to save the book to the CSV file
    FileLogger fileLogger = new FileLogger();

    @Override
    public void addBook(Book book) {
        // declare the sql query to be executed
        String sql = "INSERT INTO Books (title, author, genre, available_copies) values (?, ?, ?, ?)";

        // Use Try-with resources to establish a connection and make a prepared statement
        try (
                Connection conn = DBConnection.connectToDb();
                PreparedStatement statement = conn.prepareStatement(sql);
        ) {
            // Set the values to be saved to the database
            statement.setString(1, book.getTitle());
            statement.setString(2, book.getAuthor());
            statement.setString(3, book.getGenre());
            statement.setInt(4, book.getAvailable_copies());

            // Execute the query
            statement.executeUpdate();
            System.out.println("Book added to database successfully");
            fileLogger.writeToCSV(book.getBook_id() + "," + book.getTitle() + "," + book.getAuthor() + "," + book.getGenre() + "," + book.getAvailable_copies(), "books.csv");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Book getBookById(int bookId) {
        // declare the sql query to be executed
        String sql = "SELECT * FROM Books WHERE book_id=?";

        // Use Try-with resources to establish a connection and make a prepared statement
        try (
                Connection conn = DBConnection.connectToDb();
                PreparedStatement statement = conn.prepareStatement(sql);
        ) {

            // set the values to be queried
            statement.setInt(1, bookId);

            // execute the query
            ResultSet response = statement.executeQuery();
            if (response.next()) {
                return new Book(
                        response.getInt("book_id"), response.getString("title"), response.getString("author"),
                        response.getString("genre"), response.getInt("available_copies")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("Book cannot be found in the Database");
        return null;
    }

    @Override
    public List<Book> getAllBooks() {
        // declare an array to store the list of books gotten from the DB
        List<Book> bookList = new ArrayList<>();

        // declare the query to execute
        String sql = "SELECT * FROM Books";

        // Use Try-with resources to establish a connection and make a prepared statement
        try (
                Connection conn = DBConnection.connectToDb();
                Statement statement = conn.createStatement();
                ResultSet response = statement.executeQuery(sql);
        ) {
            // iterate through the response to create a book object
            while (response.next()) {
                bookList.add(new Book(
                        response.getInt("book_id"), response.getString("title"), response.getString("author"),
                        response.getString("genre"), response.getInt("available_copies"))
                );
            }

        }
        catch (SQLException e)  {
            e.printStackTrace();
        }

        return bookList;
    }

    @Override
    public void updateBook(Book book) {
        // Declare the query to execute
        String sql = "UPDATE Books SET title = ?, author = ?, genre = ?, available_copies = ? WHERE book_id = ?";

        // Use Try-with resources to establish a connection and make a prepared statement
        try (
                Connection conn = DBConnection.connectToDb();
                PreparedStatement pstmt = conn.prepareStatement(sql)
        ) {
            // set the values to be queried
            pstmt.setString(1, book.getTitle());
            pstmt.setString(2, book.getAuthor());
            pstmt.setString(3, book.getGenre());
            pstmt.setInt(4, book.getAvailable_copies());
            pstmt.setInt(5, book.getBook_id());
            int result = pstmt.executeUpdate();
            if (result == 0) {
                System.out.println("Book was not updated");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteBook(int bookId) {
        // Declare the query to execute
        String sql = "DELETE FROM Books WHERE book_id = ?";

        // Use Try-with resources to establish a connection and make a prepared statement
        try (
                Connection conn = DBConnection.connectToDb();
                PreparedStatement pstmt = conn.prepareStatement(sql)
        ) {
            // set the values to be queried

            pstmt.setInt(1, bookId);
            pstmt.executeUpdate();
            System.out.println("Book deleted from the Databse successfully");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Book> getBookByTitleAuthorGenre(String titleAuthorGenre) {
        // declare an array to store the list of books gotten from the DB
        List<Book> bookList = new ArrayList<>();

        // declare the sql query to be executed
        String sql = "SELECT * FROM Books WHERE title=? or author=? or genre=";

        // Use Try-with resources to establish a connection and make a prepared statement
        try (
                Connection conn = DBConnection.connectToDb();
                PreparedStatement statement = conn.prepareStatement(sql);
        ) {

            // set the values to be queried
            statement.setString(1, titleAuthorGenre);

            // execute the query
            ResultSet response = statement.executeQuery();
            if (response.next()) {
                bookList.add(new Book(
                        response.getInt("book_id"), response.getString("title"), response.getString("author"),
                        response.getString("genre"), response.getInt("available_copies")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return bookList;
    }
}
