package Manager;

import DAO.ClienteDAO;
import DAO.ManagerDAO;
import DAO.ResponsabileCatalogoDAO;
import Entities.Account;
import Entities.Cliente;
import Entities.Manager;
import Entities.ResponsabileCatalogo;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Vincenzo Raia
 * @version 0.1
 * @since 30/01/2021
 */

public class ManagerAmministrazione {

    ClienteDAO clienteDAO;
    ManagerDAO managerDAO;
    ResponsabileCatalogoDAO responsabileCatalogoDAO;

    public ManagerAmministrazione (){
        clienteDAO =new ClienteDAO();
        managerDAO = new ManagerDAO();
        responsabileCatalogoDAO = new ResponsabileCatalogoDAO();
    }

    /**
     * Questo metodo permette di prendere dalla base di dati tutti gli utenti del sistema
     * @return visualizzaUtentiRegistrati contentente la lista di tutti gli utenti presenti nel sistema
     */
    public List<Account> visualizzaUtentiRegistrati(){
        List<Cliente> clienti = clienteDAO.doRetrieveAll();
        List<Manager> managers = managerDAO.doRetrieveAll();
        List<ResponsabileCatalogo> responsabileCatalogos = responsabileCatalogoDAO.doRetrieveAll();

        List<Account> accounts  = new ArrayList<>();

        if(clienti != null){
            for(Cliente c : clienti){
                accounts.add(c);
            }
        }else {
            return null;
        }

        if(managers != null){
            for(Manager m : managers){
                accounts.add(m);
            }
        }else {
            return null;
        }

        if(responsabileCatalogos != null){
            for(ResponsabileCatalogo r : responsabileCatalogos){
                accounts.add(r);
            }
        }else {
            return null;
        }

        return accounts;
    }

    /**
     * Questo metodo permette di disabilitare un cliente dal sistema
     * @param username (String) l'username del cliente da disabilitare
     * @return true se si è riusciti a disabilitare il cliente, false altrimenti
     */
    public boolean disabilitaCliente(String username){
        Cliente cliente = clienteDAO.doRetrieveById(username);
        if(cliente !=null){
            return clienteDAO.doChangeAbilitatoCliente(username, false) != -1;
        }else {
            return false;
        }
    }

    /**
     * Questo metodo permette di disabilitare un cliente dal sistema
     * @param username (String) l'username del cliente da disabilitare
     * @return true se si è riusciti ad abilitare il cliente, false altrimenti
     */
    public boolean abilitaCliente(String username){
        Cliente cliente = clienteDAO.doRetrieveById(username);
        if(cliente !=null){
            return clienteDAO.doChangeAbilitatoCliente(username, true) != -1;
        }else {
            return false;
        }
    }
}
