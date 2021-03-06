package Control.Categorie;

import Control.Eccezioni.MyServletException;
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

@WebServlet("/eliminacategoria")
public class EliminaCategoriaServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Account account = (Account) session.getAttribute("utente");
        if(account!=null && (!account.getTipo().equals("C"))){
            String idstring = request.getParameter("id");
            int id = -1;
            if(idstring!=null){
                try {
                    id = Integer.parseInt(idstring);
                }catch (NumberFormatException e){
                    throw new MyServletException("Categoria non esistente!");
                }
            }else{
                throw new MyServletException("Categoria non esistente!");
            }
            List<Categoria> categoriaList = (List<Categoria>)  getServletContext().getAttribute("categorie");
            boolean flag = false;
            Categoria categoria = null;
            for(Categoria c : categoriaList){
                if(c.getId() == id){
                    flag = true;
                    categoria = c;
                    break;
                }
            }
            if(flag) {
               ManagerCategorie managerCategorie = new ManagerCategorie();
               if(managerCategorie.eliminaCategoria(categoria.getId())){
                   categoriaList.remove(categoria);
                   getServletContext().setAttribute("categorie", categoriaList);
                   RequestDispatcher requestDispatcher = request.getRequestDispatcher("WEB-INF/View/rimuovi-categoria.jsp");
                   requestDispatcher.forward(request, response);
               }else{
                   throw new MyServletException("Categoria non esistente!");
               }
            }else {
                throw new MyServletException("Categoria non esistente!");
            }
        }else{
            throw new MyServletException("Sezione privata!");
        }
    }
}
