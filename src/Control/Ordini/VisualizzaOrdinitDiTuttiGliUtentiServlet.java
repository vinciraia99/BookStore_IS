package Control.Ordini;

import Control.Eccezioni.MyServletException;
import Entities.Account;
import Entities.Ordine;
import Manager.ManagerOrdini;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

/**
 * @author Vincenzo Raia
 * @version 0.1
 * @since 06/03/2021
 */

@WebServlet("/visualizzaordinidituttigliutenti")
public class VisualizzaOrdinitDiTuttiGliUtentiServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        Account account = (Account) session.getAttribute("utente");
        if(account!= null && account.getTipo().equals("M")){
            ManagerOrdini managerOrdini = new ManagerOrdini();
            List<Ordine> ordini = managerOrdini.visualizzaOrdiniUtenti();
            if(ordini!= null){
                request.setAttribute("ordini",ordini);
                RequestDispatcher requestDispatcher = request.getRequestDispatcher("WEB-INF/View/visualizza-ordini-clienti.jsp");
                requestDispatcher.forward(request, response);
            }else{
                throw new MyServletException("Ordine non esistente");
            }
        }else{
            throw new MyServletException("Accesso non autorizzato");
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request,response);
    }
}
