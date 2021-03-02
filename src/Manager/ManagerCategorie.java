package Manager;

import DAO.CategoriaDAO;
import Entities.Categoria;

import java.util.List;

public class ManagerCategorie {

    CategoriaDAO categoriaDAO;

    public ManagerCategorie(){categoriaDAO = new CategoriaDAO();}

    /**
     * Questo metodo permette di aggiungere una categoria all’interno del sistema
     * @param nome (String) nome della categoria
     * @param descrizione (String) descrizione della categoria
     * @return true se si è riusciti ad aggiungere la categoria, false altrimenti
     */
    public boolean aggiungiCategoria(String nome,String descrizione){
        Categoria categoria = new Categoria(nome,descrizione);
        return categoriaDAO.doInsert(categoria) != -1;
    }

    /**
     * Questo metodo permette di modificare una categoria all’interno del sistema
     * @param nome (String) nome della categoria
     * @param descrizione (String) descrizione della categoria
     * @param id (int) id della categoria
     * @return true se si è riusciti ad modificare la categoria, false altrimenti
     */
    public boolean modificaCategoria(String nome,String descrizione,int id){
        Categoria categoria = new Categoria(id,nome,descrizione);
        return categoriaDAO.doUpdate(categoria) != -1;
    }

    /**
     * Questo metodo permette di eliminare una categoria all’interno del sistema
     * @param id (int) id della categoria
     * @return true se si è riusciti ad eliminare la categoria, false altrimenti
     */
    public boolean eliminaCategoria(int id){
        Categoria categoria = categoriaDAO.doRetrieveById(id);
        if(categoria == null) return false;

        return categoriaDAO.doDelete(categoria) != -1;
    }


    /**
     * Questo metodo permette di ricevere le informazioni di una categoria presente del database
     * @param id (int) id della categoria
     * @return acquisisciCategoria (Categoria) che è la cetegoria richiesta se esiste, null altrimenti
     */
    public Categoria acquisisciCategoria(int id){
        Categoria categoria = categoriaDAO.doRetrieveById(id);
        return categoria;
    }

    /**
     * Questo metodo permette di ricevere tutte le informazioni di ogni categoria presente del database
     * @return acquisisciTutteLeCategorie (List<Categoria>) lista delle categorie se esiste, null altrimenti;
     */
    public List< Categoria > acquisisciTutteLeCategorie(){
        List<Categoria> categorie = categoriaDAO.doRetrieveAll();
        return categorie;
    }
}
