CREATE DATABASE library_db;
USE library_db;

CREATE TABLE books (
                       book_id INT AUTO_INCREMENT PRIMARY KEY,
                       title VARCHAR(100) NOT NULL,
                       author VARCHAR(100),
                       isbn VARCHAR(20),
                       available_copies INT DEFAULT 1
);

CREATE TABLE members (
                         member_id INT AUTO_INCREMENT PRIMARY KEY,
                         name VARCHAR(100) NOT NULL,
                         email VARCHAR(100),
                         phone VARCHAR(15)
);

CREATE TABLE transactions (
                              txn_id INT AUTO_INCREMENT PRIMARY KEY,
                              book_id INT,
                              member_id INT,
                              issue_date DATE,
                              return_date DATE,
                              status VARCHAR(20) DEFAULT 'ISSUED',
                              FOREIGN KEY (book_id) REFERENCES books(book_id),
                              FOREIGN KEY (member_id) REFERENCES members(member_id)
);