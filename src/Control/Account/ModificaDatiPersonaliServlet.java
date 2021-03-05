package Control.Account;

import Control.ErroreSuiDati;
import Control.MyServletException;
import Entities.Account;
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

@WebServlet("/modificadatipersonali")
public class ModificaDatiPersonaliServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Account user = (Account) session.getAttribute("utente");
        String nome = request.getParameter("nome");
        String cognome = request.getParameter("cognome");
        String email = request.getParameter("email");
        if(user!= null){
            String error = "Sono stati trovati i seguenti errori:\n\n";
            response.setContentType("application/text");
            if(nome.length() == 0){
                error=error + "Il nome non può essere vuoto\n";
            }else if(!nome.matches("[a-zA-Z]+")){
                error=error + "Il nome deve contenere solo caratteri\n";
            }
            if(cognome.length() ==0){
                error=error + "Il cognome non può essere vuoto\n";
            }else if(!cognome.matches("[a-zA-Z]+")){
                error=error + "Il cognome deve contenere solo caratteri\n";
            }
            ManagerAccount managerAccount = new ManagerAccount();
            if(email.length()==0){
                error=error + "L'email non può essere vuota<br>";
            }else if(!email.matches("^\\w+([\\.-]?\\w+)*@\\w+([\\.-]?\\w+)*(\\.\\w+)+$")){
                error=error + "L'email é mal formata, l'email deve contere una @ e un . ad esempio: \"example@email.com\"<br>";
            }
            if(error.length() > 44){
                throw new ErroreSuiDati("Sono stati trovati i seguenti errori!" + error);
            }else{
                user.setNome(nome);
                user.setCognome(cognome);
                user.setEmail(email);
                if(managerAccount.modificaDatiPersonali(email,user.getUsername(),nome,cognome)){
                    session.setAttribute("utente",user);
                    request.setAttribute("avviso","Dati aggiornati!");
                }else{
                    request.setAttribute("avviso","Errore generico");
                }

                RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/View/visualizza-profilo.jsp");
                dispatcher.forward(request, response);

            }
        }else{
            throw new MyServletException("Errore!");
        }
    }
}
