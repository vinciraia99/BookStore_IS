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
@WebServlet("/disabilitautente")
public class DisabilitaUtenteServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        Account utente = (Account) session.getAttribute("utente");
        String id = request.getParameter("id");
        String flag = request.getParameter("flag");
        if(id!= null && flag !=null && utente != null && utente.getTipo().equals("M")){
            ManagerAmministrazione managerAmministrazione = new ManagerAmministrazione();
            boolean ok = false;
            if(flag.equals("1")){
                if(managerAmministrazione.abilitaCliente(id)) ok=true;
            }else if (flag.equals("0")){
                if(managerAmministrazione.disabilitaCliente(id)) ok=true;
            }else {
                throw new MyServletException("Errore");
            }
            if(ok) response.sendRedirect("visualizzautenti");
            else throw new MyServletException("Errore");
        }else {
            throw new MyServletException("Errore");
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request,response);
    }
}
