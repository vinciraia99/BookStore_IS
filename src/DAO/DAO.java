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
     * @return object
     */

    public abstract T doRetriveById( Object ... id );

    /**
     *
     * @return list
     */
    public abstract List<T> doRetriveAll();

    /**
     *
     * @param entity
     * @return 0 se tutto ok altrimenti -1
     */
    public abstract int doInsert( T entity);

    /**
     *
     * @param entity
     * @return  0 se tutto ok altrimenti -1
     */
    public abstract int doUpdate(T entity);

    /**
     *
     * @param entity
     * @return 0 se tutto ok altrimenti -1
     */
    public abstract int doDelete(T entity);


}