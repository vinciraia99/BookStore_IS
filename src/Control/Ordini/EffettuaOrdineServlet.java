package Control.Ordini;

import Control.Eccezioni.MyServletException;
import Entities.*;
import Manager.ManagerAccount;
import Manager.ManagerAutenticazione;
import Manager.ManagerOrdini;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

/**
 * @author Vincenzo Raia
 * @version 0.1
 * @since 06/03/2021
 */


@WebServlet("/effettuaordine")
public class EffettuaOrdineServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        Account utente = (Account) session.getAttribute("utente");
        Carrello carrello = (Carrello) session.getAttribute("carrello");
        if(utente != null && utente.getTipo().equals("C")){
            if(carrello != null && carrello.getTotaleProdotti()>0){
                RequestDispatcher requestDispatcher = request.getRequestDispatcher("WEB-INF/View/effettua-pagamento.jsp");
                requestDispatcher.forward(request, response);
            }else{
                throw new MyServletException("Errore");
            }


        }else {
            throw new MyServletException("Errore");
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        Account utente = (Account) session.getAttribute("utente");
        Carrello carrello = (Carrello) session.getAttribute("carrello");
        if(utente != null && utente.getTipo().equals("C")){
            if(carrello != null && carrello.getTotaleProdotti()>0){
                ManagerOrdini managerOrdini = new ManagerOrdini();
                ManagerAccount managerAccount = new ManagerAccount();
                Cliente cliente = managerAccount.recuperaCliente(utente);
                int id = managerOrdini.effettuaOrdine(cliente,carrello);
                    if(id!=-1){
                   response.sendRedirect("visualizzadettagliordine?id="+id);
                }else{
                    throw new MyServletException("Errore");
                }


            }else{
                throw new MyServletException("Errore");
            }


        }else {
            throw new MyServletException("Errore");
        }

    }
}
