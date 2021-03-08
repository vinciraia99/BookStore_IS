package Test.Manager;

import DAO.CategoriaDAO;
import Entities.Autore;
import Entities.Categoria;
import Entities.Libro;
import Manager.ManagerLibri;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import java.sql.Connection;
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

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ManagerLibriTest {

    private static ManagerLibri managerLibri;
    private static Connection con;
    private static Libro libro;
    private static Categoria categoria;
    private static Autore autore;

    @BeforeClass
    public static void setUpClass() throws SQLException {
        managerLibri = new ManagerLibri();
        GregorianCalendar data_pubblicazione = new GregorianCalendar();
        libro = new Libro("1245672823", "test", 100, "trama con parola chiave", 102F, "passt6", data_pubblicazione, true);
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

    @AfterClass
    public static void tearDownClass() throws SQLException {
        /*con = DriverManagerConnectionPool.getConnection();
        PreparedStatement prst3 = con.prepareStatement("delete from autore where nomecompleto = '" + autore.getnomecompleto() + "'");
        PreparedStatement prst6 = con.prepareStatement("delete from libroautore where isbn = '" + libro.getIsbn() + "'");
        PreparedStatement prst4 = con.prepareStatement("delete from librocategoria where nome = '" + categoria.getNome() + "'");
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
        DriverManagerConnectionPool.releaseConnection(con);*/
        System.out.println("Database cancellato");
    }

    /**
     * Test of cercaLibro method, of class ManagerLibri.
     */
    @Test
    public void cercaLibro() {
        System.out.println("cercaLibro");
        List<Libro> ricerca = managerLibri.cercaLibro("parola");
        boolean result = false;
        if(ricerca.size() !=0){
            result = true;
        }
        boolean expresult = true;
        assertEquals(expresult,result);
    }

    /**
     * Test of aaggiuntaLibro method, of class ManagerLibri.
     */
    @Test
    public void aaggiuntaLibro() {
        System.out.println("aggiuntaLibro");
        boolean result =  managerLibri.aggiuntaLibro(libro);
        boolean expresult = true;
        assertEquals(expresult,result);
    }

    /**
     * Test of eliminaLibro method, of class ManagerLibri.
     */
    @Test
    public void eliminaLibro() {
        System.out.println("eliminaLibro");
        boolean result = managerLibri.eliminaLibro(libro.getIsbn());
        boolean expresult = true;
        assertEquals(expresult,result);
    }

    /**
     * Test of modificaLibro method, of class ManagerLibri.
     */
    @Test
    public void dmodificaLibro() {
        System.out.println("modificaLibro");
        libro.setTitolo("libromodificato");
        List<Categoria> l =  libro.getCategorie();
        l.remove(0);
        boolean result = managerLibri.modificaLibro(libro);
        boolean expresult = true;
        assertEquals(expresult,result);
    }

    /**
     * Test of modificaNumeroCopieLibro method, of class ManagerLibri.
     */
    @Test
    public void dmodificaNumeroCopieLibro() {
        System.out.println("modificaNumeroCopieLibro");
        boolean result = managerLibri.modificaNumeroCopieLibro(libro.getIsbn(),1500);
        boolean expresult = true;
        assertEquals(expresult,result);
    }

    /**
     * Test of acquisisciLibro method, of class ManagerLibri.
     */
    @Test
    public void acquisisciLibro() {
        System.out.println("acquisisciLibro");
        Libro l = managerLibri.acquisisciLibro(libro.getIsbn());
        String expresult = libro.getIsbn();
        String result = l.getIsbn();
        assertEquals(expresult,result);
    }

    /**
     * Test of acquisisciTuttiILibri method, of class ManagerLibri.
     */
    @Test
    public void acquisisciTuttiILibri() {
        System.out.println("acquisisciTuttiILibri");
        List<Libro> libri =  managerLibri.acquisisciTuttiILibri();
        boolean result = false;
        if(libri.size()!=0){
            result = true;
        }
        boolean expresult = true;
        assertEquals(expresult,result);

    }
}