package Entities;

/**
 *
 * @author Vincenzo Raia
 * @version 0.1
 * @since 27/01/2021
 *
 */

public class Categoria {
	private int id;
	private String nome;
	private String descrizione;

	public Categoria(int id, String nome, String descrizione) {
		this.id = id;
		this.nome = nome;
		this.descrizione = descrizione;
	}

	public Categoria(String nome, String descrizione) {
		this.nome = nome;
		this.descrizione = descrizione;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}
}
