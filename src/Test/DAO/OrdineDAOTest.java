package Test.DAO;

import DAO.CategoriaDAO;
import DAO.ClienteDAO;
import DAO.LibroDAO;
import DAO.OrdineDAO;
import Entities.*;
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
public class OrdineDAOTest {

    private static Connection con;
    private static Ordine ordine;
    private static Cliente cliente;
    private static Libro libro;
    private static Autore autore;
    private static Categoria categoria;

    public OrdineDAOTest() {
    }

    @BeforeClass
    public static void setUpClass() throws SQLException {
        con = DriverManagerConnectionPool.getConnection();
        GregorianCalendar data_pubblicazione = new GregorianCalendar(2010,2,22);
        libro = new Libro("124567282130", "test", 100, "trama", 102F, "passt6", data_pubblicazione, true);
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
    }

    @AfterClass
    public static void tearDownClass() throws SQLException {

        PreparedStatement prst2 = con.prepareStatement("Delete from libroordinato where isbn = '" + libro.getIsbn() + "'");
        PreparedStatement prst = con.prepareStatement("Delete from libro where isbn = '" + libro.getIsbn() + "'");
        PreparedStatement prst3 = con.prepareStatement("Delete from ordine where username = '" + cliente.getUsername() + "'");
        PreparedStatement prst5 = con.prepareStatement("Delete from libroautore where isbn = '" + libro.getIsbn() + "'");
        PreparedStatement prst4 = con.prepareStatement("Delete from autore where nomecompleto ='" + autore.getnomecompleto() + "'");
        PreparedStatement prst6 = con.prepareStatement("Delete from indirizzo where username = '" + cliente.getUsername() + "'");
        PreparedStatement prst7 = con.prepareStatement("Delete from account where username = '" + cliente.getUsername() + "'");
        PreparedStatement prst8 = con.prepareStatement("Delete from categoria where nome = '" + categoria.getNome() + "'");
        PreparedStatement prst9 = con.prepareStatement("Delete from librocategoria where nome = '" + categoria.getNome() + "'");

        prst3.execute();
        prst6.execute();
        prst7.execute();


        prst4.execute();
        prst5.execute();

        prst.execute();
        prst2.execute();

        con.commit();
        DriverManagerConnectionPool.releaseConnection(con);
        System.out.println("Database cancellato");
    }


    /**
     * Test of doInsert method, of class OrdineDAO.
     */
    @Test
    public void doInsert() {
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
    public void doRetriveByIdCliente() {
        System.out.println("doRetriveByIdClinte");
        OrdineDAO instance = new OrdineDAO();
        boolean expResult = true;
        List<Ordine> list  = instance.doRetriveByIdCliente(cliente.getUsername());
        boolean result =false;
        if(list != null && list.size()!=0){
            result = true;
        }
        assertEquals(expResult, result);
    }

    /**
     * Test of doInsert method, of class doUpdateOrderState.
     */
    @Test
    public void doUpdateOrderState() {
        System.out.println("doUpdateOrderState");
        OrdineDAO instance = new OrdineDAO();
        int expResult = 0;
        ordine.setId(1);
        int result = instance.doUpdateOrderState(ordine);
        assertEquals(expResult, result);
    }
}