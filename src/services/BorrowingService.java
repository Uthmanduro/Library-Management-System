package services;

import models.BorrowingRecord;
import utils.*;
import dao.BorrowingDAO;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class BorrowingService implements BorrowingDAO {
    // instantiate the file logger class to be used to log the activities to the file
    FileLogger fileLogger = new FileLogger();

    @Override
    public void borrowBook(int bookId, int memberId) {
        // Declare the queries to be executed
        String borrowQuery = "INSERT INTO Borrowing_Records (book_id, member_id, borrow_date) VALUES (?, ?, CURRENT_DATE)";
        String updateBookQuery = "UPDATE Books SET available_copies = available_copies - 1 WHERE book_id = ? AND available_copies > 0";

        // Use Try-with resources to establish a connection and make a prepared statement
        try (Connection conn = DBConnection.connectToDb();
             PreparedStatement borrowStmt = conn.prepareStatement(borrowQuery);
             PreparedStatement updateBookStmt = conn.prepareStatement(updateBookQuery);
        ) {
            // initiate a transaction to ensure both queries succeed
            conn.setAutoCommit(false);

            // Check if the book has available copies
            updateBookStmt.setInt(1, bookId);
            int rowsUpdated = updateBookStmt.executeUpdate();
            if (rowsUpdated == 0) {
                System.out.println("Book is not available for borrowing.");
                return;
            }

            // Insert borrowing record
            borrowStmt.setInt(1, bookId);
            borrowStmt.setInt(2, memberId);
            borrowStmt.executeUpdate();

            // commit the transaction
            conn.commit();
            System.out.println("Book borrowed successfully!");

            // log the activity into the library_log.txt file
            fileLogger.writeToFile("Member " + memberId + " borrowed book with Id " + bookId + " on " + LocalDate.now());

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void returnBook(int bookId, int memberId) {
        // Declare the queries to be executed
        String returnQuery = "UPDATE Borrowing_Records SET return_date = CURRENT_DATE WHERE book_id = ? AND member_id = ? AND return_date IS NULL";
        String updateBookQuery = "UPDATE Books SET available_copies = available_copies + 1 WHERE book_id = ?";

        // Use Try-with resources to establish a connection and make a prepared statement
        try (Connection conn = DBConnection.connectToDb();
             PreparedStatement returnStmt = conn.prepareStatement(returnQuery);
             PreparedStatement updateBookStmt = conn.prepareStatement(updateBookQuery)) {

            // initiate a transaction to ensure both queries succeed
            conn.setAutoCommit(false);

            // Update the borrowing record
            returnStmt.setInt(1, bookId);
            returnStmt.setInt(2, memberId);
            int rowsUpdated = returnStmt.executeUpdate();
            if (rowsUpdated == 0) {
                System.out.println("No active borrowing record found for this book and member.");
                return;
            }

            // Increase available copies
            updateBookStmt.setInt(1, bookId);
            updateBookStmt.executeUpdate();

            // commit the transaction
            conn.commit();
            System.out.println("Book returned successfully!");

            // log the activity into the library_log.txt file
            fileLogger.writeToFile("Member " + memberId + " returned book with Id " + bookId + " on " + LocalDate.now());


        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<BorrowingRecord> getAllBorrowingRecords() {
        // instantiate a records variable to store the borrowing records retrieved from the database
        List<BorrowingRecord> records = new ArrayList<>();

        // Declare the queries to be executed
        String query = "SELECT * FROM Borrowing_Records";

        // Use Try-with resources to establish a connection and make a prepared statement
        try (Connection conn = DBConnection.connectToDb();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                BorrowingRecord record = new BorrowingRecord(
                        rs.getInt("record_id"),
                        rs.getInt("book_id"),
                        rs.getInt("member_id"),
                        rs.getDate("borrow_date"),
                        rs.getDate("return_date")
                );
                // add each record to the records array
                records.add(record);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return records;
    }
}
