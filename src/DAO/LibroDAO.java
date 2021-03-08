package DAO;

import Entities.Categoria;
import Entities.Libro;
import Utils.DriverManagerConnectionPool;

import java.sql.*;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

/**
 * @author Vincenzo Raia
 * @version 0.1
 * @since 28/01/2021
 */


public class LibroDAO extends DAO<Libro> {

    private final String doRetriveByIdQuery = "SELECT * FROM Libro WHERE isbn = ? AND disabilitato = 0";
    private final String doRetriveAllQuery = "SELECT * FROM Libro WHERE disabilitato = 0";
    private final String doInsertQuery = "INSERT INTO Libro(isbn,prezzo,quantita,trama,titolo,copertina,disabilitato,datapubblicazione) VALUES(?,?,?,?,?,?,?,?);";
    private final String doUpdateQuery = "UPDATE Libro SET prezzo = ?, quantita = ?, trama = ? , titolo =?, copertina=? , disabilitato = ? , datapubblicazione = ? WHERE isbn = ?";
    private final String doDeleteQuery = "Update Libro SET disabilitato = 1 WHERE isbn = ?";
    private final String doRetrieveByNomeOrDescrizioneQuery = "SELECT * FROM libro WHERE MATCH(titolo, trama) AGAINST(?)";
    private final String doInsertByRelation = "Insert into librocategoria(isbn,id) values (?,?)";
    private final String doRetrieveByIdRelation = "SELECT c.* FROM Categoria c, Libro l, librocategoria lc WHERE c.id = lc.id and l.isbn = lc.isbn and l.isbn = ?";
    private final String doRemoveRelation = "Delete from librocategoria where isbn = ?";

