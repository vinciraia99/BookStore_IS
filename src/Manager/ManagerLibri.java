package Manager;

import DAO.LibroDAO;
import Entities.Libro;

import java.util.List;

/**
 * @author Vincenzo Raia
 * @version 0.1
 * @since 30/01/2021
 */

public class ManagerLibri {

    private  LibroDAO libroDAO;

    public ManagerLibri(){
        libroDAO =   new LibroDAO();
    }


    /**
     * Questo metodo permette la ricerca di un libro all’interno del sistema
     * @param parolaChiave (String) parola da cercare
     * @return cercaLibro (List<Libro>) lista dei libri trovati (se trovati), null altrimenti
     */
    public List<Libro> cercaLibro(String parolaChiave){

        List<Libro> libri = libroDAO.doRetrieveByNomeOrDescrizione(parolaChiave);
        return libri;
    }

    /**
     * Questo metodo permette l’aggiunta di un libro all’interno del database di libri.
     * @param libro (Libro) contiene le infomrazioni del libro
     * @return true se l'aggiunta ha successo, false altrimenti
     */
    public boolean aggiuntaLibro(Libro libro ){
        return libroDAO.doInsert(libro) != -1;
    }

    /**
     * Questo metodo permette l’eliminazione di un libro dal database di libri
     * @param isbn (String) isbn del libro
     * @return true se l'eliminazione ha successo, false altrimenti
     */
    public boolean eliminaLibro(String isbn){

        Libro libro = libroDAO.doRetrieveById(isbn);
        if(libro != null) {
            return libroDAO.doDelete(libro) != -1;
        }else{
            return false;
        }
    }


    /**
     * Questo metodo permette la modifica di un libro all’interno del database di libri.
     * @param libro (Libro) oggetto contenente tutte le informazioni del libro
     * @return true se la modifica ha successo, false altrimenti
     */
    public boolean modificaLibro(Libro libro){
        Libro librodao = libroDAO.doRetrieveById(libro.getIsbn());
        if(librodao != null){
            librodao.setTitolo(libro.getTitolo());
            librodao.setTrama(libro.getTrama());
            librodao.setData_pubblicazione(libro.getData_pubblicazione());
            librodao.setPrezzo(libro.getPrezzo());
            librodao.setQuantita(libro.getQuantita());
            librodao.setCopertina(libro.getCopertina());
            librodao.setDisabilitato(libro.isDisabilitato());
            librodao.setAutori(libro.getAutori());
            librodao.setCategorie(libro.getCategorie());
            return libroDAO.doUpdate(librodao) != -1;
        }else {
            return false;
        }
    }

    /**
     * Questo metodo permette la modifica del numero copie di un libro all’interno del database di libri
     * @param isbn (String) isbn del libro
     * @param quantita (Integer) quantita di libri disponibili
     * @return true se la modifica ha successo, false altrimenti
     */
    public boolean modificaNumeroCopieLibro(String isbn,int quantita){
        Libro libro = libroDAO.doRetrieveById(isbn);
        if(libro != null){
            libro.setQuantita(quantita);
            return libroDAO.doUpdate(libro) != -1;
        }else{
            return false;
        }
    }

    /**
     * Questo metodo permette di ricevere le informazioni di un libro presente del database
     * @param isbn (String) isbn del libro
     * @return il libro richiesto se il libro è stato trovato, null altrimenti
     */
    public Libro acquisisciLibro(String isbn){
        Libro libro = libroDAO.doRetrieveById(isbn);
        return libro;
    }

    /**
     * Questo metodo permette di ricevere le informazioni di un libro presente del database
     * @return lista dei libri presenti nel sistema se esistono, null altrimenti
     */
    public List<Libro> acquisisciTuttiILibri(){
        List<Libro> libri  = libroDAO.doRetrieveAll();
        if(libri != null && libri.size() > 0){
            return libri;
        }else{
            return null;
        }
    }

}
