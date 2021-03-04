package Control.Libri;

import Control.MyServletException;
import Entities.Account;
import Manager.ManagerLibri;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @author Raffaele Scarpa
 * @version 0.1
 * @since 04/03/2021
 */

@WebServlet("/deletelibro")
public class EliminaLibroServlet extends HttpServlet {


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        throw new MyServletException("Metodo non permesso");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Account user = (Account) session.getAttribute("utente");
        if (user != null && user.getAbilitato() == true) {
            String isbn = request.getParameter("id");
            ManagerLibri libri = new ManagerLibri();
            if(libri.eliminaLibro(isbn)){
                RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/View/cancella-libro.jsp");
                dispatcher.forward(request, response);
            }else new MyServletException("Errore nella rimozione del libro");
        } else {
            throw new MyServletException("Sezione dedicata ai soli amministratori, perfavore prima fai il login");
        }
    }
}