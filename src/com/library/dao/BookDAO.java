package com.library.dao;

import com.library.model.Book;
import com.library.util.DBConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookDAO {

    // CREATE
    public void addBook(Book book) {
        String sql = "INSERT INTO books (title, author, isbn, available_copies) VALUES (?, ?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, book.getTitle());
            stmt.setString(2, book.getAuthor());
            stmt.setString(3, book.getIsbn());
            stmt.setInt(4, book.getAvailableCopies());
            stmt.executeUpdate();
            System.out.println("Book added successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // READ ALL
    public List<Book> getAllBooks() {
        List<Book> books = new ArrayList<>();
        String sql = "SELECT * FROM books";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Book book = new Book();
                book.setBookId(rs.getInt("book_id"));
                book.setTitle(rs.getString("title"));
                book.setAuthor(rs.getString("author"));
                book.setIsbn(rs.getString("isbn"));
                book.setAvailableCopies(rs.getInt("available_copies"));
                books.add(book);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return books;
    }

    // UPDATE
    public void updateBook(Book book) {
        String sql = "UPDATE books SET title=?, author=?, isbn=?, available_copies=? WHERE book_id=?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, book.getTitle());
            stmt.setString(2, book.getAuthor());
            stmt.setString(3, book.getIsbn());
            stmt.setInt(4, book.getAvailableCopies());
            stmt.setInt(5, book.getBookId());
            stmt.executeUpdate();
            System.out.println("Book updated successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // DELETE
    public void deleteBook(int bookId) {
        String sql = "DELETE FROM books WHERE book_id=?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, bookId);
            stmt.executeUpdate();
            System.out.println("Book deleted successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}