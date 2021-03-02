package Test.Manager;

import DAO.CategoriaDAO;
import Entities.Carrello;
import Entities.Categoria;
import Manager.ManagerCarrello;
import Manager.ManagerCategorie;
import Utils.DriverManagerConnectionPool;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import javax.xml.crypto.OctetStreamData;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author Vincenzo Raia
 * @version 0.1
 * @since 01/03/2021
 */

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ManagerCategorieTest {

    private static Categoria categoria;
    private static Categoria categoriaadded;
    private static ManagerCategorie managerCategorie;
    private static Connection con;

    @BeforeClass
    public static void setUpClass() throws SQLException {
        categoria = new Categoria("nome","sono una descrizione");
        categoriaadded = new Categoria("nome2","sono una descrizione2");
        CategoriaDAO categoriaDAO = new CategoriaDAO();
        categoriaDAO.doInsert(categoriaadded);
        for(Categoria c : categoriaDAO.doRetrieveAll()){
            if(c.getNome().equals(categoriaadded.getNome())){
                categoriaadded.setId(c.getId());
            }
        }

        managerCategorie = new ManagerCategorie();

    }

    @AfterClass
    public static void tearDownClass() throws SQLException {
        con = DriverManagerConnectionPool.getConnection();
        PreparedStatement prst2 = con.prepareStatement("delete from Categoria where id = '" + categoriaadded.getId() + "'");
        prst2.execute();
        con.commit();
        prst2.close();
        DriverManagerConnectionPool.releaseConnection(con);
        System.out.println("Database cancellato");
    }

    /**
     * Test of aggiungiCategoria method, of class ManagerCategorie.
     */
    @Test
    public void aggiungiCategoria() {
        boolean result = managerCategorie.aggiungiCategoria(categoria.getNome(),categoria.getDescrizione());
        boolean expresult = true;
        assertEquals(expresult,result);
    }

    /**
     * Test of acquisisciCategoria method, of class ManagerCategorie.
     */
    @Test
    public void acquisisciCategoria() {
        Categoria categoria= managerCategorie.acquisisciCategoria(categoriaadded.getId());
        String result = categoria.getNome();
        String expresult = categoriaadded.getNome();
        assertEquals(expresult,result);
    }

    /**
     * Test of acquisisciTutteLeCategorie method, of class ManagerCategorie.
     */
    @Test
    public void acquisisciTutteLeCategorie() {
        List<Categoria> categorie= managerCategorie.acquisisciTutteLeCategorie();
        boolean result = false;
        if(categorie != null && categorie.size() >1){
            result =true;
        }
        boolean expresult = true;
        assertEquals(expresult,result);
    }

    /**
     * Test of eemodificaCategoria method, of class ManagerCategorie.
     */
    @Test
    public void eemodificaCategoria() {
        categoriaadded.setNome("modificata");
        managerCategorie.modificaCategoria(categoriaadded.getNome(),categoriaadded.getDescrizione(),categoriaadded.getId());
        CategoriaDAO categoriaDAO = new CategoriaDAO();
        Categoria categoriagettted = categoriaDAO.doRetrieveById(categoriaadded.getId());
        String result = categoriagettted.getNome();
        String expresult = categoriagettted.getNome();
        assertEquals(expresult,result);
    }

    /**
     * Test of eliminaCategoria method, of class ManagerCategorie.
     */
    @Test
    public void eliminaCategoria() {
        boolean expresult = true;
        boolean result = managerCategorie.eliminaCategoria(categoriaadded.getId());
        assertEquals(expresult,result);
    }




}