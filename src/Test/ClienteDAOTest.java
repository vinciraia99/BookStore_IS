package Test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;

import DAO.ClienteDAO;
import Entities.Cliente;
import Entities.Indirizzo;

class ClienteDAOTest {

	/**
	 * Test DoRetrieveById 
	 * Versione: 0.1 
	 * Autore: Raffaele Scarpa
	 */
	@Test
	final void testDoRetrieveById() {
		String id = "Piero";
		ClienteDAO clienti = new ClienteDAO();
		Cliente cliente = clienti.doRetrieveById(id);
	}

	/**
	 * Test DoRetrieveAll 
	 * Versione: 0.1 
	 * Autore: Raffaele Scarpa
	 */
	@Test
	final void testDoRetrieveAll() {
		ClienteDAO cliente = new ClienteDAO();
		List<Cliente> clienti = cliente.doRetrieveAll();
	}

	/**
	 * Test DoInsertCliente 
	 * Versione: 0.1 
	 * Autore: Raffaele Scarpa
	 */
	@Test
	final void testDoInsertCliente() {
		Cliente cliente = new Cliente("piero@pelu.com","Piero","Password","Piero","Pelu");
		ClienteDAO clienti = new ClienteDAO();
		clienti.doInsert(cliente);
	}

	/**
	 * Test DoUpdateCliente
	 * Versione: 0.1 
	 * Autore: Raffaele Scarpa
	 */
	@Test
	final void testDoUpdateCliente() {
		Cliente cliente = new Cliente("piero@pelu.com","Piero","Password","Giovanni","Pelu");
		ClienteDAO clienti = new ClienteDAO();
		clienti.doUpdate(cliente);
	}

	/**
	 * Test DoDeleteCliente
	 * Versione: 0.1 
	 * Autore: Raffaele Scarpa
	 */
	@Test
	final void testDoDeleteCliente() {
		Cliente cliente = new Cliente("piero@pelu.com","Piero","Password","Piero","Pelu");
		ClienteDAO clienti = new ClienteDAO();
		clienti.doDelete(cliente);
	}

}
