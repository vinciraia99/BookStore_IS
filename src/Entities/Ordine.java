package Entities;

import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author Vincenzo Raia
 * @version 0.1
 * @since 27/01/2021
 *
 */

public class Ordine {

    private String stato;
    private int quantita;
    private float totale;
    private Date dataDiAcquisto;
    private int id;
    private ArrayList<LibroOrdinato> libriOrdinati;

    public Ordine(String stato, int quantita, float totale, Date dataDiAcquisto, ArrayList<LibroOrdinato> libriOrdinati) {
        this.stato = stato;
        this.quantita = quantita;
        this.totale = totale;
        this.dataDiAcquisto = dataDiAcquisto;
    }

    public Ordine(int id, String stato, int quantita, float totale, Date dataDiAcquisto,ArrayList<LibroOrdinato> libriOrdinati) {
        this.stato = stato;
        this.quantita = quantita;
        this.totale = totale;
        this.dataDiAcquisto = dataDiAcquisto;
        this.id=id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStato() {
        return stato;
    }

    public void setStato(String stato) {
        this.stato = stato;
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

    public Date getDataDiAcquisto() {
        return dataDiAcquisto;
    }

    public void setDataDiAcquisto(Date dataDiAcquisto) {
        this.dataDiAcquisto = dataDiAcquisto;
    }
}

