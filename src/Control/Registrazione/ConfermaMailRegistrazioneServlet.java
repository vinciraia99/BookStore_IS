package Control.Registrazione;

import Control.MyServletException;
import Entities.Account;
import Manager.ManagerAccount;
import Manager.ManagerAutenticazione;
import Manager.ManagerRegistrazione;

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
 * @since 04/03/2021
 */
@WebServlet("/confermaregistrazione")
public class ConfermaMailRegistrazioneServlet  extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String username = request.getParameter("username");
        String email = request.getParameter("email");
        if(username == null || email == null){
            throw new MyServletException("Errore parametri");
        }
        ManagerRegistrazione managerRegistrazione = new ManagerRegistrazione();
        if(managerRegistrazione.confermaRegistrazione(username,email)){
            ManagerAccount managerAccount = new ManagerAccount();
            Account utente = managerAccount.recuperaAccount(username);
            HttpSession session = request.getSession();
            session.setAttribute("utente",utente);
            response.sendRedirect("visualizzaprofilo");
        }else{
            throw new MyServletException("Errore parametri");
        }

    }


}
