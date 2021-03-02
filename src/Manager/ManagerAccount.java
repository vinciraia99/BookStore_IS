package Manager;

import DAO.ClienteDAO;
import DAO.ManagerDAO;
import DAO.ResponsabileCatalogoDAO;
import Entities.Cliente;
import Entities.Indirizzo;
import Entities.Manager;
import Entities.ResponsabileCatalogo;

/**
 * @author Vincenzo Raia
 * @version 0.1
 * @since 30/01/2021
 */

public class ManagerAccount {

    ClienteDAO clienteDAO;
    ManagerDAO managerDAO;
    ResponsabileCatalogoDAO responsabileCatalogoDAO;

    public ManagerAccount(){
        clienteDAO = new ClienteDAO();
        managerDAO = new ManagerDAO();
        responsabileCatalogoDAO = new ResponsabileCatalogoDAO();
    }


    /**
     * Questo metodo permette di modificare la password corrente dell’utente
     * @param username (String) username dell'utente che vuole modificare la password,
     * @param newPassword (String) newPassword dell'utente che vuole modificare la password.
     * @param oldPassword (String) oldPassword dell'utente che vuole modificare la password.
     * @return boolean true se la modifica è andata a buon fine, false altrimenti.
     */
    public boolean modificaPassword(String username, String newPassword, String oldPassword){

        Cliente cliente=  clienteDAO.doRetrieveById(username);

        if(cliente== null){
            Manager manager = managerDAO.doRetrieveById(username,oldPassword);
            if(manager== null){
                ResponsabileCatalogo responsabileCatalogo = responsabileCatalogoDAO.doRetrieveById(username,oldPassword);
                if(responsabileCatalogo == null){
                    return false;
                }  else{
                    responsabileCatalogo.setPassword(newPassword);
                    return responsabileCatalogoDAO.doUpdate(responsabileCatalogo) != -1;
                }
            }else {
                manager.setPassword(newPassword);
                return managerDAO.doUpdate(manager) != -1;
            }
        }else{
            cliente.setPassword(newPassword);
            return clienteDAO.doUpdate(cliente) != -1;
        }
    }

    /**
     *Questo metodo permette di modificare i dati personali di un utente del sistema
     * @param username (String) username dell'utente che vuole modificare i dati personali.
     * @param email (String) email dell'utente che vuole modificare i dati personali.
     * @param password (String) password dell'utente che vuole modificare i dati personali.
     * @param nome (String) nome dell'utente che vuole modificare i dati personali.
     * @param cognome (String) cognome dell'utente che vuole modificare i dati personali.
     * @return boolean true se la modifica è andata a buon fine, false altrimenti.
     */
    public boolean modificaDatiPersonali(String email, String username,String password, String nome, String cognome){

        Cliente cliente=  clienteDAO.doRetrieveById(username,password);

        if(cliente== null){
            Manager manager = managerDAO.doRetrieveById(username,password);
            if(manager== null){
                ResponsabileCatalogo responsabileCatalogo = responsabileCatalogoDAO.doRetrieveById(username);
                if(responsabileCatalogo == null){
                    return false;
                }  else{
                    responsabileCatalogo.setEmail(email);
                    responsabileCatalogo.setNome(nome);
                    responsabileCatalogo.setCognome(cognome);
                    return responsabileCatalogoDAO.doUpdate(responsabileCatalogo) != -1;
                }
            }else {
                manager.setEmail(email);
                manager.setNome(nome);
                manager.setCognome(cognome);
                return managerDAO.doUpdate(manager) != -1;
            }
        }else{
            cliente.setEmail(email);
            cliente.setNome(nome);
            cliente.setCognome(cognome);
            return clienteDAO.doUpdate(cliente) != -1;
        }
    }

    /**
     * Questo metodo permette di modificare i dati di un indirizzo di un cliente
     * @param via (String) via dell'utente che vuole modificare l'indirizzo.
     * @param comune (String) comune dell'utente che vuole modificare l'indirizzo.
     * @param provincia (String) provincia dell'utente che vuole modificare l'indirizzo.
     * @param noteCorriere (String) noteCorriere dell'utente che vuole modificare l'indirizzo.
     * @param cap (int) cap dell'utente che vuole modificare l'indirizzo.
     * @param username (String) username dell'utente che vuole modificare l'indirizzo.
     * @param password (String) password dell'utente che vuole modificare l'indirizzo.
     * @return boolean true se la modifica è andata a buon fine, false altrimenti.
     */
    public boolean modificaIndirizzo(String via, String comune,String provincia,String noteCorriere,int cap,String username,String password){

        Cliente cliente = clienteDAO.doRetrieveById(username);
        if(cliente == null){
            return false;
        }else{
            Indirizzo indirizzo = cliente.getIndirizzo();
            indirizzo.setVia(via);
            indirizzo.setComune(comune);
            indirizzo.setProvincia(provincia);
            indirizzo.setNotecorriere(noteCorriere);
            indirizzo.setCap(cap);
            cliente.setIndirizzo(indirizzo);
            return clienteDAO.doUpdate(cliente) != -1;

        }
    }
}
