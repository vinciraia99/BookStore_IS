package Test.DAO;

import DAO.ClienteDAO;
import Entities.Cliente;
import Entities.Indirizzo;
import Utils.DriverManagerConnectionPool;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author  Raffaele Scarpa
 * @version 0.1
 * @since 29/01/2021
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)

public class ClienteDAOTest {

    private static Connection con;
    private static Indirizzo indirizzo;
    private static Cliente cliente;

    public ClienteDAOTest(){
        indirizzo = new Indirizzo("Via Carlo 5", "Salerno", "SA", 84012);
        cliente = new Cliente("piero@pelu.com", "Piero", "Password", "Piero", "Pelu", indirizzo);
    }


    /**
     * Test DoRetrieveById
     * Versione: 0.1
     * Autore: Raffaele Scarpa
     */

    @BeforeClass
    public static void setUpClass() throws SQLException {
        con = DriverManagerConnectionPool.getConnection();
    }

    @AfterClass
    public static void tearDownClass() throws SQLException {
        PreparedStatement prst = con.prepareStatement("delete from account where username = 'Piero'");
        prst.execute();
        con.commit();
        prst.close();
        DriverManagerConnectionPool.releaseConnection(con);
        System.out.println("Database cancellato");
    }

    /**
     * Test DoRetrieveById
     * Versione: 0.1
     * Autore: Raffaele Scarpa
     */
    @Test
    public void testDoRetrieveById() {
        System.out.println("doRetrieveById");
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
    public void testDoRetrieveAll() {
        System.out.println("doRetrieveAll");
        ClienteDAO clienti = new ClienteDAO();
        int expResult = 1;
        List<Cliente> result = clienti.doRetrieveAll();
        assertEquals(expResult, result.size());
    }

    /**
     * Test DoInsertCliente
     * Versione: 0.1
     * Autore: Raffaele Scarpa
     */
    @Test
    public void testDoInsertCliente() {
        System.out.println("doInsertCliente");
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
    public void testDoUpdateCliente() {
        System.out.println("doUpdateCliente");
        ClienteDAO instance = new ClienteDAO();
        int expResult = 0;
        cliente.setNome("modificato");
        int result = instance.doUpdate(cliente);
        assertEquals(expResult, result);
    }

    /**
     * Test DoDeleteCliente
     * Versione: 0.1
     * Autore: Raffaele Scarpa
     */
    @Test
    public void testDoDeleteCliente() {
        System.out.println("doInsertCliente");
        ClienteDAO clienti = new ClienteDAO();
        int result = clienti.doDelete(cliente);
        int expResult = 0;
        assertEquals(expResult, result);
    }
}