# Library Management System

A full-stack Library Management System built with Core Java, JDBC, MySQL, and a web-based frontend using HTML, CSS, JavaScript, and jQuery.

## Features

- **Book Management** — Add, view, update, and delete books
- **Member Management** — Add, view, update, and delete library members
- **Issue / Return System** — Issue books to members and process returns with automatic copy tracking
- **Transaction Reports** — View all currently issued books with member details via SQL JOIN queries
- **REST API** — Backend API built with Spark Java serving JSON responses
- **Web Frontend** — Responsive UI built with HTML, CSS, JavaScript, and jQuery

## Tech Stack

| Layer | Technology |
|-------|-----------|
| Language | Core Java |
| Database | MySQL |
| DB Connectivity | JDBC (PreparedStatements) |
| Backend API | Spark Java |
| JSON Handling | Gson |
| Frontend | HTML, CSS, JavaScript, jQuery |
| Build Tool | Maven |

## Database Schema

Three relational tables with foreign key constraints:
- `books` — book inventory
- `members` — library members
- `transactions` — issue/return records linking books and members

## 📁 Project Structure

    src/main
    ├── java/com/library
    │   ├── Main.java                   # Console-based entry point
    │   ├── LibraryAPI.java             # REST API + web server entry point
    │   ├── dao/
    │   │   ├── BookDAO.java            # CRUD operations for books
    │   │   ├── MemberDAO.java          # CRUD operations for members
    │   │   └── TransactionDAO.java     # Issue/Return logic
    │   ├── model/
    │   │   ├── Book.java
    │   │   ├── Member.java
    │   │   └── Transaction.java
    │   └── util/
    │       └── DBConnection.java       # Database connection utility
    └── resources/
        ├── public/
        │   └── index.html              # Web frontend (HTML+CSS+JS+jQuery)
        └── db.properties               # DB credentials (not committed to Git)

## Setup Instructions

1. Clone this repository
2. Create the MySQL database using `schema.sql`
3. Copy `db.properties.example` to `db.properties` and fill in your MySQL credentials
4. Run `LibraryAPI.java` to start the web server
5. Open `http://localhost:4567` in your browser

## What I Learned

- Implementing the DAO pattern for clean separation between business logic and database access
- Writing parameterized SQL queries to prevent SQL injection
- Designing normalized relational schemas with foreign key relationships
- Building a REST API with Spark Java serving JSON to a frontend
- Connecting a jQuery frontend to a Java backend via AJAX calls
- Managing multi-table transactions across books and transactions tables