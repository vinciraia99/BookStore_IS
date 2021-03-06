package Manager;

/**
 * @author Vincenzo Raia
 * @version 0.1
 * @since 01/03/2021
 */

import DAO.OrdineDAO;
import Entities.Carrello;
import Entities.Cliente;
import Entities.LibroOrdinato;
import Entities.Ordine;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

public class ManagerOrdini {
    OrdineDAO ordineDAO;

    public ManagerOrdini(){
        ordineDAO = new OrdineDAO();
    }


    /**
     * Questo metodo permette di effettuare un ordine e l’operazione di pagamento
     * @param libriOrdinati (Carrello) contiene le informazioni sul carrello.
     * @param cliente (Cliente) contiene le informazioni del cliente.
     * @return id dell'ordine se l'ordine è andato a buon fine, -1 altrimenti.
     */
    public int effettuaOrdine(Cliente cliente, Carrello libriOrdinati){

        Calendar calendar = GregorianCalendar.getInstance();
        int totalequantita = 0;
        float totale = 0;
        for(Carrello.LibroCarrello l : libriOrdinati.getLibri()){
            totalequantita = totalequantita + l.getQuantita();
            totale =  (totale + (l.getLibro().getPrezzo() * (float) l.getQuantita()));
        }
        GregorianCalendar oggi = new GregorianCalendar();
        ArrayList<LibroOrdinato> libri = new ArrayList<LibroOrdinato>();
        for (Carrello.LibroCarrello l:
                libriOrdinati.getLibri()) {
            LibroOrdinato libro = new LibroOrdinato(l.getQuantita(),l.getLibro().getPrezzo(),l.getLibro().getIsbn());
            libri.add(libro);
        }
        Ordine ordine = new Ordine(totalequantita,totale,oggi,cliente.getUsername());
        ordine.setLibriOrdinati(libri);
        int r = ordineDAO.doInsert(ordine);
        if(r!=-1){return r;}
        else {return -1;}
    }


    /**
     * Questo metodo permette di visualizzare ad un cliente tutti gli ordini effettuati
     * @param cliente (Cliente) contiene le informazioni del cliente.
     * @return Una lista degli ordini effettuati, null altrimenti.
     */
    public List<Ordine> visualizzaOrdini(Cliente cliente){
         return  ordineDAO.doRetriveByIdCliente(cliente.getUsername());
    }

    /**
     * Questo metodo permette di visualizzare ad un cliente nel dettaglio un ordine effettuato
     * @return L'ordine effettuato, null altrimenti.
     */
    public Ordine visualizzaDettaglioOrdine(Cliente cliente, int id){
            Ordine ordine = ordineDAO.doRetrieveById(id);
            if(ordine.getUsername().equals(cliente.getUsername())){
                return ordine;
            }else{
                return null;
            }
    }

    /**
     * Questo metodo permette di visualizzare ad un amministratore tutti gli ordini effettuati da tutti i clienti
     * @return Una lista degli ordini effettuati, null altrimenti.
     */
    public List<Ordine> visualizzaOrdiniUtenti(){
        return  ordineDAO.doRetriveAll();
    }

    /**
     * Questo metodo permette di visualizzare ad un cliente tutti gli ordini effettuati
     * @param id (int) contiene l'id del libro.
     * @return L'ordine effettuato dal cliente, null altrimenti.
     */
    public Ordine visualizzaDettaglioOrdineUtente(int id){
        return ordineDAO.doRetrieveById(id);
    }

    /**
     * Questo metodo permette di modificare lo stato di un ordine effettuato da un cliente
     * @param id (int) contiene l'id del libro.
     * @param stato (boolean) contiene lo stato dell'ordine.
     * @return restituisce true se riesce ad effettuare il cambio,false altrimenti
     */
    public boolean modificaStatoOrdine(int id, boolean stato){
        Ordine ordine = ordineDAO.doRetrieveById(id);
        if (ordine!=null){
            return ordineDAO.doUpdateOrderState(ordine) == 0;
        }
        return false;
    }
}

