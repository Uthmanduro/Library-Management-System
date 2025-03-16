package models;

import java.util.Date;

public class BorrowingRecord {
    private int id;
    private int bookId;
    private int memberId;
    private Date borrowDate;
    private Date returnDate;

    // Initialize the constructor
    public BorrowingRecord(int id, int bookId, int memberId, Date borrowDate, Date returnDate) {
        this.id = id;
        this.bookId = bookId;
        this.memberId = memberId;
        this.borrowDate = borrowDate;
        this.returnDate = returnDate;
    }

    // Declare the getter and setter methods
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public int getMemberId() {
        return memberId;
    }

    public void setMemberId(int memberId) {
        this.memberId = memberId;
    }

    public Date getBorrowDate() {
        return borrowDate;
    }

    public void setBorrowDate(Date borrowDate) {
        this.borrowDate = borrowDate;
    }

    public Date getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(Date returnDate) {
        this.returnDate = returnDate;
    }

    // override the default tostring() method to display a well formatted output
    @Override
    public String toString() {
        return "Member with ID " + getMemberId() + " borrowed book with ID " + getBookId() + " on " + getBorrowDate();
    }
}
