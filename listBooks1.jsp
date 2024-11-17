<%-- 
    Document   : listBooks
    Created on : Nov 17, 2024, 6:24:17 PM
    Author     : itzme
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Book List</title>
    <style>
        table {
            width: 70%;
            margin: auto;
            border-collapse: collapse;
        }
        th, td {
            padding: 10px;
            text-align: left;
            border-bottom: 1px solid #ddd;
        }
        th {
            background-color: #f2f2f2;
        }
        a {
            text-decoration: none;
            color: #007bff;
        }
        a:hover {
            text-decoration: underline;
        }
    </style>
    </head>
    <body>
        
        <<body>
    <h2 style="text-align: center;">Library Book List</h2>
    <table border="1">
        <tr>
            <th>Book ID</th>
            <th>Book Name</th>
            <th>Author Name</th>
            <th>Category</th>
            <th>Actions</th>
        </tr>
        <%
            List<Book> bookList = (List<Book>) request.getAttribute("bookList");
            if (bookList != null && !bookList.isEmpty()) {
                for (Book book : bookList) {
        %>
        <tr>
            <td><%= book.getBookId() %></td>
            <td><%= book.getBookName() %></td>
            <td><%= book.getAuthorName() %></td>
            <td><%= book.getCategory() %></td>
            <td>
                <a href="BookServlet?action=update&bookId=<%= book.getBookId() %>">Edit</a>
                |
                <a href="BookServlet?action=delete&bookId=<%= book.getBookId() %>">Delete</a>
            </td>
        </tr>
        <%
                }
            } else {
        %>
        <tr>
            <td colspan="5" style="text-align: center;">No books found.</td>
        </tr>
        <%
            }
        %>
    </table>
    <div style="text-align: center; margin-top: 20px;">
        <a href="addBook.jsp">Add New Book</a>
    </div>
</body>
</html>
