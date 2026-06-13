package com.library.model;

import java.sql.Date;

public class Transaction {
    private int txnId;
    private int bookId;
    private int memberId;
    private Date issueDate;
    private Date returnDate;
    private String status;

    public Transaction() {}

    public Transaction(int bookId, int memberId, Date issueDate, String status) {
        this.bookId = bookId;
        this.memberId = memberId;
        this.issueDate = issueDate;
        this.status = status;
    }

    public int getTxnId() { return txnId; }
    public void setTxnId(int txnId) { this.txnId = txnId; }
    public int getBookId() { return bookId; }
    public void setBookId(int bookId) { this.bookId = bookId; }
    public int getMemberId() { return memberId; }
    public void setMemberId(int memberId) { this.memberId = memberId; }
    public Date getIssueDate() { return issueDate; }
    public void setIssueDate(Date issueDate) { this.issueDate = issueDate; }
    public Date getReturnDate() { return returnDate; }
    public void setReturnDate(Date returnDate) { this.returnDate = returnDate; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    @Override
    public String toString() {
        return txnId + " | Book:" + bookId + " | Member:" + memberId + " | Issued:" + issueDate + " | Returned:" + returnDate + " | " + status;
    }
}