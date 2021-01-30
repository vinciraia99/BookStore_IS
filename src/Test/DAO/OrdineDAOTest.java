package Test.DAO;

import DAO.ClienteDAO;
import DAO.LibroDAO;
import DAO.OrdineDAO;
import Entities.*;
import Utils.DriverManagerConnectionPool;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.jupiter.api.Test;

import javax.print.attribute.standard.OrientationRequested;
import java.lang.ref.Cleaner;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Vincenzo Raia
 * @version 0.1
 * @since 28/01/2021
 */

class OrdineDAOTest {

    private static Connection con;
    private static Ordine ordine;
    private static Cliente cliente;


    public OrdineDAOTest() {
        GregorianCalendar data_pubblicazione = new GregorianCalendar(2010,2,22);
        Libro libro = new Libro("124567282132", "test", 100d, "trama", 102F, "passt6", data_pubblicazione, true);
        Autore autore = new Autore("Marco mengoni");
        List<Autore> autori = new ArrayList<>();
        autori.add(autore);
        libro.setAutori(autori);

        Libro libro2 = new Libro("124567182132", "test", 100d, "trama", 102F, "pas2st6", data_pubblicazione, true);
        Autore autore2 = new Autore("Marco mengoni");
        List<Autore> autori2 = new ArrayList<>();
        autori.add(autore2);
        libro2.setAutori(autori2);

        cliente = new Cliente("pippo@pippo.com","utente1","password","nome","cognome");
        Indirizzo indirizzo = new Indirizzo("via brembate 5","comune","provincia",80010,"nota");
        cliente.setIndirizzo(indirizzo);

        ClienteDAO clienteDAO = new ClienteDAO();
        clienteDAO.doInsert(cliente);

        LibroDAO libroDAO = new LibroDAO();
        libroDAO.doInsert(libro);
        libroDAO.doInsert(libro2);

        GregorianCalendar data_ordine = new GregorianCalendar();
        ordine = new Ordine(100,10f, data_ordine,"utente1");
        LibroOrdinato libroOrdinato = new LibroOrdinato(2,libro.getPrezzo(),libro.getIsbn());
        LibroOrdinato libroOrdinato2= new LibroOrdinato(1,libro2.getPrezzo(),libro2.getIsbn());
        List<LibroOrdinato> libriordinati = new ArrayList<>();
        libriordinati.add(libroOrdinato);
        libriordinati.add(libroOrdinato2);
        ordine.setLibriOrdinati((ArrayList<LibroOrdinato>) libriordinati);

    }

    @BeforeClass
    public static void setUpClass() throws SQLException {
        con = DriverManagerConnectionPool.getConnection();
    }

    @AfterClass
    public static void tearDownClass() throws SQLException {
        PreparedStatement prst = con.prepareStatement("truncate table libro");
        PreparedStatement prst2 = con.prepareStatement("truncate table libroordinato");
        PreparedStatement prst3 = con.prepareStatement("truncate table ordine");
        PreparedStatement prst4 = con.prepareStatement("truncate table autore");
        PreparedStatement prst5 = con.prepareStatement("truncate table libroautore");
        PreparedStatement prst6 = con.prepareStatement("truncate table indirizzo");
        prst.execute();
        prst2.execute();
        prst3.execute();
        prst4.execute();
        prst5.execute();
        prst6.execute();
        con.commit();
        DriverManagerConnectionPool.releaseConnection(con);
        System.out.println("Database cancellato");
    }


    /**
     * Test of doInsert method, of class OrdineDAO.
     */
    @Test
    void doInsert() {
        System.out.println("doInsert");
        OrdineDAO instance = new OrdineDAO();
        int expResult = 0;
        int result = instance.doInsert(ordine);
        assertEquals(expResult, result);
    }

    /**
     * Test of doInsert method, of class doRetriveByIdCliente.
     */
    @Test
    void doRetriveByIdCliente() {
        System.out.println("doRetriveByIdClinte");
        OrdineDAO instance = new OrdineDAO();
        List<Ordine> expResult = new ArrayList<>();
        expResult.add(ordine);
        List<Ordine> result  = instance.doRetriveByIdCliente(cliente.getUsername());
        assertEquals(expResult.size(), result.size());
    }

    /**
     * Test of doInsert method, of class doUpdateOrderState.
     */
    @Test
    void doUpdateOrderState() {
        System.out.println("doUpdateOrderState");
        OrdineDAO instance = new OrdineDAO();
        int expResult = 0;
        ordine.setId(1);
        int result = instance.doUpdateOrderState(ordine);
        assertEquals(expResult, result);
    }
}