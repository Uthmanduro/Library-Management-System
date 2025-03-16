package dao;

import models.BorrowingRecord;
import java.util.List;

public interface BorrowingDAO {
    void borrowBook(int bookId, int memberId);
    void returnBook(int bookId, int memberId);
    List<BorrowingRecord> getAllBorrowingRecords();
}

