package DAO;

import Entities.Cliente;
import Utils.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Vincenzo Raia
 * @version 0.1
 * @since 27/01/2021
 *
 */

public class ClienteDAO extends DAO<Cliente> {

    private final String doDeleteQuery = "DELETE FROM Account WHERE username = ?";
    private final String doRetriveByIdQuery = "SELECT * FROM Account WHERE username = ? and tipo =\"C\"";
    private final String doRetriveAllQuery = "SELECT * FROM Account WHERE tipo =\"C\"";
    private final String doInsertQuery = "INSERT INTO Account(username,password,nome,cognome,email,tipo) VALUES(?,?,?,?,?,?);";
    private final String doUpdateQuery = "UPDATE Account SET nome = ?, cognome = ?, email = ?, password = ? WHERE username = ? and tipo =\"C\"";
    private final String doUpdateEmail = "UPDATE Account SET email = ? WHERE username = ? and tipo =\"C\"";
    private final String doUpdatePassword = "UPDATE Account SET password = ? WHERE username = ? and tipo =\"C\"";

    @Override
    public Cliente doRetrieveById(Object... id) {
        String username = (String) id[0];
        try {
            Connection con = DriverManagerConnectionPool.getConnection();
            PreparedStatement prst = con.prepareStatement(doRetriveByIdQuery);
            prst.setString(1, username);

            try {
                ResultSet rs = prst.executeQuery();
                con.commit();
                Cliente cliente = null;
                IndirizzoDAO indirizzoDAO = new IndirizzoDAO();
                if (rs.next()) {
                    cliente = new Cliente(rs.getString("email"), rs.getString("username"), rs.getString("password"), rs.getString("nome"), rs.getString("cognome"));
                    cliente.setIndirizzo(indirizzoDAO.doRetriveByCliente(cliente));
                }
                rs.close();
                return cliente;

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
    public List<Cliente> doRetrieveAll() {
        List<Cliente> clienti = new ArrayList<>();

        try {
            Connection con = DriverManagerConnectionPool.getConnection();
            PreparedStatement prst = con.prepareStatement(doRetriveAllQuery);

            try {
                ResultSet rs = prst.executeQuery();
                IndirizzoDAO indirizzoDAO = new IndirizzoDAO();
                while (rs.next()) {
                    Cliente cliente = new Cliente(rs.getString("email"), rs.getString("username"), rs.getString("password"), rs.getString("nome"), rs.getString("cognome"));
                    cliente.setIndirizzo(indirizzoDAO.doRetriveByCliente(cliente));
                    clienti.add(cliente);
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

        return clienti;
    }

    /**
     * Metodo che inserisce un libro nel Database e crea la relazione
     * tra cliente e indirizzo.
     *
     * @param cliente da inserire.
     * @return 0 se tutto ok altrimenti -1
     */
    @Override
    public int doInsert(Cliente cliente) {
        try {
            Connection con = DriverManagerConnectionPool.getConnection();
            try {
                PreparedStatement prst = con.prepareStatement(doInsertQuery);
                prst.setString(1, cliente.getUsername());
                prst.setString(2, cliente.getPassword());
                prst.setString(3, cliente.getNome());
                prst.setString(4, cliente.getCognome());
                prst.setString(5, cliente.getEmail());
                prst.setString(6, cliente.getTipo());
                prst.execute();
                con.commit();
                prst.close();
                IndirizzoDAO indirizzoDAO = new IndirizzoDAO();
                if (indirizzoDAO.doInsertByCliente(cliente) == 0) {
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
     * Metodo che aggiorna un libro nel Database e crea la relazione
     * tra cliente e indirizzo.
     *
     * @param cliente da modificare.
     * @return 0 se tutto ok altrimenti -1
     */
    @Override
    public int doUpdate(Cliente cliente) {
        try {
            Connection con = DriverManagerConnectionPool.getConnection();
            try {

                PreparedStatement prst = con.prepareStatement(doUpdateQuery);
                prst.setString(1, cliente.getNome());
                prst.setString(2, cliente.getCognome());
                prst.setString(3, cliente.getEmail());
                prst.setString(4, cliente.getPassword());
                prst.setString(5, cliente.getUsername());

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
     * Metodo che elimina un cliente nel Database
     *
     * @param cliente da modificare.
     * @return 0 se tutto ok altrimenti -1
     */
    public int doDelete(Cliente cliente){
        try {
            Connection con = DriverManagerConnectionPool.getConnection();
            try {

                PreparedStatement prst = con.prepareStatement(doDeleteQuery);
                prst.setString(1, cliente.getUsername());

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
