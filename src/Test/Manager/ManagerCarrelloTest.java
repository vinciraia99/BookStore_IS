package Test.Manager;

import Entities.Carrello;
import Entities.Libro;
import Entities.LibroOrdinato;
import Manager.ManagerCarrello;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Vincenzo Raia
 * @version 0.1
 * @since 01/03/2021
 */

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ManagerCarrelloTest {

    private static ManagerCarrello managerCarrello;
    private static Libro libro;
    private static Carrello carrello;

    @BeforeClass
    public static void setUpClass() throws SQLException {
        managerCarrello = new ManagerCarrello();
        GregorianCalendar data_pubblicazione = new GregorianCalendar();
        libro = new Libro("1245672823", "test", 100d, "trama con parola chiave", 102F, "passt6", data_pubblicazione, true);
        carrello = new Carrello();
    }

    @AfterClass
    public static void tearDownClass() throws SQLException {

    }

    /**
     * Test of aggiungiLibroCarrello method, of class ManagerCarrello.
     */
    @Test
    public void aggiungiLibroCarrello() {
        managerCarrello.aggiungiLibroCarrello(carrello,libro);
        int expresult = 1;
        assertEquals(expresult,carrello.getLibri().size());

    }

    /**
     * Test of modificaQuantitaCarrello method, of class ManagerCarrello.
     */
    @Test
    public void modificaQuantitaCarrello() {
        managerCarrello.modificaQuantitaCarrello(carrello,libro,100);
        int expresult=100;
        assertEquals(expresult,carrello.getLibri().get(0).getQuantita());
    }

    /**
     * Test of rimuoviLibroCarrello method, of class ManagerCarrello.
     */
    @Test
    public void rimuoviLibroCarrello() {
        managerCarrello.rimuoviLibroCarrello(carrello,libro);
        int expresult = 0;
        assertEquals(expresult,carrello.getLibri().size());

    }


}