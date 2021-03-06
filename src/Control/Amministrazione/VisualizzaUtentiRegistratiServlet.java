package Control.Amministrazione;

import Control.Eccezioni.MyServletException;
import Entities.Account;
import Manager.ManagerAmministrazione;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

/**
 * @author Vincenzo Raia
 * @version 0.1
 * @since 06/03/2021
 */
@WebServlet("/visualizzautenti")
public class VisualizzaUtentiRegistratiServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        Account utente = (Account) session.getAttribute("utente");
        if(utente != null && utente.getTipo().equals("M")){
            ManagerAmministrazione managerAmministrazione = new ManagerAmministrazione();
            List<Account> accounts = managerAmministrazione.visualizzaUtentiRegistrati();
            Iterator<Account> a = accounts.iterator();
            while(a.hasNext()){
                Account account= a.next();
                if(account.getUsername().equals(utente.getUsername())){
                    a.remove();
                }
            }
            request.setAttribute("utenti",accounts);
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("WEB-INF/View/visualizza-clienti.jsp");
            requestDispatcher.forward(request, response);
        }else {
            throw new MyServletException("Errore");
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request,response);
    }
}
