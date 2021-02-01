package Manager;

import DAO.CategoriaDAO;
import Entities.Categoria;

public class ManagerCategorie {

    CategoriaDAO categoriaDAO;

    public ManagerCategorie(){categoriaDAO = new CategoriaDAO();}

    public boolean aggiungiCategoria(String nome,String descrizione){
        Categoria
        if(categoriaDAO.doInsert())
    }
}
