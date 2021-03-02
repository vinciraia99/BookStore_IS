package Manager;

import DAO.ClienteDAO;
import Entities.Cliente;
import Entities.Indirizzo;

/**
 * @author Vincenzo Raia
 * @version 0.1
 * @since 30/01/2021
 */

public class ManagerRegistrazione {

    private ClienteDAO clienteDAO;

    public ManagerRegistrazione(){
        clienteDAO = new ClienteDAO();
    }

    /**
     * Metodo che esegue la verifica di una mail all'interno del sistema.
     * @param username (String) username dell'utente che vuole registrarsi,
     * @param email (String) email dell'utente che vuole registrarsi,
     * @param password (String) email dell'utente che vuole registrarsi,
     * @param nome (String) email dell'utente che vuole registrarsi,
     * @param cognome (String) email dell'utente che vuole registrarsi,
     * @param indirizzoCliente (Indrizzo) indirizzo dell'utente che vuole registrarsi,
     * @return boolean true se la registrazione è andata a buon fine, false altrimenti.
     */

    public boolean registraCliente(String email, String username , String password, String nome, String cognome, Indirizzo indirizzoCliente) {

       Cliente cliente =  new Cliente(email,username,password,nome,cognome,indirizzoCliente);

        if(clienteDAO.doRetrieveById(username,password) != null){
            return false;
        }

        return clienteDAO.doInsert(cliente) != -1;

    }

    /**
     * Metodo che esegue la verifica di una mail all'interno del sistema.
     * @param username (String) username dell'utente che vuole verificare i propri dati,
     * @param email (String) email dell'utente cche vuole verificare i propri dati.
     * @return boolean true se la conferma è andata a buon fine, false altrimenti.
     */
    public boolean confermaRegistrazione(String username,String email){

        return clienteDAO.doUpdateAccessCeckMail(username, email) != -1;
    }
}


