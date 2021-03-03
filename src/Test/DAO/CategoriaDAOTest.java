package Test.DAO;

import DAO.CategoriaDAO;
import Entities.Categoria;
import Entities.Libro;
import Utils.DriverManagerConnectionPool;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;


/**
 * @author  Raffaele Scarpa
 * @version 0.1
 * @since 29/01/2021
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class CategoriaDAOTest {

    private static Connection con;
    private static Categoria categoria;
    private static GregorianCalendar data_pubblicazione = new GregorianCalendar();
    private static Libro libro;


    public CategoriaDAOTest(){
        categoria = new Categoria( 1, "Horror", "Libri horror");
        libro = new Libro("1245672823", "test", 100d, "trama", 102F, "passt6", data_pubblicazione, true);
        List<Libro> libri = new ArrayList<>();
        libri.add(libro);
        categoria.setLibri(libri);
    }

    @BeforeClass
    public static void setUpClass() throws SQLException {
        con = DriverManagerConnectionPool.getConnection();
    }

    @AfterClass
    public static void tearDownClass() throws SQLException {
        PreparedStatement prst = con.prepareStatement("delete from categoria where nome=\"" + categoria.getNome() + "\"");
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
        Categoria result = null;
        CategoriaDAO categorie = new CategoriaDAO();
        List<Categoria> lista = categorie.doRetrieveAll();
        for (Categoria categoria2 : lista ){
            System.out.println(categoria2.getNome());
            if(categoria2.getNome().equals(categoria.getNome()))
                result = categoria2;
        };
        String expResult = categoria.getNome();
        assertEquals(expResult, result.getNome());
    }

    /**
     * Test DoRetrieveAll
     * Versione: 0.1
     * Autore: Raffaele Scarpa
     */

    @Test
    public void testDoRetrieveAll() {
        System.out.println("doRetrieveAll");
        CategoriaDAO categorie = new CategoriaDAO();
        int expResult = 1;
        List<Categoria> result = categorie.doRetrieveAll();
        assertEquals(expResult, result.size());

    }

    /**
     * Test DoInsertCategoria
     * Versione: 0.1
     * Autore: Raffaele Scarpa
     */

    @Test
    public void testDoInsertCategoria() {
        System.out.println("doInsertCategoria");
        CategoriaDAO categorie = new CategoriaDAO();
        int result;
        result = categorie.doInsert(categoria);
        int expResult = 0;
        assertEquals(expResult, result);
    }

    /**
     * Test DoUpdateCategoria
     * Versione: 0.1
     * Autore: Raffaele Scarpa
     */


    @Test
    public void testDoUpdateCategoria() {
        System.out.println("doUpdateCategoria");
        CategoriaDAO instance = new CategoriaDAO();
        int expResult = 0;
        categoria.setDescrizione("modificato");
        int result = instance.doUpdate(categoria);
        assertEquals(expResult, result);
    }

    /**
     * Test DoDeleteCategoria
     * Versione: 0.1
     * Autore: Raffaele Scarpa
     */


    @Test
    public void testDoDeleteCategoria() {
        System.out.println("doDeleteCategoria");
        CategoriaDAO categorie = new CategoriaDAO();
        int result = categorie.doDelete(categoria);
        int expResult = 0;
        assertEquals(expResult, result);
    }


}
