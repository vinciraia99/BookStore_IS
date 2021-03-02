package Control.Libri;


import Entities.Libro;
import Manager.ManagerLibri;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @author Vincenzo Raia
 * @version 0.1
 * @since 01/03/2021
 */

@WebServlet("/cercalibro")
public class CercaLibroServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String parolachiave = request.getParameter("q");
        ManagerLibri managerLibri = new ManagerLibri();
        List<Libro> libri = managerLibri.cercaLibro(parolachiave);
        request.setAttribute("libri", libri);
        request.setAttribute("parolachiave",parolachiave);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("WEB-INF/View/ricerca-libro.jsp");
        requestDispatcher.forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }
}
