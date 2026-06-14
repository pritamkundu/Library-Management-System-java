package com.library;

import com.google.gson.Gson;
import com.library.dao.BookDAO;
import com.library.dao.MemberDAO;
import com.library.dao.TransactionDAO;
import com.library.model.Book;
import com.library.model.Member;

import static spark.Spark.*;

public class LibraryAPI {
    public static void main(String[] args) {
        Gson gson = new Gson();
        BookDAO bookDAO = new BookDAO();
        MemberDAO memberDAO = new MemberDAO();
        TransactionDAO transactionDAO = new TransactionDAO();

        // THIS must come first
        staticFiles.location("/public");

        // CORS headers
        before((req, res) -> {
            res.header("Access-Control-Allow-Origin", "*");
            res.header("Access-Control-Allow-Methods", "GET,POST,PUT,DELETE,OPTIONS");
            res.header("Access-Control-Allow-Headers", "Content-Type");
        });
        options("/*", (req, res) -> "OK");

        // ===== BOOK ROUTES =====
        get("/books", (req, res) -> {
            res.type("application/json");
            return gson.toJson(bookDAO.getAllBooks());
        });

        post("/books", (req, res) -> {
            Book book = gson.fromJson(req.body(), Book.class);
            bookDAO.addBook(book);
            res.type("application/json");
            return gson.toJson("Book added successfully");
        });

        put("/books/:id", (req, res) -> {
            Book book = gson.fromJson(req.body(), Book.class);
            book.setBookId(Integer.parseInt(req.params("id")));
            bookDAO.updateBook(book);
            res.type("application/json");
            return gson.toJson("Book updated successfully");
        });

        delete("/books/:id", (req, res) -> {
            bookDAO.deleteBook(Integer.parseInt(req.params("id")));
            res.type("application/json");
            return gson.toJson("Book deleted successfully");
        });

        // ===== MEMBER ROUTES =====
        get("/members", (req, res) -> {
            res.type("application/json");
            return gson.toJson(memberDAO.getAllMembers());
        });

        post("/members", (req, res) -> {
            Member member = gson.fromJson(req.body(), Member.class);
            memberDAO.addMember(member);
            res.type("application/json");
            return gson.toJson("Member added successfully");
        });

        put("/members/:id", (req, res) -> {
            Member member = gson.fromJson(req.body(), Member.class);
            member.setMemberId(Integer.parseInt(req.params("id")));
            memberDAO.updateMember(member);
            res.type("application/json");
            return gson.toJson("Member updated successfully");
        });

        delete("/members/:id", (req, res) -> {
            memberDAO.deleteMember(Integer.parseInt(req.params("id")));
            res.type("application/json");
            return gson.toJson("Member deleted successfully");
        });

        // ===== TRANSACTION ROUTES =====
        post("/issue", (req, res) -> {
            int bookId = Integer.parseInt(req.queryParams("bookId"));
            int memberId = Integer.parseInt(req.queryParams("memberId"));
            transactionDAO.issueBook(bookId, memberId);
            res.type("application/json");
            return gson.toJson("Book issued successfully");
        });

        post("/return", (req, res) -> {
            int txnId = Integer.parseInt(req.queryParams("txnId"));
            transactionDAO.returnBook(txnId);
            res.type("application/json");
            return gson.toJson("Book returned successfully");
        });

        get("/issued", (req, res) -> {
            res.type("application/json");
            return gson.toJson(transactionDAO.getIssuedBooks());
        });
    }
}