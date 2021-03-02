package Entities;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author Vincenzo Raia
 * @version 0.1
 * @since 27/01/2021
 */

public class Carrello {

    private List<LibroCarrello> libri;

    public Carrello() {
        libri = new ArrayList<>();
    }

    public void aggiungiLibro(Libro l){
        LibroCarrello libro = new LibroCarrello(l);
        for(LibroCarrello libroCarrello : libri){
            if(libroCarrello.equals(libro)){
                Double q = libroCarrello.getQuantita();
                libroCarrello.setQuantita(++q);
                return;
            }
        }
        libri.add(libro);
        return;
    }

    public void rimuoviLibro(Libro l){
        LibroCarrello libro = new LibroCarrello(l);
        for(LibroCarrello libroCarrello : libri){
            if(libroCarrello.equals(libro)){
                libri.remove(libro);
                return;
            }
        }

    }

    public boolean modificaQuantitaLibro(Libro l,double quantita){
        LibroCarrello libro = new LibroCarrello(l);
        for(LibroCarrello libroCarrello : libri){
            if(libroCarrello.equals(libro)){
               libroCarrello.setQuantita(quantita);
               return true;
            }
        }
        return false;
    }

    public List<LibroCarrello> getLibri() {
        return libri;
    }

    public void setLibri(List<LibroCarrello> libri) {
        this.libri = libri;
    }

    public class LibroCarrello{
        private Libro libro;
        private double quantita;

        public LibroCarrello(Libro libro, double quantita) {
            this.libro = libro;
            this.quantita = quantita;
        }

        public LibroCarrello(Libro libro) {
            this.libro = libro;
            this.quantita = 1;
        }

        public Libro getLibro() {
            return libro;

        }

        public void setLibro(Libro libro) {
            this.libro = libro;
        }

        public double getQuantita() {
            return quantita;
        }

        public void setQuantita(double quantita) {
            this.quantita = quantita;
        }

        @Override
        public boolean equals(Object o) {
            LibroCarrello that = (LibroCarrello) o;
            return libro.getIsbn().equals(that.getLibro().getIsbn());
        }
    }


}