    @Override
    public Libro doRetrieveById(Object... id) {
        String isbn = (String) id[0];
        try {
            Connection con = DriverManagerConnectionPool.getConnection();
            PreparedStatement prst = con.prepareStatement(doRetriveByIdQuery);
            prst.setString(1, isbn);

            try {
                ResultSet rs = prst.executeQuery();
                con.commit();
                Libro libro = null;
                AutoreDAO autoreDAO = new AutoreDAO();
                if (rs.next()) {
                    GregorianCalendar dataPubblicazione = new GregorianCalendar();
                    dataPubblicazione.setTimeInMillis(rs.getDate("datapubblicazione").getTime());
                    libro = new Libro(rs.getString("isbn"), rs.getString("titolo"), rs.getInt("quantita"), rs.getString("trama"), rs.getFloat("prezzo"), rs.getString("copertina"), dataPubblicazione, rs.getBoolean("disabilitato"));
                    libro.setAutori(autoreDAO.doRetrieveByLibro(libro));
                    libro.setCategorie(doRetriveByLibroRelation(libro));
                }
                rs.close();
                return libro;

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

    @Override
    public List<Libro> doRetrieveAll() {
        List<Libro> libri = new ArrayList<>();
        Libro libro = null;
        AutoreDAO autoreDAO = new AutoreDAO();
        try {
            Connection con = DriverManagerConnectionPool.getConnection();
            PreparedStatement prst = con.prepareStatement(doRetriveAllQuery);

            try {
                ResultSet rs = prst.executeQuery();
                while (rs.next()) {
                    GregorianCalendar dataPubblicazione = new GregorianCalendar();
                    dataPubblicazione.setTimeInMillis(rs.getDate("datapubblicazione").getTime());
                    libro = new Libro(rs.getString("isbn"), rs.getString("titolo"), rs.getInt("quantita"), rs.getString("trama"), rs.getFloat("prezzo"), rs.getString("copertina"), dataPubblicazione, rs.getBoolean("disabilitato"));
                    libro.setAutori(autoreDAO.doRetrieveByLibro(libro));
                    libro.setCategorie(doRetriveByLibroRelation(libro));
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

    private List<Categoria> doRetriveByLibroRelation(Libro libro){
        List<Categoria> categorie = new ArrayList<>();
        try {
            Connection con = DriverManagerConnectionPool.getConnection();
            PreparedStatement prst = con.prepareStatement(doRetrieveByIdRelation);
            prst.setString(1, libro.getIsbn());

            try {
                ResultSet rs = prst.executeQuery();
                con.commit();
                while (rs.next()) {
                    Categoria categoria = new Categoria(rs.getInt("id"), rs.getString("nome"), rs.getString("descrizione"));
                    categorie.add(categoria);
                }
                rs.close();
                return categorie;

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

    /**
     * Metodo che aggiunge un libro nel Database e crea la relazione
     * tra libro e autore.
     *
     * @param libro da aggiungere.
     * @return 0 se tutto ok altrimenti -1
     */
    @Override
    public int doInsert(Libro libro) {
        try {
            Connection con = DriverManagerConnectionPool.getConnection();
            try {
                PreparedStatement prst = con.prepareStatement(doInsertQuery);
                prst.setString(1, libro.getIsbn());
                prst.setFloat(2, libro.getPrezzo());
                prst.setDouble(3, libro.getQuantita());
                prst.setString(4, libro.getTrama());
                prst.setString(5, libro.getTitolo());
                prst.setString(6, libro.getCopertina());
                prst.setBoolean(7, libro.isDisabilitato());
                prst.setDate(8, new Date(libro.getData_pubblicazione().getTimeInMillis()));
                prst.execute();
                con.commit();
                prst.close();
                AutoreDAO autoreDAO = new AutoreDAO();
                CategoriaDAO categoriaDAO = new CategoriaDAO();
                if (autoreDAO.doInsertByLibro(libro) == 0) {
                    List<Categoria> categorie = libro.getCategorie();
                    if(categorie.size()>0){
                        for (Categoria c : categorie){
                            if(categoriaDAO.doRetrieveById(c.getId()) == null){
                                con.rollback();
                                return -1;
                            }
                        }
                        for (Categoria c : categorie){
                            if(doInsertRelationLibriCategoria(libro,c) == -1){
                                con.rollback();
                                return -1;
                            }
                        }
                        return 0;
                    }else {
                        con.rollback();
                        return -1;
                    }

                } else {
                    con.rollback();
                    return -1;
                }
            } catch (SQLException e) {
                e.printStackTrace();
                con.rollback();
                return -1;
            } finally {
                DriverManagerConnectionPool.releaseConnection(con);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        }
    }

    private int doInsertRelationLibriCategoria(Libro libro, Categoria categoria){
        int id = categoria.getId();
        try {
            Connection con = DriverManagerConnectionPool.getConnection();
            PreparedStatement prst = con.prepareStatement(doInsertByRelation);
            prst.setInt(2, categoria.getId());
            prst.setString(1,libro.getIsbn());
            try {
                prst.execute();
                con.commit();
                prst.close();
                return 0;

            } catch (SQLException e) {
                e.printStackTrace();
                con.rollback();
                return -1;
            } finally {
                prst.close();
                DriverManagerConnectionPool.releaseConnection(con);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
            return -1;
        }
    }

    /**
     * Metodo che modifica un libro nel Database
     *
     * @param libro da modificare.
     * @return 0 se tutto ok altrimenti -1
     */
    @Override
    public int doUpdate(Libro libro) {
        try {
            Connection con = DriverManagerConnectionPool.getConnection();
            try {
                PreparedStatement prst = con.prepareStatement(doUpdateQuery);
                prst.setFloat(1, libro.getPrezzo());
                prst.setDouble(2, libro.getQuantita());
                prst.setString(3, libro.getTrama());
                prst.setString(4, libro.getTitolo());
                prst.setString(5, libro.getCopertina());
                prst.setBoolean(6, libro.isDisabilitato());
                prst.setDate(7, new Date(libro.getData_pubblicazione().getTimeInMillis()));
                prst.setString(8, libro.getIsbn());
                prst.execute();
                con.commit();
                prst.close();
                AutoreDAO autoreDAO = new AutoreDAO();
                if (autoreDAO.doUpdateByLibro(libro) == -1) {
                    return -1;
                }
                if(doUpdateRelationCategorieLibri(libro) == -1){
                    return -1;
                }
                return 0;

            } catch (SQLException e) {
                e.printStackTrace();
                con.rollback();
                return -1;
            } finally {
                DriverManagerConnectionPool.releaseConnection(con);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        }
    }

    private int doUpdateRelationCategorieLibri(Libro libro){
        try {
            Connection con = DriverManagerConnectionPool.getConnection();
            try {
                PreparedStatement prst = con.prepareStatement(doRemoveRelation);
                prst.setString(1,libro.getIsbn());
                prst.execute();
                con.commit();
                prst.close();
            } catch (SQLException e) {
                e.printStackTrace();
                con.rollback();
                return -1;
            } finally {
                DriverManagerConnectionPool.releaseConnection(con);
            }

            try {
                for(Categoria c : libro.getCategorie()){
                    PreparedStatement prst2 = con.prepareStatement(doInsertByRelation);
                    prst2.setString(1,libro.getIsbn());
                    prst2.setInt(2,c.getId());
                    prst2.execute();
                    con.commit();
                    prst2.close();
                }

            } catch (SQLException e) {
                e.printStackTrace();
                con.rollback();
                return -1;
            } finally {
                DriverManagerConnectionPool.releaseConnection(con);
            }

            return 0;


        } catch (SQLException e) {
            return -1;
        }




    }

    /**
     * Metodo che elimina un libro nel Database
     *
     * @param libro da eliminare.
     * @return 0 se tutto ok altrimenti -1
     */
    @Override
    public int doDelete(Libro libro) {
        try {
            Connection con = DriverManagerConnectionPool.getConnection();
            try {

                PreparedStatement prst = con.prepareStatement(doDeleteQuery);
                prst.setString(1, libro.getIsbn());

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

    public List<Libro> doRetrieveByNomeOrDescrizione(String keyword) {
        try {
            Connection con = DriverManagerConnectionPool.getConnection();
            PreparedStatement ps = con.prepareStatement(doRetrieveByNomeOrDescrizioneQuery);
            ps.setString(1,keyword);
            List<Libro> libri = new ArrayList<>();
            ResultSet rs = ps.executeQuery();
            AutoreDAO autoreDAO = new AutoreDAO();
            while (rs.next()) {
                GregorianCalendar dataPubblicazione = new GregorianCalendar();
                dataPubblicazione.setTimeInMillis(rs.getDate("datapubblicazione").getTime());
                Libro libro = new Libro(rs.getString("isbn"), rs.getString("titolo"), rs.getInt("quantita"), rs.getString("trama"), rs.getFloat("prezzo"), rs.getString("copertina"), dataPubblicazione, rs.getBoolean("disabilitato"));
                libro.setAutori(autoreDAO.doRetrieveByLibro(libro));
                libro.setCategorie(doRetriveByLibroRelation(libro));
                libri.add(libro);
            }
            return libri;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}