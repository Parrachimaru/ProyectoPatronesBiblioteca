package com.edu.unipiloto.servidor;

import com.edu.unipiloto.libro.Libro;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.edu.unipiloto.libro.LibroDAOImplementation;
import java.util.List;
import javax.servlet.RequestDispatcher;

public class BibliotecaServlet extends HttpServlet {
    

    private LibroDAOImplementation libroDAO;
    
        
    @Override
    public void init() throws ServletException {
        super.init();
        libroDAO = new LibroDAOImplementation();
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet BibliotecaServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet BibliotecaServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        List<Libro> listaLibros = libroDAO.getAllLibros();
        request.setAttribute("listaLibros", listaLibros);

        


        RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
        dispatcher.forward(request, response);
        
        processRequest(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        processRequest(request, response);

        RequestDispatcher dispatcher = request.getRequestDispatcher("next.jsp");
        dispatcher.forward(request, response);

    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
