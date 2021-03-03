package Test.Manager;

import Entities.Cliente;
import Entities.Indirizzo;
import Manager.ManagerRegistrazione;
import Utils.DriverManagerConnectionPool;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author Vincenzo Raia
 * @version 0.1
 * @since 01/03/2021
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ManagerRegistrazioneTest {

    private static Indirizzo indirizzo ;
    private static Cliente cliente;
    private static ManagerRegistrazione managerRegistrazione;
    private static Connection con;

    @BeforeClass
    public static void setUpClass() throws SQLException {
       indirizzo = new Indirizzo("Via Franco 5", "Torre Annunziata", "Na", 80120);
       cliente =  new Cliente("paolo@fox.com", "account1", "password1", "Paolo", "Fox", indirizzo);
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
    public void aregistraCliente() {
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