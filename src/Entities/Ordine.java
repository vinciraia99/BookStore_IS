package Entities;

import java.util.ArrayList;
import java.util.GregorianCalendar;

/**
 *
 * @author Vincenzo Raia
 * @version 0.1
 * @since 27/01/2021
 *
 */

public class Ordine {

    private boolean spedito;
    private double quantita;
    private float totale;
    private GregorianCalendar dataDiAcquisto;
    private int id;
    private ArrayList<LibroOrdinato> libriOrdinati;
    private String username;

    public Ordine(int quantita, float totale, GregorianCalendar dataDiAcquisto, int id, ArrayList<LibroOrdinato> libriOrdinati, String username) {
        this.spedito = false;
        this.quantita = quantita;
        this.totale = totale;
        this.dataDiAcquisto = dataDiAcquisto;
        this.id = id;
        this.libriOrdinati = libriOrdinati;
        this.username = username;
    }

    public Ordine(double quantita, float totale, GregorianCalendar dataDiAcquisto, int id, String username) {
        this.spedito = false;
        this.quantita = quantita;
        this.totale = totale;
        this.dataDiAcquisto = dataDiAcquisto;
        this.id = id;
        this.username = username;
    }

    public boolean getStato() {
        return spedito;
    }

    public double getQuantita() {
        return quantita;
    }

    public void setQuantita(double quantita) {
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

    public ArrayList<LibroOrdinato> getLibriOrdinati() {
        return libriOrdinati;
    }

    public void setLibriOrdinati(ArrayList<LibroOrdinato> libriOrdinati) {
        this.libriOrdinati = libriOrdinati;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}

