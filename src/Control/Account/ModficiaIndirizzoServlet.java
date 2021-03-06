package Control.Account;

import Control.Eccezioni.ErroreSuiDati;
import Control.Eccezioni.MyServletException;
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
@WebServlet("/modificaindirizzo")
public class ModficiaIndirizzoServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String errore = "";
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
            String note = request.getParameter("note");
        if(note== null){
            errore = errore + "Valore note non ricevuto<br>";
        }
        if(errore.length()>2){
            throw new ErroreSuiDati("Sono stati trovati i seguenti errori!<br><br>" + errore);
        }
        HttpSession session = request.getSession();
        Cliente utente = (Cliente) session.getAttribute("utente");
        if(utente!=null && utente instanceof Cliente){
            ManagerAccount managerAccount =  new ManagerAccount();
            int capint = -1;
            try{
                capint = Integer.parseInt(cap);
            }catch (NumberFormatException e){
                throw new ErroreSuiDati("Cap non valido");
            }
            if(managerAccount.modificaIndirizzo(via,comune,provincia,note,capint,utente.getUsername())){
                request.setAttribute("avviso","Modifica indirizzo effettuata!");
                utente.getIndirizzo().setVia(via);
                utente.getIndirizzo().setCap(capint);
                utente.getIndirizzo().setNotecorriere(note);
                utente.getIndirizzo().setComune(comune);
                utente.getIndirizzo().setProvincia(provincia);
                RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/View/visualizza-profilo.jsp");
                dispatcher.forward(request, response);
            }else{
                throw new MyServletException("Errore generico!");
            }
        }
    }
}
