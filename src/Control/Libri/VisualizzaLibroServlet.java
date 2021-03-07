package Control.Libri;


import Control.Eccezioni.MyServletException;
import Entities.Autore;
import Entities.Categoria;
import Entities.Libro;
import Manager.ManagerLibri;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Calendar;
import java.util.List;

/**
 * @author Vincenzo Raia
 * @version 0.1
 * @since 01/03/2021
 */

@WebServlet("/visualizzalibro")
public class VisualizzaLibroServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String isbn = request.getParameter("id");
        if(isbn == null){
            throw new MyServletException("Libro non esistente");
        }
        if(isbn.length()<=0){
            throw new MyServletException("Libro non esistente");
        }
        ManagerLibri managerLibri = new ManagerLibri();
        Libro libro =managerLibri.acquisisciLibro(isbn);
        boolean flag = true;
        if(libro!=null){
            List<Categoria> categoriaList = libro.getCategorie();
            String categoriastring = "";
            for(Categoria c : categoriaList){
                if(flag){
                    categoriastring = c.getNome();
                    flag = false;
                }else{
                    categoriastring = categoriastring + "," + c.getNome();
                }
            }
            String autori = "";
            flag = true;
            for(Autore e : libro.getAutori()){
                if(flag){
                    autori = e.getnomecompleto();
                    flag = false;
                }else{
                    autori = autori + "," + e.getnomecompleto();
                }
            }
            String annopubblciazione = String.valueOf(libro.getData_pubblicazione().get(Calendar.YEAR));
            request.setAttribute("autori",autori);
            request.setAttribute("categorieassociate",categoriastring);
            request.setAttribute("libro", libro);
            request.setAttribute("annopubblicazione", annopubblciazione);
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("WEB-INF/View/visualizza-libro.jsp");
            requestDispatcher.forward(request, response);
        }else{
            throw new MyServletException("Libro non esistente");
        }


    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

}