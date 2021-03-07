package Control.Carrello;

import Control.Eccezioni.MyServletException;
import Entities.Carrello;
import Entities.Libro;
import Manager.ManagerCarrello;
import Manager.ManagerLibri;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @author Vincenzo Raia
 * @version 0.1
 * @since 07/03/2021
 */

@WebServlet("/aggiungicarrello")
public class AggiungiLibroCarrelloServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Carrello carrello = (Carrello) session.getAttribute("carrello");
        ManagerLibri managerLibri = new ManagerLibri();
        String isbn = request.getParameter("id");
        Libro libro = managerLibri.acquisisciLibro(isbn);
        if(libro==null){throw new MyServletException("Libro non aggiunto!");}
        ManagerCarrello managerCarrello = new ManagerCarrello();
        if(carrello!=null){
            if(managerCarrello.aggiungiLibroCarrello(carrello,libro) == false){
                throw new MyServletException("Libro non aggiunto!");
            }
        }else{
            if(isbn != null && isbn.length()>1){
                carrello = new Carrello();
                if(managerCarrello.aggiungiLibroCarrello(carrello,libro) == false){
                    throw new MyServletException("Libro non aggiunto!");
                }
                session.setAttribute("carrello",carrello);
            }else{
                throw new MyServletException("Libro non aggiunto!");
            }

        }

        response.sendRedirect("visualizzacarrello");

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

}
