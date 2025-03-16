package collectionService;

import collectionService.*;
import models.*;
import java.util.*;

public class TestCollectionImpl {

    public static void main(String[] args) {
        // instantiate the book and member collection handler
        BookCollectionService bookHandler = new BookCollectionService();
        MemberCollectionservice memberHandler = new MemberCollectionservice();
        BorrowingCollectionService borrowingHandler = new BorrowingCollectionService(bookHandler, memberHandler);

        System.out.println("=== ADDING BOOKS ===");
        bookHandler.addBook(new Book(1, "The Pragmatic Programmer", "Andy Hunt", "Programming", 3));
        bookHandler.addBook(new Book(2, "Clean Code", "Robert C. Martin", "Software Engineering", 2));

        // Get all books in the array
        System.out.println("\n=== GETTING ALL BOOKS ===");
        List<Book> bookList = bookHandler.getAllBooks();
        for (Book book : bookList) {
            System.out.println(book.toString());
        }

        System.out.println("\n=== UPDATING BOOK ===");
        Book updatedBook = new Book(1, "The Pragmatic Programmer", "Andy Hunt", "Software Development", 4);
        bookHandler.updateBook(updatedBook);
        System.out.println("Updated book: " + bookHandler.getBookById(1));


        System.out.println("\n=== GETTING BOOK BY ID ===");
        System.out.println("Book with ID 2: " + bookHandler.getBookById(2));

        System.out.println("\n=== SEARCHING BOOK BY TITLE, AUTHOR, OR GENRE ===");
        System.out.println("Books matching 'Software': " + bookHandler.getBookByTitleAuthorGenre("Software"));

        System.out.println("\n=== DELETING A BOOK ===");
        bookHandler.deleteBook(2);
        System.out.println("Books after deletion: " + bookHandler.getAllBooks());


        // Testing all Member Implementation

        System.out.println("\n=== ADDING MEMBERS ===");
        memberHandler.addMember(new Member(1, "John Doe", "johndoe@gmail.com", "+902344565665"));
        memberHandler.addMember(new Member(2, "Jane Smith", "janesmith@gmail.com", "+90987654325"));
        System.out.println("Members after adding: " + memberHandler.getAllMembers());

        // Get all members in the array
        List<Member> memberList = memberHandler.getAllMembers();
        for (Member member : memberList) {
            System.out.println(member.toString());
        }

        System.out.println("\n=== UPDATING MEMBER ===");
        Member updatedMember = new Member(1, "Johnathan Doe", "johndoe@gmail.com", "+902344565665");
        memberHandler.updateMember(updatedMember);
        System.out.println("Updated member: " + memberHandler.getMemberById(1));

        System.out.println("\n=== GETTING MEMBER BY ID ===");
        System.out.println("Member with ID 2: " + memberHandler.getMemberById(2));

        System.out.println("\n=== DELETING A MEMBER ===");
        memberHandler.deleteMember(2);
        System.out.println("Members after deletion: " + memberHandler.getAllMembers());



        // TESTING THE BORROWING RECORD IMPLEMENTATION

        System.out.println("\n=== BORROWING A BOOK ===");
        borrowingHandler.borrowBook(1, 1);
        borrowingHandler.borrowBook(2, 2);

        System.out.println("\n=== RETURNING A BOOK ===");
        borrowingHandler.returnBook(1, 1);

        System.out.println("\nBooks in Library:");
        bookHandler.getAllBooks().forEach(System.out::println);

        System.out.println("\nBorrowing Records:");
        borrowingHandler.getAllBorrowingRecords().forEach(System.out::println);
    }
}
