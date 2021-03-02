package Test.DAO;

import DAO.ClienteDAO;
import Entities.Cliente;
import Entities.Indirizzo;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;


/**
 * @author Raffaele Scarpa
 * @version 0.1
 * 29/01/2021
 */

class ClienteDAOTest {

	private static Indirizzo indirizzo = new Indirizzo("Via Carlo 5", "Salerno", "SA", 84012);
	private static Cliente cliente = new Cliente("piero@pelu.com", "Piero", "Password", "Piero", "Pelu", indirizzo);
	
    /**
     * Test DoRetrieveById
     * Versione: 0.1
     * Autore: Raffaele Scarpa
     */
    @Test
    final void testDoRetrieveById() {
        ClienteDAO clienti = new ClienteDAO();
        Cliente result = clienti.doRetrieveById(cliente.getUsername(),cliente.getPassword());
        String expResult = cliente.getUsername();
        assertEquals(expResult,result.getUsername());
    }

    /**
     * Test DoRetrieveAll
     * Versione: 0.1
     * Autore: Raffaele Scarpa
     */
    @Test
    final void testDoRetrieveAll() {
        ClienteDAO clienti = new ClienteDAO();
        List<Cliente> expResult = new ArrayList<>();
        expResult.add(cliente);
        List<Cliente> result = clienti.doRetrieveAll();
        assertEquals(expResult.size(), result.size());
    }

    /**
     * Test DoInsertCliente
     * Versione: 0.1
     * Autore: Raffaele Scarpa
     */
    @Test
    final void testDoInsertCliente() {
    	cliente.setIndirizzo(indirizzo);
        ClienteDAO clienti = new ClienteDAO();
        int result = clienti.doInsert(cliente);
        int expResult = 0;
        assertEquals(expResult, result);
    }

    /**
     * Test DoUpdateCliente
     * Versione: 0.1
     * Autore: Raffaele Scarpa
     */
    @Test
    final void testDoUpdateCliente() {
        Cliente clienteNew = new Cliente("piero@pelu.com", "Piero", "Password", "Giovanni", "Pelu");
        ClienteDAO clienti = new ClienteDAO();
        int result = clienti.doUpdate(clienteNew);
        int expResult = 0;
        assertEquals(expResult, result);
        
        
        Cliente updatedCategoria = clienti.doRetrieveById(clienteNew.getUsername());
        assertEquals(updatedCategoria.getUsername(),clienteNew.getUsername());
    }

    /**
     * Test DoDeleteCliente
     * Versione: 0.1
     * Autore: Raffaele Scarpa
     */
    @Test
    final void testDoDeleteCliente() {
        ClienteDAO clienti = new ClienteDAO();
        int result = clienti.doDelete(cliente);
        int expResult = 0;
        assertEquals(expResult, result);
    }

}
