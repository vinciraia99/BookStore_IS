/**
 *
 */
package Test.DAO;

import DAO.CategoriaDAO;
import Entities.Categoria;
import org.junit.jupiter.api.Test;

import java.util.List;

/**
 * @author Raffaele Scarpa
 * @version 0.1
 * 29/01/2021
 */
class CategoriaDAOTest {


    /**
     * Test DoRetrieveById
     * Versione: 0.1
     * Autore: Raffaele Scarpa
     */

    @Test
    final void testDoRetrieveById() {
        int id = 1;
        CategoriaDAO categoria = new CategoriaDAO();
        Categoria cercata = categoria.doRetrieveById(id);
    }

    /**
     * Test DoRetrieveAll
     * Versione: 0.1
     * Autore: Raffaele Scarpa
     */
    @Test
    final void testDoRetrieveAll() {
        CategoriaDAO categorie = new CategoriaDAO();
        List<Categoria> risultato = categorie.doRetrieveAll();
    }

    /**
     * Test DoInsertCategoria
     * Versione: 0.1
     * Autore: Raffaele Scarpa
     */
    @Test
    final void testDoInsertCategoria() {
        Categoria nuova = new Categoria("Horror", "Libri horror");
        CategoriaDAO categorie = new CategoriaDAO();
        categorie.doInsert(nuova);
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
        categorie.doUpdate(aggiorna);
    }

    /**
     * Test DoDeleteCategoria
     * Versione: 0.1
     * Autore: Raffaele Scarpa
     */
    @Test
    final void testDoDeleteCategoria() {
        Categoria vecchia = new Categoria(1, "Horror", "Libri horror");
        CategoriaDAO categorie = new CategoriaDAO();
        int risultato = categorie.doDelete(vecchia);
    }


}
