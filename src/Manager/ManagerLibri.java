package Manager;

import DAO.LibroDAO;
import Entities.Autore;
import Entities.Libro;

import java.util.GregorianCalendar;
import java.util.List;

public class ManagerLibri {

    private  LibroDAO libroDAO;

    public ManagerLibri(){
        libroDAO =   new LibroDAO();
    }

    public List<Libro> cercaLibro(String parolaChiave){

        List<Libro> libri = libroDAO.doRetrieveByNomeOrDescrizione(parolaChiave);
        if(libri!= null){
            return libri;
        }else{
            return null;
        }
    }

    public boolean aggiuntaLibro(String isbn, String titolo, String trama, GregorianCalendar dataPubblicazione, Float prezzo, double quantita, String coperina,boolean disabilitato, List<Autore> autori){
        Libro libro = new Libro(isbn,titolo,quantita,trama,prezzo,coperina,dataPubblicazione);
        libro.setAutori(autori);

        if(libroDAO.doInsert(libro) == -1){
            return false;
        }else{
            return true;
        }
    }

    public boolean eliminaLibro(String isbn){

        Libro libro = libroDAO.doRetrieveById(isbn);
        if(libro != null) {
            if (libroDAO.doDelete(libro) == -1) {
                return false;
            } else {
                return true;
            }
        }else{
            return false;
        }
    }

    public boolean modificaLibro(String isbn, String titolo, String trama, GregorianCalendar  dataPubblicazione, Float prezzo, double quantita, String copertina,boolean disabilitato, List<Autore> autori){
        Libro libro = libroDAO.doRetrieveById(isbn);
        if(libro != null){
            libro.setTitolo(titolo);
            libro.setTrama(trama);
            libro.setData_pubblicazione(dataPubblicazione);
            libro.setPrezzo(prezzo);
            libro.setQuantita(quantita);
            libro.setCopertina(copertina);
            libro.setDisabilitato(disabilitato);
            libro.setAutori(autori);
           if(libroDAO.doUpdate(libro) == -1){
               return false;
           }else{
               return true;
           }
        }else {
            return true;
        }
    }

    public boolean modificaNumeroCopieLibro(String isbn,double quantita){
        Libro libro = libroDAO.doRetrieveById(isbn);
        if(libro != null){
            libro.setQuantita(quantita);
            if(libroDAO.doUpdate(libro) == -1){
                return false;
            }else{
                return true;
            }
        }else{
            return false;
        }
    }

    public Libro acquisisciLibro(String isbn){
        Libro libro = libroDAO.doRetrieveById(isbn);
        if(libro != null){
            return libro;
        } else{
            return null;
        }
    }

    public List<Libro> acquisisciTuttiILibri(){
        List<Libro> libri  = libroDAO.doRetrieveAll();
        if(libri != null && libri.size() > 0){
            return libri;
        }else{
            return null;
        }
    }

}
