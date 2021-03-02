package Test.Manager;

import DAO.CategoriaDAO;
import DAO.ClienteDAO;
import DAO.LibroDAO;
import DAO.OrdineDAO;
import Entities.*;
import Manager.ManagerOrdini;
import Utils.DriverManagerConnectionPool;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

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
 * @since 01/03/2021
 */

public class ManagerOrdiniTest {
    private static ManagerOrdini managerOrdini;
    private static Connection con;
    private static Ordine ordine;
    private static Cliente cliente;
    private static Libro libro;
    private static Autore autore;
    private static Categoria categoria;
    private static Carrello carrello;

    @BeforeClass
    public static void setUpClass() throws SQLException {
        managerOrdini = new ManagerOrdini();
        GregorianCalendar data_pubblicazione = new GregorianCalendar(2010,2,22);
        libro = new Libro("124567282130", "test", 100d, "trama", 102F, "passt6", data_pubblicazione, true);
        autore = new Autore("Marco mengoni");
        List<Autore> autori = new ArrayList<>();
        autori.add(autore);
        libro.setAutori(autori);

        CategoriaDAO categoriaDAO = new CategoriaDAO();
        categoria = new Categoria("nome","desc");
        ArrayList<Categoria> categorie = new ArrayList<Categoria>();
        categoriaDAO.doInsert(categoria);
        ArrayList<Categoria> categorie2 = (ArrayList<Categoria>) categoriaDAO.doRetrieveAll();
        for(Categoria c : categorie2){
            if(categoria.getNome().equals(c.getNome())){
                categoria = c;
                break;
            }
        }
        categorie.add(categoria);
        libro.setCategorie(categorie);
        cliente = new Cliente("pippo@pippo.com","utente1","password","nome","cognome");
        Indirizzo indirizzo = new Indirizzo("via brembate 5","comune","provincia",80010,"nota");
        cliente.setIndirizzo(indirizzo);

        ClienteDAO clienteDAO = new ClienteDAO();
        clienteDAO.doInsert(cliente);

        LibroDAO libroDAO = new LibroDAO();
        libroDAO.doInsert(libro);

        GregorianCalendar data_ordine = new GregorianCalendar();
        ordine = new Ordine(100,10f, data_ordine,"utente1");
        LibroOrdinato libroOrdinato = new LibroOrdinato(2,libro.getPrezzo(),libro.getIsbn());
        List<LibroOrdinato> libriordinati = new ArrayList<>();
        libriordinati.add(libroOrdinato);
        ordine.setLibriOrdinati(libriordinati);
        carrello = new Carrello();
        OrdineDAO ordineDAO = new OrdineDAO();
        ordineDAO.doInsert(ordine);

        ArrayList<Ordine> ordini = (ArrayList<Ordine>) ordineDAO.doRetriveAll();
        for(Ordine o : ordini){
            if(ordine.getTotale() == o.getTotale()){
               ordine = o;
            }
        }

    }

    @AfterClass
    public static void tearDownClass() throws SQLException {
        con = DriverManagerConnectionPool.getConnection();
        PreparedStatement prst2 = con.prepareStatement("Delete from libroordinato where isbn = '" + libro.getIsbn() + "'");
        PreparedStatement prst = con.prepareStatement("Delete from libro where isbn = '" + libro.getIsbn() + "'");
        PreparedStatement prst3 = con.prepareStatement("Delete from ordine where username = '" + cliente.getUsername() + "'");
        PreparedStatement prst5 = con.prepareStatement("Delete from libroautore where isbn = '" + libro.getIsbn() + "'");
        PreparedStatement prst4 = con.prepareStatement("Delete from autore where nomecompleto ='" + autore.getnomecompleto() + "'");
        PreparedStatement prst6 = con.prepareStatement("Delete from indirizzo where username = '" + cliente.getUsername() + "'");
        PreparedStatement prst7 = con.prepareStatement("Delete from account where username = '" + cliente.getUsername() + "'");
        PreparedStatement prst8 = con.prepareStatement("Delete from categoria where nome = '" + categoria.getNome() + "'");
        PreparedStatement prst9 = con.prepareStatement("Delete from librocategoria where id = '" + categoria.getId() + "'");
        PreparedStatement prst10 = con.prepareStatement("Delete from ordine where id = '" + ordine.getId() + "'");

        prst3.execute();
        prst6.execute();
        prst7.execute();


        prst4.execute();
        prst5.execute();

        prst.execute();
        prst2.execute();
        prst10.execute();

        con.commit();
        DriverManagerConnectionPool.releaseConnection(con);
        System.out.println("Database cancellato");

    }

    /**
     * Test of effettuaOrdine method, of class ManagerOrdini.
     */
    @Test
    public void effettuaOrdine() {
        System.out.println("effettuaOrdine");
        carrello.aggiungiLibro(libro);
        boolean result = managerOrdini.effettuaOrdine(cliente,carrello,"123","1234567891234567","2","2022");
        boolean expresult = true;
        assertEquals(expresult, result);
    }

    /**
     * Test of visualizzaOrdini method, of class ManagerOrdini.
     */
    @Test
    public void visualizzaOrdini() {
        System.out.println("visualizzaOrdini");
        List<Ordine> ordini = managerOrdini.visualizzaOrdini(cliente);
        boolean result= false;
        if(ordini.size()!=0){
            result = true;
        }
        boolean expresult=true;
        assertEquals(expresult,result);
    }

    /**
     * Test of visualizzaDettaglioOrdine method, of class ManagerOrdini.
     */
    @Test
    public void visualizzaDettaglioOrdine() {
        System.out.println("visualizzaDettaglioOrdine");
        Ordine ordinegetted = managerOrdini.visualizzaDettaglioOrdine(cliente,ordine.getId());
        int result= ordinegetted.getId();
        int expresult= ordine.getId();
        assertEquals(expresult,result);
    }


    /**
     * Test of visualizzaOrdiniUtenti method, of class ManagerOrdini.
     */
    @Test
    public void visualizzaOrdiniUtenti() {
        System.out.println("visualizzaOrdiniUtenti");
        List<Ordine> ordini = managerOrdini.visualizzaOrdiniUtenti();
        boolean result= false;
        if(ordini.size()!=0){
            result = true;
        }
        boolean expresult= true;
        assertEquals(expresult,result);
    }


    /**
     * Test of visualizzaDettaglioOrdineUtente method, of class ManagerOrdini.
     */
    @Test
    public void visualizzaDettaglioOrdineUtente() {
        System.out.println("visualizzaDettaglioOrdineUtente");
        Ordine ordinegetted = managerOrdini.visualizzaDettaglioOrdineUtente(ordine.getId());
        int result= ordinegetted.getId();
        int expresult= ordine.getId();
        assertEquals(expresult,result);
    }


    /**
     * Test of modificaStatoOrdine method, of class ManagerOrdini.
     */
    @Test
    public void modificaStatoOrdine() {
        System.out.println("modificaStatoOrdine");
        boolean result = managerOrdini.modificaStatoOrdine(ordine.getId(),true);
        boolean expresult = true;
        assertEquals(expresult,result);

    }
}