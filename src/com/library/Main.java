package com.library;

import com.library.dao.BookDAO;
import com.library.dao.MemberDAO;
import com.library.dao.TransactionDAO;
import com.library.model.Book;
import com.library.model.Member;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        BookDAO bookDAO = new BookDAO();
        MemberDAO memberDAO = new MemberDAO();
        TransactionDAO transactionDAO = new TransactionDAO();

        while (true) {
            System.out.println("\n--- Library Management System ---");
            System.out.println("1. Add Book");
            System.out.println("2. View All Books");
            System.out.println("3. Update Book");
            System.out.println("4. Delete Book");
            System.out.println("5. Add Member");
            System.out.println("6. View All Members");
            System.out.println("7. Update Member");
            System.out.println("8. Delete Member");
            System.out.println("9. Issue Book");
            System.out.println("10. Return Book");
            System.out.println("11. View Issued Books");
            System.out.println("12. Exit");
            System.out.print("Choose an option: ");
            int choice = sc.nextInt();
            sc.nextLine(); // consume leftover newline

            switch (choice) {
                case 1:
                    System.out.print("Title: ");
                    String title = sc.nextLine();
                    System.out.print("Author: ");
                    String author = sc.nextLine();
                    System.out.print("ISBN: ");
                    String isbn = sc.nextLine();
                    System.out.print("Copies: ");
                    int copies = sc.nextInt();
                    bookDAO.addBook(new Book(title, author, isbn, copies));
                    break;

                case 2:
                    List<Book> books = bookDAO.getAllBooks();
                    for (Book b : books) System.out.println(b);
                    break;

                case 3:
                    System.out.print("Book ID to update: ");
                    int updateId = sc.nextInt();
                    sc.nextLine();
                    System.out.print("New Title: ");
                    String newTitle = sc.nextLine();
                    System.out.print("New Author: ");
                    String newAuthor = sc.nextLine();
                    System.out.print("New ISBN: ");
                    String newIsbn = sc.nextLine();
                    System.out.print("New Copies: ");
                    int newCopies = sc.nextInt();
                    Book updatedBook = new Book(newTitle, newAuthor, newIsbn, newCopies);
                    updatedBook.setBookId(updateId);
                    bookDAO.updateBook(updatedBook);
                    break;

                case 4:
                    System.out.print("Book ID to delete: ");
                    int deleteId = sc.nextInt();
                    bookDAO.deleteBook(deleteId);
                    break;

                case 5:
                    System.out.print("Name: ");
                    String name = sc.nextLine();
                    System.out.print("Email: ");
                    String email = sc.nextLine();
                    System.out.print("Phone: ");
                    String phone = sc.nextLine();
                    memberDAO.addMember(new Member(name, email, phone));
                    break;

                case 6:
                    List<Member> members = memberDAO.getAllMembers();
                    for (Member m : members) System.out.println(m);
                    break;

                case 7:
                    System.out.print("Member ID to update: ");
                    int memUpdateId = sc.nextInt();
                    sc.nextLine();
                    System.out.print("New Name: ");
                    String newName = sc.nextLine();
                    System.out.print("New Email: ");
                    String newEmail = sc.nextLine();
                    System.out.print("New Phone: ");
                    String newPhone = sc.nextLine();
                    Member updatedMember = new Member(newName, newEmail, newPhone);
                    updatedMember.setMemberId(memUpdateId);
                    memberDAO.updateMember(updatedMember);
                    break;

                case 8:
                    System.out.print("Member ID to delete: ");
                    int memDeleteId = sc.nextInt();
                    memberDAO.deleteMember(memDeleteId);
                    break;

                case 9:
                    System.out.print("Book ID to issue: ");
                    int issueBookId = sc.nextInt();
                    System.out.print("Member ID: ");
                    int issueMemberId = sc.nextInt();
                    transactionDAO.issueBook(issueBookId, issueMemberId);
                    break;

                case 10:
                    System.out.print("Transaction ID to return: ");
                    int returnTxnId = sc.nextInt();
                    transactionDAO.returnBook(returnTxnId);
                    break;

                case 11:
                    List<String> issued = transactionDAO.getIssuedBooks();
                    if (issued.isEmpty()) {
                        System.out.println("No books currently issued.");
                    } else {
                        for (String line : issued) System.out.println(line);
                    }
                    break;

                case 12:
                    System.out.println("Exiting...");
                    sc.close();
                    return;

                default:
                    System.out.println("Invalid choice.");
            }
        }
    }
}