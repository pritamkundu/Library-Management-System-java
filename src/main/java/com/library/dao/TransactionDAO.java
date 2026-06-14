package com.library.dao;

import com.library.model.Transaction;
import com.library.util.DBConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TransactionDAO {

    // ISSUE a book: create transaction + decrease available_copies
    public void issueBook(int bookId, int memberId) {
        String checkSql = "SELECT available_copies FROM books WHERE book_id=?";
        String insertSql = "INSERT INTO transactions (book_id, member_id, issue_date, status) VALUES (?, ?, CURDATE(), 'ISSUED')";
        String updateSql = "UPDATE books SET available_copies = available_copies - 1 WHERE book_id=?";

        try (Connection conn = DBConnection.getConnection()) {
            // Check availability
            try (PreparedStatement checkStmt = conn.prepareStatement(checkSql)) {
                checkStmt.setInt(1, bookId);
                ResultSet rs = checkStmt.executeQuery();
                if (rs.next()) {
                    int available = rs.getInt("available_copies");
                    if (available <= 0) {
                        System.out.println("No copies available for this book.");
                        return;
                    }
                } else {
                    System.out.println("Book not found.");
                    return;
                }
            }

            // Insert transaction
            try (PreparedStatement insertStmt = conn.prepareStatement(insertSql)) {
                insertStmt.setInt(1, bookId);
                insertStmt.setInt(2, memberId);
                insertStmt.executeUpdate();
            }

            // Decrease available copies
            try (PreparedStatement updateStmt = conn.prepareStatement(updateSql)) {
                updateStmt.setInt(1, bookId);
                updateStmt.executeUpdate();
            }

            System.out.println("Book issued successfully.");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // RETURN a book: update transaction + increase available_copies
    public void returnBook(int txnId) {
        String getBookSql = "SELECT book_id FROM transactions WHERE txn_id=? AND status='ISSUED'";
        String updateTxnSql = "UPDATE transactions SET return_date=CURDATE(), status='RETURNED' WHERE txn_id=?";
        String updateBookSql = "UPDATE books SET available_copies = available_copies + 1 WHERE book_id=?";

        try (Connection conn = DBConnection.getConnection()) {
            int bookId = -1;

            // Get book_id from transaction
            try (PreparedStatement getStmt = conn.prepareStatement(getBookSql)) {
                getStmt.setInt(1, txnId);
                ResultSet rs = getStmt.executeQuery();
                if (rs.next()) {
                    bookId = rs.getInt("book_id");
                } else {
                    System.out.println("Transaction not found or already returned.");
                    return;
                }
            }

            // Update transaction status
            try (PreparedStatement updateTxnStmt = conn.prepareStatement(updateTxnSql)) {
                updateTxnStmt.setInt(1, txnId);
                updateTxnStmt.executeUpdate();
            }

            // Increase available copies
            try (PreparedStatement updateBookStmt = conn.prepareStatement(updateBookSql)) {
                updateBookStmt.setInt(1, bookId);
                updateBookStmt.executeUpdate();
            }

            System.out.println("Book returned successfully.");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // VIEW all currently issued books (JOIN query)
    public List<String> getIssuedBooks() {
        List<String> result = new ArrayList<>();
        String sql = "SELECT t.txn_id, b.title, m.name, t.issue_date " +
                "FROM transactions t " +
                "JOIN books b ON t.book_id = b.book_id " +
                "JOIN members m ON t.member_id = m.member_id " +
                "WHERE t.status='ISSUED'";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                String line = "Txn:" + rs.getInt("txn_id") +
                        " | Book: " + rs.getString("title") +
                        " | Issued to: " + rs.getString("name") +
                        " | Date: " + rs.getDate("issue_date");
                result.add(line);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }
}