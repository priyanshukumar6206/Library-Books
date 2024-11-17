/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package com.library.servlet;

import com.library.dao.BookDAO;
import com.library.model.Book;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

/**
 *
 * @author itzme
 */
@WebServlet(urlPatterns = {"/BookServlet"})
public class BookServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    private BookDAO bookDAO;

    @Override
    public void init() {
        bookDAO = new BookDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        try {
            switch (action) {
                case "new":
                    request.getRequestDispatcher("addBook.jsp").forward(request, response);
                    break;
                case "edit":
                    int bookId = Integer.parseInt(request.getParameter("id"));
                    Book existingBook = bookDAO.getAllBooks().stream().filter(b -> b.getBookId() == bookId).findFirst().orElse(null);
                    request.setAttribute("book", existingBook);
                    request.getRequestDispatcher("updateBook.jsp").forward(request, response);
                    break;
                case "delete":
                    bookDAO.deleteBook(Integer.parseInt(request.getParameter("id")));
                    response.sendRedirect("list");
                    break;
                default:
                    List<Book> bookList = bookDAO.getAllBooks();
                    request.setAttribute("bookList", bookList);
                    request.getRequestDispatcher("listBooks.jsp").forward(request, response);
                    break;
            }
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        try {
            String bookName = request.getParameter("bookName");
            String authorName = request.getParameter("authorName");
            String category = request.getParameter("category");

            if ("insert".equals(action)) {
                Book newBook = new Book();
                newBook.setBookName(bookName);
                newBook.setAuthorName(authorName);
                newBook.setCategory(category);
                bookDAO.addBook(newBook);
            } else if ("update".equals(action)) {
                int bookId = Integer.parseInt(request.getParameter("bookId"));
                Book updatedBook = new Book();
                updatedBook.setBookId(bookId);
                updatedBook.setBookName(bookName);
                updatedBook.setAuthorName(authorName);
                updatedBook.setCategory(category);
                bookDAO.updateBook(updatedBook);
            }
            response.sendRedirect("list");
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }
}
    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
