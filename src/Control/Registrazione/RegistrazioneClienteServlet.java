package Control.Registrazione;

import Control.Eccezioni.ErroreSuiDati;
import Control.Eccezioni.MyServletException;
import Control.Eccezioni.RegistrazioneFallita;
import Entities.Account;
import Entities.Indirizzo;
import Manager.ManagerAccount;
import Manager.ManagerRegistrazione;

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
 * @since 04/03/2021
 */

@WebServlet("/registrazionecliente")
public class RegistrazioneClienteServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        Account utente = (Account) session.getAttribute("utente");
        if(utente==null){
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("WEB-INF/View/registrazione.jsp");
            requestDispatcher.forward(request, response);
        }else{
            response.sendRedirect("visualizzaprofilo");
        }


    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        Account utente = (Account) session.getAttribute("utente");
        if(utente==null){
            String errore = "";
            String nome = request.getParameter("nome");
            if(nome == null || nome.length()<2 || nome.length()>128 || nome.matches("[A-Za-z]{2,}$") == false){
                errore = errore + "Nome non valido o vuoto";
            }
            String cognome = request.getParameter("cognome");
            if(cognome == null || cognome.length()<2 || cognome.length()>128 || cognome.matches("[A-Za-z]{2,}$") == false){
                errore = errore + "Cognome non valido o vuoto";
            }
            String email = request.getParameter("email");
            if(email == null || email.length()<2 || email.length()>128 || email.matches("[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,3}$") == false){
                errore = errore + "Email non valida o vuota";
            }
            String username = request.getParameter("username");
            ManagerAccount managerAccount = new ManagerAccount();
            if(username == null || username.length()<2 || username.length()>32){
                errore = errore + "Username non valido o vuoto o già esistente";
            }
            if(managerAccount.recuperaAccount(username) != null){
                throw new RegistrazioneFallita("username già esistente");
            }
            String password = request.getParameter("password");
            if(password == null || password.length()<2 || password.length()>128 || password.matches("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z]).{8,32}$") == false){
                errore = errore + "La password é mal formata! La password deve essere lunga 8 caratteri e al massimo 32. Deve contenere una lettere maiuscola e una minuscola. Deve contenere un numero o password vuota";
            }
            String via = request.getParameter("via");
            if(via== null ||via.length()<2 || via.length()>128){
                errore = errore + "Via non valida o vuota<br>";
            }
            String comune = request.getParameter("comune");
            if(comune== null || comune.length()<2 || comune.length()>128 || comune.matches("[A-Za-z]{2,}") == false){
                errore = errore + "Comune non valido o vuoto<br>";
            }
            String provincia = request.getParameter("provincia");
            if(provincia== null || provincia.length()<2 || provincia.length()>128 || provincia.matches("[A-Za-z]{2,}") == false){
                errore = errore + "Provincia non valida o vuota<br>";
            }
            String cap = request.getParameter("cap");
            if(cap== null || cap.length()<2 || cap.length()>6 || cap.matches("[0-9]{2,6}") == false){
                errore = errore + "Cap non valido o vuoto<br>";
            }
            int intcap = -1;
            try{
                intcap = Integer.parseInt(cap);
            }catch (NumberFormatException e){
                errore = errore + "Cap non valido (deve contenere solo numeri)<br>";
            }
            String note = request.getParameter("note");
            if(note== null){
                errore = errore + "Valore note non ricevuto<br>";
            }
            if(errore.length()>2){
                throw new ErroreSuiDati("Sono stati trovati i seguenti errori!<br><br>" + errore);
            }
            Indirizzo indirizzo = new Indirizzo(via,comune,provincia,intcap,note);
            ManagerRegistrazione managerRegistrazione = new ManagerRegistrazione();
            if(managerRegistrazione.registraCliente(email,username,password,nome,cognome,indirizzo)){
                request.setAttribute("username",username);
                request.setAttribute("email",email);
                RequestDispatcher requestDispatcher = request.getRequestDispatcher("WEB-INF/View/conferma-registrazione.jsp");
                requestDispatcher.forward(request, response);
            }else{
                throw new MyServletException("Errore!");
            }

        }else{
            response.sendRedirect("visualizzaprofilo");
        }

    }
}
