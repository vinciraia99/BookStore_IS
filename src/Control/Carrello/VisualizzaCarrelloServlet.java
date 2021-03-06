package Control.Carrello;

import Control.Eccezioni.MyServletException;
import Entities.Account;
import Entities.Carrello;

import javax.servlet.RequestDispatcher;
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
 * @since 02/03/2021
 */

@WebServlet("/visualizzacarrello")
public class VisualizzaCarrelloServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Carrello carrello = (Carrello) session.getAttribute("carrello");
        Account utente = (Account) session.getAttribute("utente");
        if(utente == null || (utente!=null && utente.getTipo().equals("C"))){
            if(carrello!=null){
                float totale =0;
                for(Carrello.LibroCarrello c : carrello.getLibri()){
                    totale = totale + (c.getLibro().getPrezzo() * c.getQuantita());
                }
                request.setAttribute("totale",totale);
            }
        }else{
            throw new MyServletException("Non sei un cliente");
        }

        RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/View/visualizza-carrello.jsp");
        dispatcher.forward(request, response);

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

}
