/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author itzme
 */
package com.library.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import com.library.model.Book;

public class BookDAO {
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/library_db";
    private static final String JDBC_USER = "root";
    private static final String JDBC_PASSWORD = "";

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);
    }

    // Create (Insert)
    public void addBook(Book book) throws SQLException {
        String query = "INSERT INTO books (BookName, AuthorName, Category) VALUES (?, ?, ?)";
        try (Connection con = getConnection(); PreparedStatement ps = con.prepareStatement(query)) {
            ps.setString(1, book.getBookName());
            ps.setString(2, book.getAuthorName());
            ps.setString(3, book.getCategory());
            ps.executeUpdate();
        }
    }

    // Read (Retrieve all books)
    public List<Book> getAllBooks() throws SQLException {
        List<Book> books = new ArrayList<>();
        String query = "SELECT * FROM books";
        try (Connection con = getConnection(); Statement stmt = con.createStatement(); ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                Book book = new Book();
                book.setBookId(rs.getInt("BookId"));
                book.setBookName(rs.getString("BookName"));
                book.setAuthorName(rs.getString("AuthorName"));
                book.setCategory(rs.getString("Category"));
                books.add(book);
            }
        }
        return books;
    }

    // Update
    public void updateBook(Book book) throws SQLException {
        String query = "UPDATE books SET BookName = ?, AuthorName = ?, Category = ? WHERE BookId = ?";
        try (Connection con = getConnection(); PreparedStatement ps = con.prepareStatement(query)) {
            ps.setString(1, book.getBookName());
            ps.setString(2, book.getAuthorName());
            ps.setString(3, book.getCategory());
            ps.setInt(4, book.getBookId());
            ps.executeUpdate();
        }
    }

    // Delete
    public void deleteBook(int bookId) throws SQLException {
        String query = "DELETE FROM books WHERE BookId = ?";
        try (Connection con = getConnection(); PreparedStatement ps = con.prepareStatement(query)) {
            ps.setInt(1, bookId);
            ps.executeUpdate();
        }
    }
}
