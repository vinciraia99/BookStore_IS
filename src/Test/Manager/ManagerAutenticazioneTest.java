package Test.Manager;

import DAO.ClienteDAO;
import Entities.Account;
import Entities.Cliente;
import Entities.Indirizzo;
import Manager.ManagerAutenticazione;
import Manager.ManagerRegistrazione;
import Utils.DriverManagerConnectionPool;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import javax.servlet.http.HttpSession;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author Vincenzo Raia
 * @version 0.1
 * @since 01/03/2021
 */

public  class ManagerAutenticazioneTest {
    public static Cliente cliente;
    public static Indirizzo indirizzocliente;
    public static ClienteDAO clienteDAO;
    public static Connection con;
    public static ManagerAutenticazione  managerAutenticazione;

    @BeforeClass
    public static void setUpClass() throws SQLException {
        managerAutenticazione = new ManagerAutenticazione();
        clienteDAO =  new ClienteDAO();
        indirizzocliente = new Indirizzo("libertà", "Roccabascerana", "AV", 83016, "Da consegnare al piano");
        cliente = new Cliente("mail@mail.com","utente4","1234pas","carlo","cracco",indirizzocliente);
        clienteDAO.doInsert(cliente);
        ManagerRegistrazione managerRegistrazione = new ManagerRegistrazione();
        managerRegistrazione.confermaRegistrazione(cliente.getUsername(),cliente.getEmail());
    }

    @AfterClass
    public static void tearDownClass() throws SQLException {
        con = DriverManagerConnectionPool.getConnection();
        PreparedStatement prst = con.prepareStatement("delete FROM indirizzo where username = '" + cliente.getUsername() + "'");
        PreparedStatement prst2 = con.prepareStatement("delete FROM account where username = '" + cliente.getUsername() + "'");
        prst.execute();
        prst2.execute();
        con.commit();
        prst.close();
        prst2.close();
        DriverManagerConnectionPool.releaseConnection(con);
        System.out.println("Database cancellato");
    }

    /**
     * Test of login method, of class ManagerAutenticazione.
     */
    @Test
    public void login() {
        System.out.println("login");
        Account result =  managerAutenticazione.login(cliente.getUsername(),cliente.getPassword());
        Account expresult = cliente;
        assertEquals(expresult.getUsername(),result.getUsername());
    }

    /**
     * Test of logout method, of class ManagerAutenticazione.
     */
    @Test
    public void logout() {
        System.out.println("logout");
        HttpSession session = null;
        boolean expResult = false;
        boolean result = managerAutenticazione.logout(session);
        assertEquals(expResult, result);
    }
}