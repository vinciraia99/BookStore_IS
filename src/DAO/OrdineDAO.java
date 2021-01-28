package DAO;

import Entities.Libro;
import Entities.LibroOrdinato;
import Entities.Ordine;
import Utils.DriverManagerConnectionPool;

import java.sql.*;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

/**
 *
 * @author Vincenzo Raia
 * @version 0.1
 * @since 28/01/2021
 *
 */


public class OrdineDAO {

    private final String doInsertQuery = "INSERT INTO Ordine(quantita,totale,username,datadiacqusto) VALUES(?,?,?,?);";
    private final String doRetriveByClientId= "SELECT * FROM Ordine where username = ?";
    private final String doUpdateOrderState= "UPDATE Ordine SET spedito = true WHERE id = ?";


    public Ordine doRetriveByIdClinte(String username) {
        try {
            Connection con = DriverManagerConnectionPool.getConnection();
            PreparedStatement prst = con.prepareStatement(doRetriveByClientId);
            prst.setString(1, username);

            try {
                ResultSet rs = prst.executeQuery();
                con.commit();
                Ordine ordine = null;
                LibroOrdinatoDAO libroOrdinatoDAO = new LibroOrdinatoDAO();
                if (rs.next()) {
                    GregorianCalendar datadiacquisto = new GregorianCalendar();
                    datadiacquisto.setTimeInMillis(rs.getDate("datadiacquisto").getTime());
                    ordine = new Ordine(rs.getDouble("quantita"),rs.getFloat("totale"),datadiacquisto,rs.getInt("id"),username);
                    ordine.setLibriOrdinati((ArrayList<LibroOrdinato>) libroOrdinatoDAO.doRetriveByOrderId(ordine));
                }
                rs.close();
                return ordine;

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


    public int doInsert(Ordine ordine) {
        LibroOrdinatoDAO libroOrdinatoDAO = new LibroOrdinatoDAO();
        try {
            Connection con = DriverManagerConnectionPool.getConnection();
            String generatedColumns[] = { "ID" };
            try {
                PreparedStatement prst = con.prepareStatement(doInsertQuery,generatedColumns);
                prst.setDouble(1, ordine.getQuantita());
                prst.setFloat(2, ordine.getTotale());
                prst.setString(3, ordine.getUsername());
                prst.setDate(4, new Date(ordine.getDataDiAcquisto().getTimeInMillis()));
                try{
                    prst.execute();
                    con.commit();
                    ResultSet rs = prst.getGeneratedKeys();
                    if (rs.next()) {
                        for (LibroOrdinato l : ordine.getLibriOrdinati()){
                            if(libroOrdinatoDAO.doInsertByOrderId(l,ordine.getId()) == -1){
                                return -1;
                            }
                        }
                    }
                    return 0;
                } catch(SQLException e){
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

    public int doUpdateOrderState(Ordine ordine){
        try {
            Connection con = DriverManagerConnectionPool.getConnection();
            try {
                PreparedStatement prst = con.prepareStatement(doUpdateOrderState);
                prst.setDouble(1, ordine.getId());
                try{
                    prst.execute();
                    con.commit();
                    return 0;
                } catch(SQLException e){
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


}
