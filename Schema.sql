CREATE DATABASE LibraryDB;

USE LibraryDB;

CREATE TABLE Books (
    BookId INT PRIMARY KEY AUTO_INCREMENT,
    BookName VARCHAR(100) NOT NULL,
    AuthorName VARCHAR(100) NOT NULL,
    Category VARCHAR(50) NOT NULL
);
