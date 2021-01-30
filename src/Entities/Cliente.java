package Entities;

/**
 * @author Vincenzo Raia
 * @version 0.1
 * @since 27/01/2021
 */

public class Cliente extends Account {

    private Indirizzo indirizzo;

    public Cliente(String email, String username, String password, String nome, String cognome, Indirizzo indirizzo) {
        super(email, username, password, nome, cognome, TIPO_CLIENTE);
        this.indirizzo = indirizzo;
    }

    public Cliente(String email, String username, String password, String nome, String cognome) {
        super(email, username, password, nome, cognome, TIPO_CLIENTE);
    }

    public Indirizzo getIndirizzo() {
        return indirizzo;
    }

    public void setIndirizzo(Indirizzo indirizzo) {
        this.indirizzo = indirizzo;
    }
}
