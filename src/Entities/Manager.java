package Entities;

/**
 * @author Vincenzo Raia
 * @version 0.1
 * @since 27/01/2021
 */


public class Manager extends Account {
    public Manager(String email, String username, String password, String nome, String cognome) {
        super(email, username, password, nome, cognome, TIPO_MANAGER);
    }
}
