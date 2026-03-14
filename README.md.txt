# Library Management System

A console-based Library Management System built using **Java, JDBC, and MySQL**.

## Technologies Used

- Java
- JDBC
- MySQL
- VS Code

## Features

- Add Book
- View Books
- Update Book
- Delete Book
- Issue Book
- Return Book
- Search Book
- View Issued Books
- Student Borrow History
- Overdue Books
- Input Validation

## Database Tables

### Books Table

| Column | Type |
|------|------|
| book_id | INT (PK) |
| title | VARCHAR |
| author | VARCHAR |
| quantity | INT |

### Issued Books Table

| Column | Type |
|------|------|
| issue_id | INT (PK) |
| book_id | INT |
| student_id | INT |
| issue_date | DATE |
| return_date | DATE |

## How to Run

1. Install MySQL
2. Create database
3. Compile the project

javac -cp ".;lib/mysql-connector-j-9.6.0.jar" src/*.java


4. Run the project
java -cp ".;lib/mysql-connector-j-9.6.0.jar;src" Main


## Program Menu

![Library Menu](screenshots/menu-output.png)