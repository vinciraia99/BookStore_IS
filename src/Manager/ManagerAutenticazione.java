package Manager;

import DAO.ClienteDAO;
import DAO.ManagerDAO;
import DAO.ResponsabileCatalogoDAO;
import Entities.Account;

import javax.servlet.http.HttpSession;

/**
 * @author Vincenzo Raia
 * @version 0.1
 * @since 30/01/2021
 */

public class ManagerAutenticazione {

    public ManagerAutenticazione(){

    }

    /**
     * Questo metodo permette di effettuare l’accesso
     * @param username (String) username il dell'utente che vuole loggarsi,
     * @param password (String) password corrispondente all'email dell'utente che vuole loggarsi.
     * @return oggetto Account corrispondente a chi si è loggato, null altrimenti.
     */
    public Account login(String username,String password){
        ClienteDAO clienteDAO = new ClienteDAO();
        ManagerDAO managerDAO = new ManagerDAO();
        ResponsabileCatalogoDAO responsabileCatalogoDAO = new ResponsabileCatalogoDAO();

        Account  account  = clienteDAO.doRetrieveById(username);
        if(account == null){
            account = managerDAO.doRetrieveById(username);
            if(account == null){
                 account = responsabileCatalogoDAO.doRetrieveById(username);
            }
        }else if(account.getAbilitato() != true){
            return null;
        }
        if(account==null){return null;}

        if(account.getPassword().equals(password)){
            return account;
        }

        return null;

    }

    /**
     * Questo metodo permette di effettuare il logout
     * @param session (HttpSession) la sessione nella quale l'utente è loggato
     * @return true se il logout è avvenuto con successo, false altrimenti
     */
    public boolean logout(HttpSession session){
        try {
            session.invalidate();
            return true;
        }
        catch(Exception e) {
            return false;
        }
    }
}
