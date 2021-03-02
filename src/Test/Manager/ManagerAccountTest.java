package Test.Manager;

import DAO.ClienteDAO;
import DAO.ManagerDAO;
import Entities.Cliente;
import Entities.Indirizzo;
import Entities.Manager;
import Manager.*;
import Utils.DriverManagerConnectionPool;
import com.mysql.cj.xdevapi.Client;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Vincenzo Raia
 * @version 0.1
 * @since 01/03/2021
 */

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ManagerAccountTest {

    public static Indirizzo indirizzocliente;
    public static Cliente cliente;
    public static Manager manager;
    public static ClienteDAO clienteDAO;
    public static ManagerDAO managerDAO;
    private static Connection con;

    @BeforeClass
    public static void setUpClass() throws SQLException {
        con = DriverManagerConnectionPool.getConnection();
        indirizzocliente = new Indirizzo("libertà", "Roccabascerana", "AV", 83016, "Da consegnare al piano");
        clienteDAO =  new ClienteDAO();
        cliente = new Cliente("mail@mail.com","utente2","1234pas","carlo","cracco",indirizzocliente);
        clienteDAO.doInsert(cliente);
    }

    @AfterClass
    public static void tearDownClass() throws SQLException {
        PreparedStatement prst2 = con.prepareStatement("delete from Account where username = '" + cliente.getUsername() + "'");
        prst2.execute();
        con.commit();
        prst2.close();
        DriverManagerConnectionPool.releaseConnection(con);
        System.out.println("Database cancellato");
    }

    /**
     * Test of modificaPassword method, of class ManagerAccount.
     */
    @Test
    public void modificaPassword() {
        System.out.println("modificaPassword");
        String newPsw = "pass233454356";
        ManagerAccount instance = new ManagerAccount();
        boolean expResult = true;
        boolean result = instance.modificaPassword(cliente.getUsername(),newPsw,cliente.getPassword());
        assertEquals(expResult, result);
    }

    /**
     * Test of modificaDatiPersonali method, of class ManagerAccount.
     */
    @Test
    public void modificaDatiPersonali() {
        System.out.println("modificaDatiPersonali");
        Cliente cliente = new Cliente("nuoovamail@mail.com","utente2","1234pas","Francesco","cracco");
        cliente.setIndirizzo(indirizzocliente);
        int result =  clienteDAO.doUpdate(cliente);
        int expResult = 0;
        assertEquals(expResult, result);
    }

    /**
     * Test of modificaIndirizzo method, of class ManagerAccount.
     */
    @Test
    public void modificaIndirizzo() {
        System.out.println("modificaIndirizzo");
        Indirizzo expResult = cliente.getIndirizzo();
        expResult.setVia("romagna");
        clienteDAO.doUpdate(cliente);
        Cliente ne = clienteDAO.doRetrieveById(cliente.getUsername(),cliente.getPassword());
        Indirizzo result = ne.getIndirizzo();
        assertEquals(expResult.getVia(),result.getVia());
    }
}