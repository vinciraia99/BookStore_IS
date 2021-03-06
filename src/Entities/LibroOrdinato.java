package Entities;

/**
 * @author Vincenzo Raia
 * @version 0.1
 * @since 01/03/2021
 */

public class LibroOrdinato {

    private int id;
    private int quantita;
    private float prezzo;
    private String isbn;
    private int idordine;

    public LibroOrdinato(int id, int quantita, float prezzo, String isbn) {
        this.id = id;
        this.quantita = quantita;
        this.prezzo = prezzo;
        this.isbn = isbn;
    }

    public LibroOrdinato(int quantita, float prezzo, String isbn) {
        this.id = id;
        this.quantita = quantita;
        this.prezzo = prezzo;
        this.isbn = isbn;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getQuantita() {
        return quantita;
    }

    public void setQuantita(int quantita) {
        this.quantita = quantita;
    }

    public float getPrezzo() {
        return prezzo;
    }

    public void setPrezzo(float prezzo) {
        this.prezzo = prezzo;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }
}
