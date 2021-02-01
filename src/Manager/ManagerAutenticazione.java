package Manager;

import DAO.ClienteDAO;
import DAO.ManagerDAO;
import DAO.ResponsabileCatalogoDAO;
import Entities.Account;
import Entities.Cliente;
import Entities.Manager;
import Entities.ResponsabileCatalogo;

/**
 * @author Vincenzo Raia
 * @version 0.1
 * @since 30/01/2021
 */

import javax.servlet.http.HttpSession;

public class ManagerAutenticazione {

    public ManagerAutenticazione(){

    }

    /**
     * Metodo che esegue il login di un utente all'interno del sistema.
     * @param username (String) username il dell'utente che vuole loggarsi,
     * @param password (String) password corrispondente all'email dell'utente che vuole loggarsi.
     * @return oggetto Account corrispondente a chi si è loggato, null altrimenti.
     */
    public Account login(String username,String password){
        ClienteDAO clienteDAO = new ClienteDAO();
        ManagerDAO managerDAO = new ManagerDAO();
        ResponsabileCatalogoDAO responsabileCatalogoDAO = new ResponsabileCatalogoDAO();

        Account  account  = clienteDAO.doRetrieveById(username,password);
        if(account == null){
            account = managerDAO.doRetrieveById(username,password);
            if(account == null){
                 account = responsabileCatalogoDAO.doRetrieveById(username,password);
                if(account == null){
                    return null;
                }else{
                    return account;
                }
            }else{
                return account;
            }
        }else if(account.getAbilitato() == true){
            return account;
        }else {
            return null;
        }
    }

    /**
     * Metodo che esegue un logout dell'utente collegato dal sistema.
     * @param session (HttpSession) la sessione nella quale l'utente è loggato
     * @return true se il logout è avvenuto con successo, false altrimenti
     */
    public boolean logout(HttpSession session){
        try {
            session.invalidate();
            return true;
        }
        catch(Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
