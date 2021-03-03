package Test.DAO;

import DAO.CategoriaDAO;
import DAO.LibroDAO;
import Entities.Autore;
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
 * @author Vincenzo Raia
 * @version 0.1
 * @since 28/01/2021
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class LibroDAOTest {

    private static Connection con;
    private static Libro libro;
    private static Categoria categoria;
    private static Autore autore;

    public LibroDAOTest() {
        GregorianCalendar data_pubblicazione = new GregorianCalendar();
        libro = new Libro("1245672823", "test", 100d, "trama con parola chiave", 102F, "passt6", data_pubblicazione, true);
        autore = new Autore("Marco mengoni");
        autore.setId(1);
        List<Autore> autori = new ArrayList<>();
        autori.add(autore);
        libro.setAutori(autori);
        categoria = new Categoria("categoria 1", "questa è una categoria");
        CategoriaDAO categoriaDAO =  new CategoriaDAO();
        categoriaDAO.doInsert(categoria);
        ArrayList<Categoria> categorie = (ArrayList<Categoria>) categoriaDAO.doRetrieveAll();
        for(Categoria c : categorie){
            if(categoria.getNome().equals(c.getNome())){
                categoria = c;
                break;
            }
        }
        List<Categoria> categoriee = new ArrayList<>();
        categoriee.add(categoria);
        libro.setCategorie(categoriee);
    }

    @BeforeClass
    public static void setUpClass() throws SQLException {
        con = DriverManagerConnectionPool.getConnection();
    }

    @AfterClass
    public static void tearDownClass() throws SQLException {
        PreparedStatement prst3 = con.prepareStatement("delete from autore where nomecompleto = '" + autore.getnomecompleto() + "'");
        PreparedStatement prst6 = con.prepareStatement("delete from libroautore where isbn = '" + libro.getIsbn() + "'");
        PreparedStatement prst4 = con.prepareStatement("delete from librocategoria where id = '" + categoria.getId() + "'");
        PreparedStatement prst5 = con.prepareStatement("delete from categoria where nome = '" + categoria.getNome() + "'");
        PreparedStatement prst = con.prepareStatement("delete from libroautore where isbn = '" + libro.getIsbn() + "'");
        PreparedStatement prst2 = con.prepareStatement("delete from Libro where isbn = '" + libro.getIsbn() + "'");

        prst5.execute();
        prst2.execute();
        prst6.execute();
        prst4.execute();
        prst3.execute();
        prst.execute();
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
    public void doRetrieveAll() {
        System.out.println("doRetrieveAll");
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
    public void doInsert() {
        System.out.println("doInsert");
        LibroDAO instance = new LibroDAO();
        int expResult = 0;
        int result = instance.doInsert(libro);
        assertEquals(expResult, result);
    }

    /**
     * Test of testDoRetrieveByNomeOrDescrizione method, of class LibroDAO.
     */
    @Test
    public void doRetrieveByNomeOrDescrizione() {
        System.out.println("doRetrieveByNomeOrDescrizione");
        LibroDAO instance = new LibroDAO();
        int expResult = 1;
        List<Libro> result = instance.doRetrieveByNomeOrDescrizione("parola");
        assertEquals(expResult, result.size());
    }

    /**
     * Test of doUpdate method, of class LibroDAO.
     */
    @Test
    public void doUpdate() {
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
    public void doRetrieveById() {
        System.out.println("doRetrieveById");
        LibroDAO instance = new LibroDAO();
        String expResult = libro.getIsbn();
        Libro result = instance.doRetrieveById("1245672823");
        assertEquals(expResult, result.getIsbn());
    }



}