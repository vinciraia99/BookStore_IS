package Control.Carrello;

import Control.Eccezioni.MyServletException;
import Entities.Carrello;
import Entities.Libro;
import Manager.ManagerCarrello;
import Manager.ManagerCategorie;
import Manager.ManagerLibri;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;

/**
 * @author Vincenzo Raia
 * @version 0.1
 * @since 02/03/2021
 */

@WebServlet("/modificaquantitacarrello")
public class ModificaQuantitaCarrelloServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Carrello carrello = (Carrello) session.getAttribute("carrello");
        ManagerLibri managerLibri = new ManagerLibri();
        ManagerCarrello managerCarrello = new ManagerCarrello();
        String isbn = request.getParameter("id");
        Libro libro = managerLibri.acquisisciLibro(isbn);
        if(libro==null){throw new MyServletException("Libro non aggiunto!");}
        int quantita = -1;
        try{
            quantita = Integer.parseInt(request.getParameter("quantita"));
        }
        catch (NumberFormatException er){
            throw new MyServletException(
                    "Errore aggiornamento quantità"
            );
        }
        if(quantita<= 0){
            quantita =1;
        }
        ArrayList<Carrello.LibroCarrello> libri = (ArrayList<Carrello.LibroCarrello>) carrello.getLibri();
        Libro libro1 = managerLibri.acquisisciLibro(isbn);
        if(libro1 != null){
            if(managerCarrello.modificaQuantitaCarrello(carrello,libro1,quantita)){
                float totale =0;
                for(Carrello.LibroCarrello c : carrello.getLibri()){
                    totale = totale + (c.getLibro().getPrezzo() * c.getQuantita());
                }
                request.getSession().setAttribute("carrello", carrello);
                response.setContentType("text/plain");
                int disponibili = -1;
                float totaleprodotto = -1;
                for(Carrello.LibroCarrello c : carrello.getLibri()){
                    if(c.getLibro().getIsbn().equals(libro1.getIsbn())){
                    disponibili = libro1.getQuantita();
                    totaleprodotto = c.getQuantita()*c.getLibro().getPrezzo();
                    break;}
                }
                response.getWriter().write("" + totale + " " +totaleprodotto + " " + disponibili);
            }
        }




    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

}
