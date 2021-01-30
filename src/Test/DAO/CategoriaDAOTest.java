/**
 *
 */
package Test.DAO;

import DAO.CategoriaDAO;
import Entities.Categoria;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Raffaele Scarpa
 * @version 0.1
 * 29/01/2021
 */



class CategoriaDAOTest {


	private static Categoria categoria = new Categoria("Horror", "Libri horror");
	
	
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
        CategoriaDAO categorie = new CategoriaDAO();
        int result = categorie.doInsert(categoria);
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
