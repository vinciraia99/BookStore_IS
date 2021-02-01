package DAO;

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

    private final String doRetriveByIdQuery = "SELECT * FROM Libro WHERE isbn = ?";
    private final String doRetriveAllQuery = "SELECT * FROM Libro";
    private final String doInsertQuery = "INSERT INTO Libro(isbn,prezzo,quantita,trama,titolo,copertina,disabilitato,datapubblicazione) VALUES(?,?,?,?,?,?,?,?);";
    private final String doUpdateQuery = "UPDATE Libro SET prezzo = ?, quantita = ?, trama = ? , titolo =?, copertina=? , disabilitato = ? , datapubblicazione = ? WHERE isbn = ?";
    private final String doDeleteQuery = "DELETE FROM Libro WHERE isbn = ?";
    private final String doRetrieveByNomeOrDescrizioneQuery = "SELECT * FROM libro WHERE MATCH(titolo, trama) AGAINST(?)";

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
                    libro = new Libro(rs.getString("isbn"), rs.getString("titolo"), rs.getDouble("quantita"), rs.getString("trama"), rs.getFloat("prezzo"), rs.getString("copertina"), dataPubblicazione, rs.getBoolean("disabilitato"));
                    libro.setAutori(autoreDAO.doRetrieveByLibro(libro));
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
                    libro = new Libro(rs.getString("isbn"), rs.getString("titolo"), rs.getDouble("quantita"), rs.getString("trama"), rs.getFloat("prezzo"), rs.getString("copertina"), dataPubblicazione, rs.getBoolean("disabilitato"));
                    libro.setAutori(autoreDAO.doRetrieveByLibro(libro));
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
                if (autoreDAO.doInsertByLibro(libro) == 0) {
                    return 0;
                } else {
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
                if (autoreDAO.doUpdateByLibro(libro) == 0) {
                    return 0;
                } else {
                    return -1;
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
                Libro libro = new Libro(rs.getString("isbn"), rs.getString("titolo"), rs.getDouble("quantita"), rs.getString("trama"), rs.getFloat("prezzo"), rs.getString("copertina"), dataPubblicazione, rs.getBoolean("disabilitato"));
                libro.setAutori(autoreDAO.doRetrieveByLibro(libro));
                libri.add(libro);
            }
            return libri;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
