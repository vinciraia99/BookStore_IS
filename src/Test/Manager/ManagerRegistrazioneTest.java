package Test.Manager;

import Entities.Cliente;
import Entities.Indirizzo;
import Manager.ManagerRegistrazione;
import Utils.DriverManagerConnectionPool;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author Vincenzo Raia
 * @version 0.1
 * @since 01/03/2021
 */

public class ManagerRegistrazioneTest {

    private static Indirizzo indirizzo ;
    private static Cliente cliente;
    private static ManagerRegistrazione managerRegistrazione;
    private static Connection con;

    @BeforeClass
    public static void setUpClass() throws SQLException {
       indirizzo = new Indirizzo("Via Carlo 5", "Salerno", "SA", 84012);
       cliente =  new Cliente("piero@pelu.com", "Piero", "Password", "Piero", "Pelu", indirizzo);
       managerRegistrazione = new ManagerRegistrazione();
    }

    @AfterClass
    public static void tearDownClass() throws SQLException {
        con = DriverManagerConnectionPool.getConnection();
        PreparedStatement prst = con.prepareStatement("Delete from account where username = '" + cliente.getUsername() + "'");
        prst.execute();
        con.commit();
        prst.close();
        DriverManagerConnectionPool.releaseConnection(con);
        System.out.println("Database cancellato");
    }

    /**
     * Test of registraCliente method, of class ManagerRegistrazione.
     */
    @Test
    public void registraCliente() {
        System.out.println("registraCliente");
        boolean result = managerRegistrazione.registraCliente(cliente.getEmail(),cliente.getUsername(),cliente.getPassword(),cliente.getNome(),cliente.getCognome(),cliente.getIndirizzo());
        boolean expresult = true;
        assertEquals(expresult,result);
    }

    /**
     * Test of confermaRegistrazione method, of class ManagerRegistrazione.
     */
    @Test
    public void confermaRegistrazione() {
        System.out.println("confermaRegistrazione");
        boolean result = managerRegistrazione.confermaRegistrazione(cliente.getUsername(),cliente.getEmail());
        boolean expresult = true;
        assertEquals(expresult,result);
    }
}