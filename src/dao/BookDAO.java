package dao;

import models.Book;
import java.util.List;

public interface BookDAO {
    void addBook(Book book);
    void updateBook(Book book);
    void deleteBook(int bookId);
    Book getBookById(int bookId);
    List<Book> getAllBooks();
    List<Book> getBookByTitleAuthorGenre(String titleAuthorGenre);
}

