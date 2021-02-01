package Test.DAO;

import DAO.LibroDAO;
import Entities.Autore;
import Entities.Libro;
import Utils.DriverManagerConnectionPool;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author Vincenzo Raia
 * @version 0.1
 * @since 28/01/2021
 */

class LibroDAOTest {

    private static Connection con;
    private static Libro libro;

    public LibroDAOTest() {
        GregorianCalendar data_pubblicazione = new GregorianCalendar();
        libro = new Libro("1245672823", "test", 100d, "trama con parola chiave", 102F, "passt6", data_pubblicazione, true);
        Autore autore = new Autore("Marco mengoni");
        autore.setId(1);
        List<Autore> autori = new ArrayList<>();
        autori.add(autore);
        libro.setAutori(autori);
    }

    @BeforeClass
    public static void setUpClass() throws SQLException {
        con = DriverManagerConnectionPool.getConnection();
    }

    @AfterClass
    public static void tearDownClass() throws SQLException {
        PreparedStatement prst = con.prepareStatement("truncate table Libro_Autore");
        PreparedStatement prst2 = con.prepareStatement("delete from Libro where isbn = '" + libro.getIsbn() + "'");
        prst.execute();
        prst2.execute();
        con.commit();
        prst.close();
        prst2.close();
        DriverManagerConnectionPool.releaseConnection(con);
        System.out.println("Database cancellato");
    }

    /**
     * Test of doRetriveAll method, of class LibroDAO.
     */
    @Test
    void doRetrieveAll() {
        System.out.println("doRetriveAll");
        LibroDAO instance = new LibroDAO();
        List<Libro> expResult = new ArrayList<>();
        expResult.add(libro);
        List<Libro> result = instance.doRetrieveAll();
        assertEquals(expResult.size(), result.size());
    }

    /**
     * Test of doInsert method, of class LibroDAO.
     */
    @Test
    void doInsert() {
        System.out.println("doInsert");
        LibroDAO instance = new LibroDAO();
        int expResult = 0;
        int result = instance.doInsert(libro);
        assertEquals(expResult, result);
    }

    /**
     * Test of doUpdate method, of class LibroDAO.
     */
    @Test
    void doUpdate() {
        System.out.println("doUpdate");
        libro.setTrama("trama nuova");
        LibroDAO instance = new LibroDAO();
        int expResult = 0;
        int result = instance.doUpdate(libro);
        assertEquals(expResult, result);

        Libro retrivedLibro = instance.doRetrieveById(libro.getIsbn());
        assertEquals(libro.getTrama(), retrivedLibro.getTrama());

    }

    /**
     * Test of doRetrieveById method, of class LibroDAO.
     */
    @Test
    void doRetrieveById() {
        System.out.println("doRetrieveById");
        libro.setTrama("trama nuova");
        LibroDAO instance = new LibroDAO();
        String expResult = libro.getIsbn();
        Libro result = instance.doRetrieveById("1245672823");
        assertEquals(expResult, result.getIsbn());
    }


    /**
     * Test of testDoRetrieveByNomeOrDescrizione method, of class LibroDAO.
     */
    @Test
    void testDoRetrieveByNomeOrDescrizione() {
        System.out.println("doRetrieveByNomeOrDescrizione");
        LibroDAO instance = new LibroDAO();
        int expResult = 1;
        List<Libro> result = instance.doRetrieveByNomeOrDescrizione("parola");
        assertEquals(expResult, result.size());
    }
}