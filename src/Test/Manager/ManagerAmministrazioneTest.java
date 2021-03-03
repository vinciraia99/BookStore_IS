package Test.Manager;

import DAO.ClienteDAO;
import DAO.ManagerDAO;
import Entities.Account;
import Entities.Cliente;
import Entities.Indirizzo;
import Entities.Manager;
import Manager.ManagerAmministrazione;
import Utils.DriverManagerConnectionPool;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author Vincenzo Raia
 * @version 0.1
 * @since 01/03/2021
 */

public class ManagerAmministrazioneTest {

    public static Indirizzo indirizzocliente;
    public static Cliente cliente;
    public static Manager manager;
    public static ClienteDAO clienteDAO;
    public static ManagerDAO managerDAO;
    private static Connection con;
    private static ManagerAmministrazione managerAmministrazione;


    @BeforeClass
    public static void setUpClass() throws SQLException {
        managerAmministrazione = new ManagerAmministrazione();
        indirizzocliente = new Indirizzo("libertà", "Roccabascerana", "AV", 83016, "Da consegnare al piano");
        clienteDAO =  new ClienteDAO();
        managerDAO = new ManagerDAO();
        cliente = new Cliente("mail@mail.com","utente2","1234pas","carlo","cracco",indirizzocliente);
        clienteDAO.doInsert(cliente);
        manager = new Manager("mail2@mail.com","manager","1234pas","dr","drake");
        managerDAO.doInsert(manager);
    }

    @AfterClass
    public static void tearDownClass() throws SQLException {
        con = DriverManagerConnectionPool.getConnection();
        PreparedStatement prst2 = con.prepareStatement("delete FROM bookstoredb.account");
        PreparedStatement prst = con.prepareStatement("delete FROM bookstoredb.indirizzo");
        prst.execute();
        prst2.execute();
        con.commit();
        prst.close();
        prst2.close();
        DriverManagerConnectionPool.releaseConnection(con);
        System.out.println("Database cancellato");
    }

    /**
     * Test of visualizzaUtentiRegistrati method, of class ManagerAmministrazione.
     */
    @Test
    public void visualizzaUtentiRegistrati() {
        System.out.println("visualizzaUtentiRegistrati");
        int expresult =2;
        List<Account> utenti = managerAmministrazione.visualizzaUtentiRegistrati();
        int result = utenti.size();
        assertEquals(expresult,result);
    }

    /**
     * Test of disabilitaCliente method, of class ManagerAmministrazione.
     */
    @Test
    public void disabilitaCliente() {
        System.out.println("disabilitaCliente");
        cliente.setAbilitato(false);
        managerAmministrazione.disabilitaCliente(cliente.getUsername());
        Cliente result = clienteDAO.doRetrieveById(cliente.getUsername(),cliente.getPassword());
        assertEquals(cliente.toString(),result.toString());
    }

    /**
     * Test of abilitaCliente method, of class ManagerAmministrazione.
     */
    @Test
    public  void abilitaCliente() {
        System.out.println("disabilitaCliente");
        cliente.setAbilitato(true);
        managerAmministrazione.abilitaCliente(cliente.getUsername());
        Cliente result = clienteDAO.doRetrieveById(cliente.getUsername(),cliente.getPassword());
        assertEquals(cliente.toString(),result.toString());
    }
}