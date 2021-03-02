package Control;

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
import java.io.IOException;
import java.util.List;

@WebServlet(name = "HomeServlet", urlPatterns="", loadOnStartup=1)
public class HomeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public void init() throws ServletException {
		ManagerCategorie managerCategorie = new ManagerCategorie();
		List<Categoria> categorie = managerCategorie.acquisisciTutteLeCategorie();
		getServletContext().setAttribute("categorie", categorie);
		super.init();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ManagerLibri managerLibri = new ManagerLibri();
		List<Libro> libri = managerLibri.acquisisciTuttiILibri();
		request.setAttribute("libri", libri);
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("WEB-INF/View/index.jsp");
		requestDispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
