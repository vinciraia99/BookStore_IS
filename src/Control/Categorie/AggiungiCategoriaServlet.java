package Control.Categorie;

import Control.ErroreSuiDati;
import Control.MyServletException;
import Entities.Account;
import Entities.Categoria;
import Manager.ManagerCategorie;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

/**
 * @author Vincenzo Raia
 * @version 0.1
 * @since 04/03/2021
 */

@WebServlet("/aggiungicategoria")
public class AggiungiCategoriaServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Account account = (Account) session.getAttribute("utente");
        if(account!=null && (!account.getTipo().equals("C"))){
            String errore = "";
            String titolo = request.getParameter("titolo");
            if(titolo== null || titolo.length()<2 || titolo.length()>100){
                    errore = "Titolo non valido o vuoto!<br>";
            }
            String descrizione = request.getParameter("descrizione");
            if(descrizione== null || descrizione.length()<2){
                errore = "Descrizione non valida o vuota!<br>";
            }
            if(errore.length()>2){
                throw new ErroreSuiDati("Sono stati trovati i seguenti errori:<br><br>" + errore);
            }

            ManagerCategorie managerCategorie = new ManagerCategorie();
            if(managerCategorie.aggiungiCategoria(titolo,descrizione)){
                boolean flag = true;
                List<Categoria> categoriaList = managerCategorie.acquisisciTutteLeCategorie();
                categoriaList = managerCategorie.acquisisciTutteLeCategorie();
                for(Categoria c : categoriaList){
                    if(c.getNome().equals(titolo)){
                        flag=false;
                        getServletContext().setAttribute("categorie", categoriaList);
                        response.sendRedirect("visualizzacategoria?id="+c.getId());
                    }
                }
                if(flag){ throw new MyServletException("Errore generico");}
            }else{
                errore="Categoria già esistente!";
                throw new ErroreSuiDati("Sono stati trovati i seguenti errori:<br><br>" + errore);
            }
        }else{
            throw new MyServletException("Sezione privata!");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      HttpSession session = request.getSession();
        Account account = (Account) session.getAttribute("utente");
        if(account!=null && (!account.getTipo().equals("C"))){
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("WEB-INF/View/aggiungi-categoria.jsp");
            requestDispatcher.forward(request, response);
        }else{
            throw new MyServletException("Sezione privata!");
        }
    }
}
