package Entities;

/**
 * @author Vincenzo Raia
 * @version 0.1
 * @since 27/01/2021
 */

import java.util.GregorianCalendar;
import java.util.List;
import java.util.Objects;


public class Libro {
    private String isbn;
    private String titolo;
    private int quantita;
    private String trama;
    private float prezzo;
    private String copertina; //path della copertina del libro
    private GregorianCalendar data_pubblicazione;
    private boolean disabilitato;
    private List<Autore> autori;
    private List<Categoria> categorie;

    public Libro(String isbn, String titolo, int quantita, String trama, Float prezzo, String copertina, GregorianCalendar data_pubblicazione, boolean disabilitato, List<Autore> autori) {
        this.isbn = isbn;
        this.titolo = titolo;
        this.quantita = quantita;
        this.trama = trama;
        this.prezzo = prezzo;
        this.copertina = copertina;
        this.data_pubblicazione = data_pubblicazione;
        this.disabilitato = disabilitato;
        this.autori = autori;
    }

    public Libro(String isbn, String titolo, int quantita, String trama, Float prezzo, String copertina, GregorianCalendar data_pubblicazione, boolean disabilitato) {
        this.isbn = isbn;
        this.titolo = titolo;
        this.quantita = quantita;
        this.trama = trama;
        this.prezzo = prezzo;
        this.copertina = copertina;
        this.data_pubblicazione = data_pubblicazione;
        this.disabilitato = disabilitato;

    }

    public Libro(String isbn, String titolo, int quantita, String trama, Float prezzo, String copertina, GregorianCalendar data_pubblicazione) {
        this.isbn = isbn;
        this.titolo = titolo;
        this.quantita = quantita;
        this.trama = trama;
        this.prezzo = prezzo;
        this.copertina = copertina;
        this.data_pubblicazione = data_pubblicazione;
        this.disabilitato = false;

    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getTitolo() {
        return titolo;
    }

    public void setTitolo(String titolo) {
        this.titolo = titolo;
    }

    public int getQuantita() {
        return quantita;
    }

    public void setQuantita(int quantita) {
        this.quantita = quantita;
    }

    public String getTrama() {
        return trama;
    }

    public void setTrama(String trama) {
        this.trama = trama;
    }

    public Float getPrezzo() {
        return prezzo;
    }

    public void setPrezzo(Float prezzo) {
        this.prezzo = prezzo;
    }

    public String getCopertina() {
        return copertina;
    }

    public void setCopertina(String copertina) {
        this.copertina = copertina;
    }

    public GregorianCalendar getData_pubblicazione() {
        return data_pubblicazione;
    }

    public void setData_pubblicazione(GregorianCalendar data_pubblicazione) {
        this.data_pubblicazione = data_pubblicazione;
    }

    public boolean isDisabilitato() {
        return disabilitato;
    }

    public void setDisabilitato(boolean disabilitato) {
        this.disabilitato = disabilitato;
    }

    public List<Autore> getAutori() {
        return autori;
    }

    public void setAutori(List<Autore> autori) {
        this.autori = autori;
    }

    public List<Categoria> getCategorie() {
        return categorie;
    }

    public void setCategorie(List<Categoria> categorie) {
        this.categorie = categorie;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Libro)) return false;
        Libro libro = (Libro) o;
        return Double.compare(libro.getQuantita(), getQuantita()) == 0 &&
                Float.compare(libro.getPrezzo(), getPrezzo()) == 0 &&
                isDisabilitato() == libro.isDisabilitato() &&
                Objects.equals(getIsbn(), libro.getIsbn()) &&
                Objects.equals(getTitolo(), libro.getTitolo()) &&
                Objects.equals(getTrama(), libro.getTrama()) &&
                Objects.equals(getCopertina(), libro.getCopertina()) &&
                Objects.equals(getData_pubblicazione(), libro.getData_pubblicazione()) &&
                Objects.equals(getAutori(), libro.getAutori()) &&
                Objects.equals(getCategorie(), libro.getCategorie());
    }

}
