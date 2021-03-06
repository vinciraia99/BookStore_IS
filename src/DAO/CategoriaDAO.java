package DAO;

import Entities.Categoria;
import Entities.Libro;
import Utils.DriverManagerConnectionPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

/**
 * @author Vincenzo Raia
 * @version 0.1
 * @since 28/01/2021
 */


public class CategoriaDAO extends DAO<Categoria> {

    private final String doRetriveAllQuery = "SELECT * FROM Categoria";
    private final String doRetriveAllLibroQuery = "SELECT l.* FROM categoria c, librocategoria lc, libro l where c.id=lc.id and l.isbn = lc.isbn and c.id = ? "; // query sql che permette la ricerca nell'associazione Libro Categoria
    private final String doInsertQuery = "INSERT INTO Categoria(nome,descrizione) VALUES(?,?);";
    private final String doInsertInRelationQuery = "INSERT INTO librocategoria(isbn,id) VALUES(?,?);";
    private final String doUpdateQuery = "UPDATE Categoria SET nome = ?, descrizione = ? WHERE id = ?";
    private final String doDeleteQuery = "DELETE FROM Categoria WHERE id = ?";
    private final String doRetrieveByIdQuery = "SELECT * FROM Categoria WHERE id = ?";
    private final String doRetrieveByIdRelation = "SELECT l.* FROM Categoria c, Libro l, librocategoria lc WHERE c.id = lc.id and l.isbn = lc.isbn and c.id = ? and l.disabilitato =0";

