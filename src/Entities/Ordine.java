package Entities;

import java.util.GregorianCalendar;
import java.util.List;

/**
 * @author Vincenzo Raia
 * @version 0.1
 * @since 27/01/2021
 */

public class Ordine {

    private boolean spedito;
    private int quantita;
    private float totale;
    private GregorianCalendar dataDiAcquisto;
    private int id;
    private List<LibroOrdinato> libriOrdinati;
    private String username;

    public Ordine(int quantita, float totale, GregorianCalendar dataDiAcquisto, int id, List<LibroOrdinato> libriOrdinati, String username) {
        this.spedito = false;
        this.quantita = quantita;
        this.totale = totale;
        this.dataDiAcquisto = dataDiAcquisto;
        this.id = id;
        this.libriOrdinati = libriOrdinati;
        this.username = username;
    }

    public Ordine(int quantita, float totale, GregorianCalendar dataDiAcquisto, int id, String username) {
        this.spedito = false;
        this.quantita = quantita;
        this.totale = totale;
        this.dataDiAcquisto = dataDiAcquisto;
        this.id = id;
        this.username = username;
    }

    public Ordine(int quantita, float totale, GregorianCalendar dataDiAcquisto, String username) {
        this.spedito = false;
        this.quantita = quantita;
        this.totale = totale;
        this.dataDiAcquisto = dataDiAcquisto;
        this.username = username;
        this.id = -1;
    }

    public boolean getStato() {
        return spedito;
    }

    public int getQuantita() {
        return quantita;
    }

    public void setQuantita(int quantita) {
        this.quantita = quantita;
    }

    public float getTotale() {
        return totale;
    }

    public void setTotale(float totale) {
        this.totale = totale;
    }

    public GregorianCalendar getDataDiAcquisto() {
        return dataDiAcquisto;
    }

    public void setDataDiAcquisto(GregorianCalendar dataDiAcquisto) {
        this.dataDiAcquisto = dataDiAcquisto;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<LibroOrdinato> getLibriOrdinati() {
        return libriOrdinati;
    }

    public void setLibriOrdinati(List<LibroOrdinato> libriOrdinati) {
        this.libriOrdinati = libriOrdinati;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getQuantitaByIsbn(String isbn){
        for(LibroOrdinato l : libriOrdinati){
            if(l.getIsbn().equals(isbn)){
                return l.getQuantita();
            }
        }
        return -1;
    }

    public String getDataString(){
        String string = getDataDiAcquisto().get(GregorianCalendar.DATE) + "-" + ((int)getDataDiAcquisto().get(GregorianCalendar.MONTH)+1) + "-" + getDataDiAcquisto().get(GregorianCalendar.YEAR);
        return string;
    }
}

