package collectionService;

import models.BorrowingRecord;
import models.Book;
import models.Member;
import java.util.*;

public class BorrowingCollectionService {
    private final List<BorrowingRecord> borrowingRecords = new ArrayList<>();
    private final BookCollectionService bookCollectionService;
    private final MemberCollectionservice memberCollectionservice;

    public BorrowingCollectionService(BookCollectionService bookCollectionService, MemberCollectionservice memberCollectionservice) {
        this.bookCollectionService = bookCollectionService;
        this.memberCollectionservice = memberCollectionservice;
    }

    public void borrowBook(int bookId, int memberId) {
        Book book = bookCollectionService.getBookById(bookId);
        Member member = memberCollectionservice.getMemberById(memberId);

        if (book == null) {
            System.out.println("Error: Book not found.");
            return;
        }
        if (member == null) {
            System.out.println("Error: Member not found.");
            return;
        }
        if (book.getAvailable_copies() <= 0) {
            System.out.println("Error: No copies available.");
            return;
        }

        book.setAvailable_copies(book.getAvailable_copies() - 1);
        borrowingRecords.add(new BorrowingRecord(1, bookId, memberId, new Date(), null));
        System.out.println("Book borrowed successfully.");
    }

    public void returnBook(int bookId, int memberId) {
        for (BorrowingRecord record : borrowingRecords) {
            if (record.getBookId() == bookId && record.getMemberId() == memberId && record.getReturnDate() == null) {
                record.setReturnDate(new Date());
                Book book = bookCollectionService.getBookById(bookId);
                if (book != null) {
                    book.setAvailable_copies(book.getAvailable_copies() + 1);
                }
                System.out.println("Book returned successfully.");
                return;
            }
        }
        System.out.println("Error: Borrowing record not found.");
    }

    public List<BorrowingRecord> getAllBorrowingRecords() {
        return borrowingRecords;
    }
}