    @Override
    public Categoria doRetrieveById(Object... ids) {
        int id = (int) ids[0];
        try {
            Connection con = DriverManagerConnectionPool.getConnection();
            PreparedStatement prst = con.prepareStatement(doRetrieveByIdQuery);
            prst.setInt(1, id);

            try {
                ResultSet rs = prst.executeQuery();
                con.commit();
                Categoria categoria = null;
                if (rs.next()) {
                    categoria = new Categoria(rs.getInt("id"), rs.getString("nome"), rs.getString("descrizione"));
                    categoria.setLibri(doRetrieveByIdRelation(categoria));
                }
                rs.close();
                return categoria;

            } catch (SQLException e) {
                con.rollback();
                e.printStackTrace();
                return null;
            } finally {
                prst.close();
                DriverManagerConnectionPool.releaseConnection(con);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    private List<Libro> doRetrieveByIdRelation(Categoria categoria) {
        List<Libro> libri = new ArrayList<>();
        int id = categoria.getId();
        try {
            Connection con = DriverManagerConnectionPool.getConnection();
            PreparedStatement prst = con.prepareStatement(doRetrieveByIdRelation);
            prst.setInt(1, id);

            try {
                ResultSet rs = prst.executeQuery();
                LibroDAO libroDAO = new LibroDAO();
                while (rs.next()) {
                    GregorianCalendar dataPubblicazione = new GregorianCalendar();
                    dataPubblicazione.setTimeInMillis(rs.getDate("datapubblicazione").getTime());
                    Libro libro = new Libro(rs.getString("isbn"), rs.getString("titolo"), rs.getInt("quantita"), rs.getString("trama"), rs.getFloat("prezzo"), rs.getString("copertina"), dataPubblicazione, rs.getBoolean("disabilitato"));
                    libri.add(libro);
                }

                rs.close();

            } catch (SQLException e) {
                e.printStackTrace();
                con.rollback();
            } finally {
                prst.close();
                DriverManagerConnectionPool.releaseConnection(con);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return libri;

    }

    @Override
    public List<Categoria> doRetrieveAll() {
        List<Categoria> categorie = new ArrayList<>();

        try {
            Connection con = DriverManagerConnectionPool.getConnection();
            PreparedStatement prst = con.prepareStatement(doRetriveAllQuery);

            try {
                ResultSet rs = prst.executeQuery();
                LibroDAO libroDAO = new LibroDAO();
                while (rs.next()) {
                    Categoria categoria = new Categoria(rs.getInt("id"), rs.getString("nome"), rs.getString("descrizione"));
                    categoria.setLibri(libroDAO.doRetrieveAll());
                    categoria.setLibri(doRetrieveAllLibri(categoria));
                    categorie.add(categoria);

                }

                rs.close();

            } catch (SQLException e) {
                e.printStackTrace();
                con.rollback();
            } finally {
                prst.close();
                DriverManagerConnectionPool.releaseConnection(con);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return categorie;
    }

    private List<Libro> doRetrieveAllLibri(Categoria categoria) {
        List<Libro> libri = new ArrayList<>();
        Libro libro = null;
        AutoreDAO autoreDAO = new AutoreDAO();
        try {
            Connection con = DriverManagerConnectionPool.getConnection();
            PreparedStatement prst = con.prepareStatement(doRetriveAllLibroQuery);
            prst.setInt(1, categoria.getId());

            try {
                LibroDAO libroDAO = new LibroDAO();
                ResultSet rs = prst.executeQuery();
                while (rs.next()) {
                    libro = libroDAO.doRetrieveById(rs.getString("isbn"));
                    libri.add(libro);
                }

                rs.close();

            } catch (SQLException e) {
                e.printStackTrace();
                con.rollback();
            } finally {
                prst.close();
                DriverManagerConnectionPool.releaseConnection(con);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return libri;
    }

    /**
     * Metodo che inserisce una categoria nel Database
     *
     * @param categoria da inserire.
     * @return 0 se tutto ok altrimenti -1
     */
    @Override
    public int doInsert(Categoria categoria) {
        try {
            Connection con = DriverManagerConnectionPool.getConnection();
            try {
                PreparedStatement prst = con.prepareStatement(doInsertQuery);
                prst.setString(1, categoria.getNome());
                prst.setString(2, categoria.getDescrizione());
                try {
                    prst.execute();
                    con.commit();
                    return 0;
                } catch (SQLException e) {
                    con.rollback();
                    e.printStackTrace();
                    return -1;
                } finally {
                    prst.close();
                    DriverManagerConnectionPool.releaseConnection(con);
                }


            } catch (SQLException e) {
                con.rollback();
                return -1;
            } finally {
                DriverManagerConnectionPool.releaseConnection(con);
            }

        } catch (SQLException e) {
            return -1;
        }
    }


    /**
     * Metodo che aggiorna una categoria nel Database
     *
     * @param categoria da modificare.
     * @return 0 se tutto ok altrimenti -1
     */
    @Override
    public int doUpdate(Categoria categoria) {
        try {
            Connection con = DriverManagerConnectionPool.getConnection();
            try {
                PreparedStatement prst = con.prepareStatement(doUpdateQuery);
                prst.setString(1, categoria.getNome());
                prst.setString(2, categoria.getDescrizione());
                prst.setInt(3, categoria.getId());
                try {
                    prst.execute();
                    con.commit();
                    return 0;
                } catch (SQLException e) {
                    con.rollback();
                    e.printStackTrace();
                    return -1;
                } finally {
                    prst.close();
                    DriverManagerConnectionPool.releaseConnection(con);
                }
            } catch (SQLException e) {
                con.rollback();
                return -1;
            } finally {
                DriverManagerConnectionPool.releaseConnection(con);
            }

        } catch (SQLException e) {
            return -1;
        }
    }

    /**
     * Metodo che elimina una categoria dal Database
     *
     * @param categoria da modificare.
     * @return 0 se tutto ok altrimenti -1
     */
    @Override
    public int doDelete(Categoria categoria) {
        try {
            Connection con = DriverManagerConnectionPool.getConnection();
            try {

                PreparedStatement prst = con.prepareStatement(doDeleteQuery);
                prst.setInt(1, categoria.getId());

                prst.execute();
                con.commit();
                prst.close();
                return 0;

            } catch (SQLException e) {
                con.rollback();
                return -1;
            } finally {
                DriverManagerConnectionPool.releaseConnection(con);
            }

        } catch (SQLException e) {
            return -1;
        }
    }
}

