package Control.Libri;

import Control.MyServletException;
import Entities.Account;
import Entities.Categoria;
import Entities.Libro;
import Manager.ManagerCategorie;
import Manager.ManagerLibri;

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

@WebServlet("/editlibro")

public class EditLibroServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        throw new MyServletException("Metodo non permesso");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        Account user = (Account) session.getAttribute("account");
        if (user != null && user.getAbilitato() == true) {
            ManagerCategorie categorie = new ManagerCategorie();
            ManagerLibri libri = new ManagerLibri();
            List<Categoria> list = categorie.acquisisciTutteLeCategorie();
            String isbn = request.getParameter("id");
            Libro libro = null;
            request.setAttribute("titolo","Modifica libro");
            ManagerLibri manager = new ManagerLibri();
            libro = manager.acquisisciLibro(isbn);
            if(libro ==null){
                throw new MyServletException("Libro non trovato");
            }
            request.setAttribute("libro",libro);
            int f = list.size();
            Iterator<Categoria> iter1 = list.iterator();
            Categoria current = null;
            while (iter1.hasNext()) {//iterazione sulle categorie da rimuovere
                current = iter1.next();
                for(int i=0;i<libro.getCategorie().size();i++){
                    if(current.getNome().equals(libro.getCategorie().get(i).getNome())){
                        iter1.remove();
                        break;
                    }
                }
            }
            request.setAttribute("cecked",libro.getCategorie());
            request.setAttribute("categorie",list);

            RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/modifica-libro.jsp");
            dispatcher.forward(request,response);
        }else{
            throw new MyServletException("Sezione dedicata ai soli amministratori, perfavore prima fai il login");
        }

    }
}
