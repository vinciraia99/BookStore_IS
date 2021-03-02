package Control.Categorie;

import Control.MyServletException;
import Entities.Categoria;
import Manager.ManagerCategorie;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @author Vincenzo Raia
 * @version 0.1
 * @since 01/03/2021
 */

@WebServlet("/visualizzacategoria")
public class VisualizzaCategoriaServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id;
        try{
            id = Integer.parseInt((String) request.getParameter("id"));
        }catch (NumberFormatException e){
            throw new MyServletException("Categoria non esistente");
        }
        ManagerCategorie categorie = new ManagerCategorie();
        Categoria categoria = categorie.acquisisciCategoria(id);
        if(categoria!=null){
            request.setAttribute("categoria",categoria);
            request.setAttribute("libri",categoria.getLibri());
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("WEB-INF/View/visualizza-categoria.jsp");
            requestDispatcher.forward(request, response);
        }else{
            throw new MyServletException("Categoria non esistente");
        }



    }


}
