package Test.Utils;

import Utils.DriverManagerConnectionPool;
import org.junit.*;

import java.sql.Connection;
import java.sql.SQLException;

import static org.junit.Assert.assertEquals;

/**
 * @author Vincenzo Raia
 * @version 0.1
 * @since 27/01/2021
 */
public class DriverManagerConnectionPoolTest {

    public DriverManagerConnectionPoolTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of getConnection method, of class DriverManagerConnectionPool.
     *
     * @throws java.lang.Exception
     */
    @Test
    public void testGetConnection() throws Exception {
        System.out.println("getConnection");
        String dbUrl = "jdbc:mysql://localhost:3306/bookstoredb?characterEncoding=UTF8&useUnicode=yes&serverTimezone=Europe/Rome";
        Connection result = DriverManagerConnectionPool.getConnection();
        assertEquals(dbUrl, result.getMetaData().getURL());

    }

    /**
     * Test of releaseConnection method, of class DriverManagerConnectionPool.
     */
    @Test
    public void testReleaseConnection() throws SQLException {
        System.out.println("releaseConnection");
        Connection connection = DriverManagerConnectionPool.getConnection();
        DriverManagerConnectionPool.releaseConnection(connection);
        //assertTrue(DriverManagerConnectionPool.);

    }

}
