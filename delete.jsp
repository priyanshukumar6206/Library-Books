<%-- 
    Document   : delete
    Created on : Nov 17, 2024, 9:51:23 PM
    Author     : itzme
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
    <title>Delete Book</title>
</head>
<body>
    <h2>Delete Book</h2>
    <p>Are you sure you want to delete this book?</p>
    <form action="BookServlet?action=delete" method="post">
        <input type="hidden" name="bookId" value="${bookId}" />
        <input type="submit" value="Yes, Delete" />
        <a href="listBooks.jsp">No, Cancel</a>
    </form>
</body>
</html>

