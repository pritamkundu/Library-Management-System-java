# Library Management System (Java + MySQL)

A console-based Library Management System built with Java and JDBC, demonstrating CRUD operations, relational database design, and transaction management.

## Features

- **Book Management**: Add, view, update, and delete books
- **Member Management**: Add, view, update, and delete library members
- **Issue/Return System**: Issue books to members and process returns, with automatic tracking of available copies
- **Transaction Reports**: View all currently issued books with member details via SQL JOIN queries

## Tech Stack

- **Language**: Java
- **Database**: MySQL
- **Connectivity**: JDBC (PreparedStatements for SQL injection prevention)
- **Architecture**: DAO (Data Access Object) design pattern

## Database Schema

Three relational tables with foreign key constraints:
- `books` — book inventory
- `members` — library members
- `transactions` — issue/return records linking books and members

## Setup Instructions

1. Clone this repository
2. Create the MySQL database using the schema in `schema.sql`
3. Copy `db.properties.example` to `db.properties` and update with your MySQL credentials
4. Run `Main.java`

## What I Learned

- Implementing the DAO pattern for clean separation between business logic and database access
- Writing parameterized SQL queries to prevent SQL injection
- Designing normalized relational schemas with foreign key relationships
- Managing multi-table transactions (e.g., issuing a book updates both `transactions` and `books` tables atomically)