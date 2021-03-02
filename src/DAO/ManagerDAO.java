package DAO;


import Entities.Manager;
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
 * @since 27/01/2021
 */

public class ManagerDAO extends DAO<Manager> {

    private final String doDeleteQuery = "DELETE FROM Account WHERE username = ? and tipo =`M`";
    private final String doRetriveByIdQuery = "SELECT * FROM Account WHERE username = ? and tipo =\"M\"";
    private final String doRetriveAllQuery = "SELECT * FROM Account WHERE tipo =\"M\"";
    private final String doInsertQuery = "INSERT INTO Account(username,password,nome,cognome,email,tipo,abilitato) VALUES(?,?,?,?,?,?,1);";
    private final String doUpdateQuery = "UPDATE Account SET nome = ?, cognome = ?, email = ?, password = ? WHERE username = ? and tipo =\"M\"";

    @Override
    public Manager doRetrieveById(Object... id) {
        String username = (String) id[0];
        try {
            Connection con = DriverManagerConnectionPool.getConnection();
            PreparedStatement prst = con.prepareStatement(doRetriveByIdQuery);
            prst.setString(1, username);

            try {
                ResultSet rs = prst.executeQuery();
                con.commit();
                Manager manager = null;
                if (rs.next()) {
                    manager = new Manager(rs.getString("email"), rs.getString("username"), rs.getString("password"), rs.getString("nome"), rs.getString("cognome"));
                }
                rs.close();
                return manager;

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
    public List<Manager> doRetrieveAll() {
        List<Manager> managerList = new ArrayList<>();

        try {
            Connection con = DriverManagerConnectionPool.getConnection();
            PreparedStatement prst = con.prepareStatement(doRetriveAllQuery);

            try {
                ResultSet rs = prst.executeQuery();
                while (rs.next()) {
                    Manager manager = new Manager(rs.getString("email"), rs.getString("username"), rs.getString("password"), rs.getString("nome"), rs.getString("cognome"));
                    managerList.add(manager);
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

        return managerList;
    }

    /**
     * Metodo che inserisce un manager nel Database
     *
     * @param manager da inserire.
     * @return 0 se tutto ok altrimenti -1
     */
    @Override
    public int doInsert(Manager manager) {
        try {
            Connection con = DriverManagerConnectionPool.getConnection();
            try {
                PreparedStatement prst = con.prepareStatement(doInsertQuery);
                prst.setString(1, manager.getUsername());
                prst.setString(2, manager.getPassword());
                prst.setString(3, manager.getNome());
                prst.setString(4, manager.getCognome());
                prst.setString(5, manager.getEmail());
                prst.setString(6, manager.getTipo());
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
     * Metodo che modificare un Manager dal Databse
     *
     * @param manager da modificare.
     * @return 0 se tutto ok altrimenti -1
     */
    @Override
    public int doUpdate(Manager manager) {
        try {
            Connection con = DriverManagerConnectionPool.getConnection();
            try {

                PreparedStatement prst = con.prepareStatement(doUpdateQuery);
                prst.setString(1, manager.getNome());
                prst.setString(2, manager.getCognome());
                prst.setString(3, manager.getEmail());
                prst.setString(4, manager.getPassword());
                prst.setString(5, manager.getUsername());

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
     * Metodo che eliminare un manager dal Database
     *
     * @param manager da eliminare.
     * @return 0 se tutto ok altrimenti -1
     */
    @Override
    public int doDelete(Manager manager) {
        try {
            Connection con = DriverManagerConnectionPool.getConnection();
            try {

                PreparedStatement prst = con.prepareStatement(doDeleteQuery);
                prst.setString(1, manager.getUsername());

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
