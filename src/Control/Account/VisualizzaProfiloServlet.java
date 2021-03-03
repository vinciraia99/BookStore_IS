package Control.Account;

import Control.MyServletException;
import Entities.Account;
import Entities.Cliente;
import Manager.ManagerAccount;

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
 * @since 03/03/2021
 */

@WebServlet("/visualizzaprofilo")
public class VisualizzaProfiloServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        Account utente = (Account) session.getAttribute("utente");
        if(utente != null){
            if(utente.getTipo().equals("C")){
                ManagerAccount managerAccount = new ManagerAccount();
                Cliente cliente = managerAccount.recuperaCliente(utente);
                if(cliente!=null){
                    session.setAttribute("utente",cliente);
                }
            }
            RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/View/visualizza-profilo.jsp");
            dispatcher.forward(request,response);
        }else{
            throw new MyServletException("Utente non collegato, perfavore prima fai il login");
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {doGet(request,response);}
}
