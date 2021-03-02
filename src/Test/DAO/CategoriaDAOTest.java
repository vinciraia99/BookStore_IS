/**
 *
 */
package Test.DAO;

import DAO.CategoriaDAO;
import Entities.Categoria;
import Entities.Libro;
import org.junit.Test;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author Raffaele Scarpa
 * @version 0.1
 * 29/01/2021
 */



class CategoriaDAOTest {


	private static Categoria categoria = new Categoria(1, "Horror", "Libri horror");
	private static GregorianCalendar data_pubblicazione = new GregorianCalendar();
	private static Libro libro = new Libro("1245672823", "test", 100d, "trama", 102F, "passt6", data_pubblicazione, true);
	
	
    /**
     * Test DoRetrieveById
     * Versione: 0.1
     * Autore: Raffaele Scarpa
     */

    @Test
    final void testDoRetrieveById() {
        int id = 1;
        CategoriaDAO categorie = new CategoriaDAO();
        int expResult = categoria.getId();
        Categoria result = categorie.doRetrieveById(categoria.getId());
        assertEquals(expResult,result.getId());
    }

    /**
     * Test DoRetrieveAll
     * Versione: 0.1
     * Autore: Raffaele Scarpa
     */
    @Test
    final void testDoRetrieveAll() {
        CategoriaDAO categorie = new CategoriaDAO();
        List<Categoria> expResult = new ArrayList<>();
        expResult.add(categoria);
        List<Categoria> result = categorie.doRetrieveAll();
        assertEquals(expResult.size(), result.size());
        
    }

    /**
     * Test DoInsertCategoria
     * Versione: 0.1
     * Autore: Raffaele Scarpa
     */
    @Test
    final void testDoInsertCategoria() {
    	List<Libro> libri = new ArrayList<>();
    	libri.add(libro);
    	categoria.setLibri(libri);
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
    final void testDoUpdateCategoria() {
        Categoria aggiorna = new Categoria(1, "Thriller", "Libri horror");
        CategoriaDAO categorie = new CategoriaDAO();
        int result = categorie.doUpdate(aggiorna);
        int expResult = 0;
        assertEquals(expResult, result);
        
        
        Categoria updatedCategoria = categorie.doRetrieveById(aggiorna.getId());
        assertEquals(updatedCategoria.getId(),aggiorna.getId());
    }

    /**
     * Test DoDeleteCategoria
     * Versione: 0.1
     * Autore: Raffaele Scarpa
     */
    @Test
    final void testDoDeleteCategoria() {
        CategoriaDAO categorie = new CategoriaDAO();
        int result = categorie.doDelete(categoria);
        int expResult = 0;
        assertEquals(expResult, result);
    }


}
