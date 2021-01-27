package DAO;

import java.util.List;

/**
 * Superclasse astratta di tutte le DAO, obbliga
 * l'implementazione dei metodi CRUD.
 * @author Vincenzo Raia
 * @version 0.1
 * @since 27/01/2021
 * @param <T> L'entità per cui si sta creando la DAO
 */
public abstract class DAO<T> {

    /**
     *
     * @param id
     * @return
     */

    public abstract T doRetriveById( Object ... id );

    /**
     *
     * @return
     */
    public abstract List<T> doRetriveAll();

    /**
     *
     * @param entity
     * @return
     */
    public abstract int doInsert( T entity);

    /**
     *
     * @param entity
     * @return
     */
    public abstract int doUpdate(T entity);


}
