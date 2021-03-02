package Test.DAO;

import DAO.ManagerDAO;
import Entities.Account;
import Entities.Manager;
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
 * @author Vincenzo Raia
 * @version 0.1
 * @since 29/01/2021
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ManagerDAOTest {

    private static Connection con;
    private static Manager manager;

    public ManagerDAOTest(){
        manager = new Manager("piero@pelu.com", "username", "Password", "Piero", "Pelu");
    }

    @BeforeClass
    public static void setUpClass() throws SQLException {
        con = DriverManagerConnectionPool.getConnection();
    }

    @AfterClass
    public static void tearDownClass() throws SQLException {
        PreparedStatement prst = con.prepareStatement("delete from account where username = '" + manager.getUsername() + "' and tipo=\"" + Account.TIPO_MANAGER + "\"");
        prst.execute();
        con.commit();
        prst.close();
        DriverManagerConnectionPool.releaseConnection(con);
        System.out.println("Database cancellato");
    }

    /**
     * Test of doRetrieveById method, of class ManagerDAO.
     */
    @Test
    public void doRetrieveById() {
        System.out.println("doRetrieveById");
        ManagerDAO instance = new ManagerDAO();
        String expResult = manager.getUsername();
        Manager result = instance.doRetrieveById(manager.getUsername());
        assertEquals(expResult, result.getUsername());

    }

    /**
     * Test of doRetrieveAll method, of class ManagerDAO.
     */
    @Test
    public void doRetrieveAll() {
        System.out.println("doRetrieveAll");
        List<Manager> result;
        ManagerDAO instance = new ManagerDAO();
        int expResult = 1;
        result = instance.doRetrieveAll();
        assertEquals(expResult, result.size());
    }


    /**
     * Test of doInsert method, of class ManagerDAO.
     */
    @Test
    public void doInsert() {
        System.out.println("doInsert");
        ManagerDAO instance = new ManagerDAO();
        int expResult = 0;
        int result = instance.doInsert(manager);
        assertEquals(expResult, result);
    }

    /**
     * Test of doUpdate method, of class ManagerDAO.
     */
    @Test
    public void doUpdate() {
        System.out.println("doUpdate");
        ManagerDAO instance = new ManagerDAO();
        int expResult = 0;
        manager.setCognome("modificato");
        int result = instance.doUpdate(manager);
        assertEquals(expResult, result);
    }
}