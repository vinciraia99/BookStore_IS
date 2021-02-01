package Entities;

/**
 * @author Vincenzo Raia
 * @version 0.1
 * @since 27/01/2021
 */

import java.util.GregorianCalendar;
import java.util.List;


public class Libro {
    private String isbn;
    private String titolo;
    private double quantita;
    private String trama;
    private float prezzo;
    private String copertina; //path della copertina del libro
    private GregorianCalendar data_pubblicazione;
    private boolean disabilitato;
    private List<Autore> autori;

    public Libro(String isbn, String titolo, Double quantita, String trama, Float prezzo, String copertina, GregorianCalendar data_pubblicazione, boolean disabilitato, List<Autore> autori) {
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

    public Libro(String isbn, String titolo, Double quantita, String trama, Float prezzo, String copertina, GregorianCalendar data_pubblicazione, boolean disabilitato) {
        this.isbn = isbn;
        this.titolo = titolo;
        this.quantita = quantita;
        this.trama = trama;
        this.prezzo = prezzo;
        this.copertina = copertina;
        this.data_pubblicazione = data_pubblicazione;
        this.disabilitato = disabilitato;

    }

    public Libro(String isbn, String titolo, Double quantita, String trama, Float prezzo, String copertina, GregorianCalendar data_pubblicazione) {
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

    public Double getQuantita() {
        return quantita;
    }

    public void setQuantita(Double quantita) {
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
}
