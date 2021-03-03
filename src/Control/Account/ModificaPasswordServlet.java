package Control.Account;

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

@WebServlet("/cambiapassword")
public class ModificaPasswordServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Account user = (Account) session.getAttribute("utente");
        String expassword = request.getParameter("expassword");
        String password = request.getParameter("passwordsubmit");
        String passwordconfirm = request.getParameter("passwordsubmitconfirm");
        if(user!= null){
            String error = "Sono stati trovati i seguenti errori:\n\n";
            response.setContentType("application/text");
            if(expassword.equals(user.getPassword()) == false) {
                error = error + "La password corrente non é corretta!\n";
            }
            if(password.length() ==0){
                error=error + "La password non può essere vuota\n";
            }else if(!password.matches("(.*\\d)(?=.*[a-z])(?=.*[A-Z]).{8,}")){
                error=error + "La password é mal formata.<br>&nbsp;&nbsp;&nbsp;La password deve essere lunga 8 caratteri e al massimo 32\n&nbsp;&nbsp;&nbsp;Deve contenere una lettere maiuscola e una minuscola\n&nbsp;&nbsp;&nbsp;Deve contenere un numero\n";
            }else if(!password.equals(passwordconfirm)){
                error=error + "La password e la conferma password sono diverse<br>";
            }
            if(error.length() > 44){
                request.setAttribute("avviso",error);
            }else{
                ManagerAccount managerAccount =  new ManagerAccount();
                if(managerAccount.modificaPassword(user.getUsername(),password,expassword)){
                    request.setAttribute("avviso","Modifica effettuata!");
                }else{
                    request.setAttribute("avviso","Errore generico");
                }
            }
            RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/View/visualizza-profilo.jsp");
            dispatcher.forward(request,response);
        }
    }
}
