package Entities;

/**
 *
 * @author Vincenzo Raia
 * @version 0.1
 * @since 27/01/2021
 *
 */


public abstract class Account {

    protected String email;
    protected String username;
    protected String password;
    protected String tipo;
    protected String nome;
    protected String cognome;

    public static final String TIPO_CLIENTE = "C";
    public static final String TIPO_RESPONSABILE_CATALOGO = "R";
    public static final String TIPO_MANAGER = "M";

    public Account() {
    }

    public Account(String email, String username, String password, String nome, String cognome,String tipo) {
        this.email = email;
        this.username = username;
        this.password = password;
        this.tipo = tipo;
        this.nome = nome;
        this.cognome = cognome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCognome() {
        return cognome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public String getTipo() {
        return tipo;
    }
}
