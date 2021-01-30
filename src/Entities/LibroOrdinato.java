package Entities;

public class LibroOrdinato {

    private int id;
    private double quantita;
    private float prezzo;
    private String isbn;
    private int idordine;

    public LibroOrdinato(int id, double quantita, float prezzo, String isbn) {
        this.id = id;
        this.quantita = quantita;
        this.prezzo = prezzo;
        this.isbn = isbn;
    }

    public LibroOrdinato(double quantita, float prezzo, String isbn) {
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

    public double getQuantita() {
        return quantita;
    }

    public void setQuantita(double quantita) {
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
