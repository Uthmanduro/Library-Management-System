package collectionService;
import models.Book;
import java.util.*;

public class BookCollectionService {
    private final List<Book> books = new ArrayList<>();

    // Add a book to the collection
    public void addBook(Book book) {
        books.add(book);
    }

    // Update book details
    public void updateBook(Book book) {
        for (int i = 0; i < books.size(); i++) {
            if (books.get(i).getBook_id() == book.getBook_id()) {
                books.set(i, book);
                return;
            }
        }
    }

    // Delete a book
    public void deleteBook(int bookId) {
        books.removeIf(book -> book.getBook_id() == bookId);
    }

    // Get book by ID
    public Book getBookById(int bookId) {
        return books.stream().filter(b -> b.getBook_id() == bookId).findFirst().orElse(null);
    }

    // Get all books
    public List<Book> getAllBooks() {
        return books;
    }

    // Get books by title, author, or genre (case-insensitive search)
    public List<Book> getBookByTitleAuthorGenre(String titleAuthorGenre) {
        String query = titleAuthorGenre.toLowerCase();
        List<Book> result = new ArrayList<>();
        for (Book book : books) {
            if (book.getTitle().toLowerCase().contains(query) ||
                    book.getAuthor().toLowerCase().contains(query) ||
                    book.getGenre().toLowerCase().contains(query)) {
                result.add(book);
            }
        }
        return result;
    }
}
