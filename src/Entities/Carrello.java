package Entities;

import java.util.ArrayList;

/**
 *
 * @author Vincenzo Raia
 * @version 0.1
 * @since 27/01/2021
 *
 */

public class Carrello {

    private ArrayList<Libro> libri;

    public Carrello(ArrayList<Libro> libri) {
        this.libri = libri;
    }

    public void aggiungiLibro(Libro libro){
        libri.add(libro);
    }

    public void rimuoviLibro(Libro libro){
        libri.remove(libro);
    }

    public ArrayList<Libro> fornisciTuttiILibri(){
        return new ArrayList<Libro>(libri);
    }


}
