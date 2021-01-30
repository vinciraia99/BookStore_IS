package Entities;

/**
 * @author Vincenzo Raia
 * @version 0.1
 * @since 27/01/2021
 */

public class Autore {

    private int id;
    private String nomecompleto;

    public Autore(int id, String nomecompleto) {
        this.id = id;
        this.nomecompleto = nomecompleto;
    }

    public Autore(String nomecompleto) {
        this.id = 0;
        this.nomecompleto = nomecompleto;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getnomecompleto() {
        return nomecompleto;
    }

    public void setnomecompleto(String nomecompleto) {
        this.nomecompleto = nomecompleto;
    }
}
