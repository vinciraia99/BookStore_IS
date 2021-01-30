package DAO;

import Entities.ResponsabileCatalogo;
import Utils.DriverManagerConnectionPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Vincenzo Raia
 * @version 0.1
 * @since 28/01/2021
 */

public class ResponsabileCatalogoDAO extends DAO<ResponsabileCatalogo> {

    private final String doDeleteQuery = "DELETE FROM Account WHERE username = ? and tipo =\"R\"";
    private final String doRetriveByIdQuery = "SELECT * FROM Account WHERE username = ? and tipo =\"R\"";
    private final String doRetriveAllQuery = "SELECT * FROM Account where tipo =\"R\"";
    private final String doInsertQuery = "INSERT INTO Account(username,password,nome,cognome,email,tipo) VALUES(?,?,?,?,?,?);";
    private final String doUpdateQuery = "UPDATE Account SET nome = ?, cognome = ?, email = ?, password = ? WHERE username = ? and tipo =\"R\"";

    @Override
    public ResponsabileCatalogo doRetrieveById(Object... id) {
        String username = (String) id[0];
        try {
            Connection con = DriverManagerConnectionPool.getConnection();
            PreparedStatement prst = con.prepareStatement(doRetriveByIdQuery);
            prst.setString(1, username);

            try {
                ResultSet rs = prst.executeQuery();
                con.commit();
                ResponsabileCatalogo responsabileCatalogo = null;
                if (rs.next()) {
                    responsabileCatalogo = new ResponsabileCatalogo(rs.getString("email"), rs.getString("username"), rs.getString("password"), rs.getString("nome"), rs.getString("cognome"));
                }
                rs.close();
                return responsabileCatalogo;

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
    public List<ResponsabileCatalogo> doRetrieveAll() {
        List<ResponsabileCatalogo> responsabileCatalogolist = new ArrayList<>();

        try {
            Connection con = DriverManagerConnectionPool.getConnection();
            PreparedStatement prst = con.prepareStatement(doRetriveAllQuery);

            try {
                ResultSet rs = prst.executeQuery();
                while (rs.next()) {
                    ResponsabileCatalogo responsabileCatalogo = new ResponsabileCatalogo(rs.getString("email"), rs.getString("username"), rs.getString("password"), rs.getString("nome"), rs.getString("cognome"));
                    responsabileCatalogolist.add(responsabileCatalogo);
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

        return responsabileCatalogolist;
    }

    /**
     * Metodo che inserisce un responsabile catalogo nel Database
     *
     * @param responsabileCatalogo da inserire.
     * @return 0 se tutto ok altrimenti -1
     */
    @Override
    public int doInsert(ResponsabileCatalogo responsabileCatalogo) {
        try {
            Connection con = DriverManagerConnectionPool.getConnection();
            try {
                PreparedStatement prst = con.prepareStatement(doInsertQuery);
                prst.setString(1, responsabileCatalogo.getUsername());
                prst.setString(2, responsabileCatalogo.getPassword());
                prst.setString(3, responsabileCatalogo.getNome());
                prst.setString(4, responsabileCatalogo.getCognome());
                prst.setString(5, responsabileCatalogo.getEmail());
                prst.setString(6, responsabileCatalogo.getTipo());
                prst.execute();
                con.commit();
                prst.close();
                return 0;

            } catch (SQLException e) {
                con.rollback();
                e.printStackTrace();
                return -1;
            } finally {
                DriverManagerConnectionPool.releaseConnection(con);
            }

        } catch (SQLException e) {
            return -1;
        }
    }

    /**
     * Metodo che modificare un responsabile catalogo dal Databse
     *
     * @param responsabileCatalogo da modificare.
     * @return 0 se tutto ok altrimenti -1
     */
    @Override
    public int doUpdate(ResponsabileCatalogo responsabileCatalogo) {
        try {
            Connection con = DriverManagerConnectionPool.getConnection();
            try {

                PreparedStatement prst = con.prepareStatement(doUpdateQuery);
                prst.setString(1, responsabileCatalogo.getNome());
                prst.setString(2, responsabileCatalogo.getCognome());
                prst.setString(3, responsabileCatalogo.getEmail());
                prst.setString(4, responsabileCatalogo.getPassword());
                prst.setString(5, responsabileCatalogo.getUsername());

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

    /**
     * Metodo che eliminare un responsabile catalogo dal Database
     *
     * @param responsabileCatalogo da eliminare.
     * @return 0 se tutto ok altrimenti -1
     */
    @Override
    public int doDelete(ResponsabileCatalogo responsabileCatalogo) {
        try {
            Connection con = DriverManagerConnectionPool.getConnection();
            try {

                PreparedStatement prst = con.prepareStatement(doDeleteQuery);
                prst.setString(1, responsabileCatalogo.getUsername());

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
