package Entities;

/**
 *
 * @author Vincenzo Raia
 * @version 0.1
 * @since 27/01/2021
 *
 */

public class Indirizzo {

    private int id;
    private String via;
    private String comune;
    private String provincia;
    private int cap;
    private String notecorriere;


    public Indirizzo(int id,String via, String comune, String provincia, int cap,String notecorriere) {
        this.via = via;
        this.comune = comune;
        this.provincia = provincia;
        this.cap = cap;
        this.notecorriere = notecorriere;
        this.id=id;
    }


    public Indirizzo(String via, String comune, String provincia, int cap,String notecorriere) {
        this.via = via;
        this.comune = comune;
        this.provincia = provincia;
        this.cap = cap;
        this.notecorriere = notecorriere;
    }

    public String getVia() {
        return via;
    }

    public void setVia(String via) {
        this.via = via;
    }

    public String getComune() {
        return comune;
    }

    public void setComune(String comune) {
        this.comune = comune;
    }

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    public int getCap() {
        return cap;
    }

    public void setCap(int cap) {
        this.cap = cap;
    }

    public String getNotecorriere() {
        return notecorriere;
    }

    public void setNotecorriere(String notecorriere) {
        this.notecorriere = notecorriere;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
