package DAO;


import Entities.Cliente;
import Entities.Indirizzo;
import Utils.DriverManagerConnectionPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


/**
 * @author Vincenzo Raia
 * @version 0.1
 * @since 27/01/2021
 */

public class IndirizzoDAO {

    private final String doDeleteQuery = "DELETE FROM Indirizzo WHERE id = ?";
    private final String doRetriveByIdQuery = "SELECT * FROM Indirizzo WHERE id = ?";
    private final String doRetriveByCliente = "SELECT * FROM Indirizzo WHERE username = ?";
    private final String doRetriveAllQuery = "SELECT * FROM Indirizzo";
    private final String doInsertQuery = "INSERT INTO Indirizzo(via,comune,provincia,cap,note,username)"
            + "VALUES(?,?,?,?,?,?);";
    private final String doUpdateQuery = "UPDATE Indirizzo SET via = ?, comune = ?, provincia = ?, cap = ?, note = ? WHERE id= ? ";

    protected int doUpdateByCliente(Cliente cliente) {
        Indirizzo indirizzo = cliente.getIndirizzo();
        try {
            Connection con = DriverManagerConnectionPool.getConnection();
            PreparedStatement prst = con.prepareStatement(doUpdateQuery);
            prst.setString(1, indirizzo.getVia());
            prst.setString(2, indirizzo.getComune());
            prst.setString(3, indirizzo.getProvincia());
            prst.setInt(4, indirizzo.getCap());
            prst.setString(5, indirizzo.getNotecorriere());
            prst.setInt(6, indirizzo.getId());
            try {
                prst.execute();
                con.commit();
                return 0;
            } catch (SQLException e) {
                e.printStackTrace();
                con.rollback();
                return -1;
            } finally {
                prst.close();
                DriverManagerConnectionPool.releaseConnection(con);
            }
        } catch (SQLException e) {
            return -1;
        }

    }

    protected int doInsertByCliente(Cliente cliente) {
        Indirizzo indirizzo = cliente.getIndirizzo();
        try {
            Connection con = DriverManagerConnectionPool.getConnection();
            String generatedColumns[] = {"ID"};
            PreparedStatement prst = con.prepareStatement(doInsertQuery, generatedColumns);
            prst.setString(1, indirizzo.getVia());
            prst.setString(2, indirizzo.getComune());
            prst.setString(3, indirizzo.getProvincia());
            prst.setInt(4, indirizzo.getCap());
            prst.setString(5, indirizzo.getNotecorriere());
            prst.setString(6, cliente.getUsername());
            try {
                prst.execute();
                con.commit();
                ResultSet rs = prst.getGeneratedKeys();
                if (rs.next()) {
                    indirizzo.setId(rs.getInt(1));
                }
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
            return -1;
        }
    }


    protected Indirizzo doRetriveByCliente(Cliente entity) {
        String username = entity.getUsername();
        try {
            Connection con = DriverManagerConnectionPool.getConnection();
            PreparedStatement prst = con.prepareStatement(doRetriveByIdQuery);
            prst.setString(1, username);

            try {
                ResultSet rs = prst.executeQuery();
                con.commit();
                Indirizzo indirizzo = null;
                if (rs.next()) {
                    indirizzo = new Indirizzo(rs.getInt("id"), rs.getString("via"), rs.getString("comune"), rs.getString("provincia"), rs.getInt("cap"), rs.getString("note"));
                }
                rs.close();
                return indirizzo;

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
}
