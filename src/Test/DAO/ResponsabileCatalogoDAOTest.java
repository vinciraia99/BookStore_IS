package Test.DAO;

import DAO.LibroDAO;
import DAO.ResponsabileCatalogoDAO;
import Entities.Cliente;
import Entities.Ordine;
import Entities.ResponsabileCatalogo;
import Utils.DriverManagerConnectionPool;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Vincenzo Raia
 * @version 0.1
 * @since 29/01/2021
 */

class ResponsabileCatalogoDAOTest {

    private static Connection con;
    private static ResponsabileCatalogo responsabileCatalogo;

    public ResponsabileCatalogoDAOTest(){
        responsabileCatalogo = new ResponsabileCatalogo("piero@pelu.com", "username", "Password", "Piero", "Pelu");
    }

    @BeforeClass
    public static void setUpClass() throws SQLException {
        con = DriverManagerConnectionPool.getConnection();
    }

    @AfterClass
    public static void tearDownClass() throws SQLException {
        PreparedStatement prst = con.prepareStatement("delete from account where tipo=\"" + responsabileCatalogo.TIPO_RESPONSABILE_CATALOGO + "\"");
        prst.execute();
        con.commit();
        prst.close();
        DriverManagerConnectionPool.releaseConnection(con);
        System.out.println("Database cancellato");
    }

    /**
     * Test of doRetrieveById method, of class ResponsabileCatalogoDAO.
     */
    @Test
    void doRetrieveById() {
        System.out.println("doRetrieveById");
        ResponsabileCatalogoDAO instance = new ResponsabileCatalogoDAO();
        String expResult = responsabileCatalogo.getUsername();
        ResponsabileCatalogo result = instance.doRetrieveById(responsabileCatalogo.getUsername());
        assertEquals(expResult, result.getUsername());

    }

    /**
     * Test of doRetrieveAll method, of class ResponsabileCatalogoDAO.
     */
    @Test
    void doRetrieveAll() {
        System.out.println("doRetrieveAll");
        List<ResponsabileCatalogo> result;
        ResponsabileCatalogoDAO instance = new ResponsabileCatalogoDAO();
        int expResult = 1;
        result = instance.doRetrieveAll();
        assertEquals(expResult, result.size());
    }


    /**
     * Test of doInsert method, of class ResponsabileCatalogoDAO.
     */
    @Test
    void doInsert() {
        System.out.println("doInsert");
        ResponsabileCatalogoDAO instance = new ResponsabileCatalogoDAO();
        int expResult = 0;
        int result = instance.doInsert(responsabileCatalogo);
        assertEquals(expResult, result);
    }

    /**
     * Test of doUpdate method, of class ResponsabileCatalogoDAO.
     */
    @Test
    void doUpdate() {
        System.out.println("doUpdate");
        ResponsabileCatalogoDAO instance = new ResponsabileCatalogoDAO();
        int expResult = 0;
        responsabileCatalogo.setCognome("modificato");
        int result = instance.doUpdate(responsabileCatalogo);
        assertEquals(expResult, result);
    }
}