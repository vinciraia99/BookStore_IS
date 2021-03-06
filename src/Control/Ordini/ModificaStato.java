package Control.Ordini;

import Control.Eccezioni.MyServletException;
import Entities.Account;
import Entities.Carrello;
import Entities.Cliente;
import Manager.ManagerAccount;
import Manager.ManagerOrdini;

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
 * @since 06/03/2021
 */


@WebServlet("/modificastato")
public class ModificaStato extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        Account utente = (Account) session.getAttribute("utente");
        String id = request.getParameter("id");

        if(id!= null && utente != null && utente.getTipo().equals("M")){
            int idint = -1;
            try {
                idint = Integer.parseInt(id);
            }catch (NumberFormatException e){
                throw new MyServletException("Errore");
            }

            ManagerOrdini managerOrdini= new ManagerOrdini();
            if(managerOrdini.modificaStatoOrdine(idint,true)){
                response.sendRedirect("visualizzadettagliordineutente?id="+id);
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
