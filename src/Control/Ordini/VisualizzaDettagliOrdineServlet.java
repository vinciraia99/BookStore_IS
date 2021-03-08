package Control.Ordini;

import Control.Eccezioni.MyServletException;
import Entities.Account;
import Entities.Libro;
import Entities.LibroOrdinato;
import Entities.Ordine;
import Manager.ManagerAccount;
import Manager.ManagerLibri;
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

/**
 * @author Vincenzo Raia
 * @version 0.1
 * @since 06/03/2021
 */

@WebServlet("/visualizzadettagliordine")
public class VisualizzaDettagliOrdineServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        String id = request.getParameter("id");
        Account account = (Account) session.getAttribute("utente");
        if(id != null && account!= null && account.getTipo().equals("C")){
            ManagerOrdini managerOrdini = new ManagerOrdini();
            ManagerAccount managerAccount = new ManagerAccount();
            int idint=-1;
            try {
                idint = Integer.parseInt(id);
            }catch (NumberFormatException e){
                throw new MyServletException("Ordine non valido");
            }
            Ordine ordine =  managerOrdini.visualizzaDettaglioOrdine(managerAccount.recuperaCliente(account),idint);
            if(ordine!= null){
                request.setAttribute("ordine",ordine);
                ArrayList<Libro> libri = new ArrayList<>();
                ManagerLibri managerLibri= new ManagerLibri();
                for(LibroOrdinato l : ordine.getLibriOrdinati()){
                    Libro lib = managerLibri.acquisisciLibro(l.getIsbn());
                    libri.add(lib);
                }
                String data= ordine.getDataDiAcquisto().get(GregorianCalendar.DATE) + "-" + ((int)ordine.getDataDiAcquisto().get(GregorianCalendar.MONTH)+1) + "-" + ordine.getDataDiAcquisto().get(GregorianCalendar.YEAR);
                request.setAttribute("data",data);
                request.setAttribute("libri",libri);
                RequestDispatcher requestDispatcher = request.getRequestDispatcher("WEB-INF/View/visualizza-dettaglio-ordine-effettuato.jsp");
                requestDispatcher.forward(request, response);
            }else{
                throw new MyServletException("Ordine non esistente");
            }
        }else{
            throw new MyServletException("Accesso non autorizzato");
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request,response);
    }
}
