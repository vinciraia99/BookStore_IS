package Entities;

public class LibroOrdinato {

    private int id;
    private int quantita;
    private float prezzo;

    public LibroOrdinato(int id, int quantita, float prezzo) {
        this.id = id;
        this.quantita = quantita;
        this.prezzo = prezzo;
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
}
