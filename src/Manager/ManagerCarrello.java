package Manager;

import Entities.Carrello;
import Entities.Libro;

/**
 * @author Vincenzo Raia
 * @version 0.1
 * @since 01/03/2021
 */

public class ManagerCarrello {

    public ManagerCarrello(){}

    /**
     * Questo metodo permette di aggiungere un libro nel carrello
     * @param carrello (Cliente) contiene i dati del cliente,
     * @param libroDaAggiungere (Carrello) contiene le informazioni del carrello.
     * @return boolean true se l'ordine è stato effettuato, false altrimenti.
     */
    public boolean aggiungiLibroCarrello(Carrello carrello, Libro libroDaAggiungere){
        carrello.aggiungiLibro(libroDaAggiungere);
        return true;
    }

    /**
     * Questo metodo permette di rimuovere un libro nel carrello
     * @param libroDaRimuovere (Libro) contiene i dati del libro,
     * @param carrello (Carrello) contiene le informazioni del carrello.
     * @return boolean true se la rimozione è andata a buon fine, false altrimenti.
     */
    public boolean rimuoviLibroCarrello(Carrello carrello, Libro libroDaRimuovere){
        carrello.rimuoviLibro(libroDaRimuovere);
        return true;
    }

    /**
     * Questo metodo permette di modificare la password corrente dell’utente
     * @param libro (Libro) contiene i dati del libro,
     * @param carrello (Carrello) contiene le informazioni del carrello.
     * @param quantita (double) contiene le informazioni sulla quantità da modificare.
     * @return boolean true se la modifica è andata a buon fine, false altrimenti.
     */
    public boolean modificaQuantitaCarrello(Carrello carrello, Libro libro, int quantita){
        return carrello.modificaQuantitaLibro(libro, quantita);
    }

}
