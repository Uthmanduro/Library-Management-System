import services.BorrowingService;
import models.*;
import services.*;
import java.util.*;

public class TestBorrowingRecords {
    public static void main(String[] args) {
        // declare all the service used to communicate with the database
        BorrowingService borrowingService = new BorrowingService();
        BookService bookService = new BookService();
        MemberService memberService = new MemberService();

        // Initialize different collection object to store data temporarily
        List<Book> bookList = bookService.getAllBooks();
        List<Member> memberList = memberService.getAllMembers();


        // check if members and books are not empty and then borrow a book if true
        if (!bookList.isEmpty() && !memberList.isEmpty()) {
            borrowingService.borrowBook(bookList.get(0).getBook_id(), memberList.get(0).getMember_id());
        }

        // return the book borrowed
        if (!bookList.isEmpty() && !memberList.isEmpty()) {
            borrowingService.returnBook(bookList.get(0).getBook_id(), memberList.get(0).getMember_id());
        }

        // Display all borrowing records
        List<BorrowingRecord> borrowingRecords = borrowingService.getAllBorrowingRecords();
        if (!borrowingRecords.isEmpty()) {
            for (BorrowingRecord record : borrowingRecords) {
                System.out.println(record.toString());
            }
        }
    }
}
