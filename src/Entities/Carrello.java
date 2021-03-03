package Entities;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Vincenzo Raia
 * @version 0.1
 * @since 27/01/2021
 */

public class Carrello {

    private List<LibroCarrello> libri;
    private int totaleProdotti;

    public Carrello() {
        libri = new ArrayList<>();
        totaleProdotti=0;
    }

    public void aggiungiLibro(Libro l){
        LibroCarrello libro = new LibroCarrello(l);
        for(LibroCarrello libroCarrello : libri){
            if(libroCarrello.equals(libro)){
                int q = libroCarrello.getQuantita();
                libroCarrello.setQuantita(++q);
                totaleProdotti++;
                return;
            }
        }
        totaleProdotti++;
        libri.add(libro);
        return;
    }

    public void rimuoviLibro(Libro l){
        LibroCarrello libro = new LibroCarrello(l);
        for(LibroCarrello libroCarrello : libri){
            if(libroCarrello.equals(libro)){
                int exquantita = libroCarrello.getQuantita();
                libri.remove(libro);
                totaleProdotti= totaleProdotti - exquantita;
                return;
            }
        }

    }

    public boolean modificaQuantitaLibro(Libro l,int quantita){
        LibroCarrello libro = new LibroCarrello(l);
        for(LibroCarrello libroCarrello : libri){
            if(libroCarrello.equals(libro)){
                int exquantita = libroCarrello.getQuantita();
               libroCarrello.setQuantita(quantita);
                totaleProdotti= (totaleProdotti - exquantita) + quantita;
               return true;
            }
        }
        return false;
    }

    public int getTotaleProdotti() {
        return totaleProdotti;
    }

    public void setTotaleProdotti(int totaleProdotti) {
        this.totaleProdotti = totaleProdotti;
    }

    public List<LibroCarrello> getLibri() {
        return libri;
    }

    public void setLibri(List<LibroCarrello> libri) {
        this.libri = libri;
    }

    public class LibroCarrello{
        private Libro libro;
        private int quantita;

        public LibroCarrello(Libro libro, int quantita) {
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

        public int getQuantita() {
            return quantita;
        }

        public void setQuantita(int quantita) {
            this.quantita = quantita;
        }

        @Override
        public boolean equals(Object o) {
            LibroCarrello that = (LibroCarrello) o;
            return libro.getIsbn().equals(that.getLibro().getIsbn());
        }
    }


}
