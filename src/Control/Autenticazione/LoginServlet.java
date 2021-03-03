package Control.Autenticazione;


import Control.ErroreSuiDati;
import Entities.Account;
import Manager.ManagerAutenticazione;

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
 * @since 01/03/2021
 */

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession login = request.getSession();
        if(login.getAttribute("utente")!= null){
            response.sendRedirect("profilo");
        }
        else{
            RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/View/login.jsp");
            dispatcher.forward(request, response);
        }
    }


    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String username = request.getParameter("username");
        ManagerAutenticazione managerAutenticazione = new ManagerAutenticazione();
        Account utente = null;
        if(username != null){
            String password = request.getParameter("password");
            if(password == null || password.length() <1){
                throw new ErroreSuiDati("Password non valida");
            }

            utente = managerAutenticazione.login(username,password);
            if(utente == null){
                throw new ErroreSuiDati("Account non esistente o non abilitato");
            }


            }else if (username == null|| username.length() <1){
                throw new ErroreSuiDati("Username vuoto!");
            }

            HttpSession session = request.getSession();
            session.setAttribute("utente",utente);
            response.sendRedirect("visualizzaprofilo");

        }

    }

