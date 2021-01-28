package DAO;


import Entities.LibroOrdinato;
import Entities.Ordine;
import Utils.DriverManagerConnectionPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LibroOrdinatoDAO {

    private final String doRetriveByOrderIdQuery = "SELECT * FROM LibroOrdinato WHERE idordine = ? ";
    private final String doInsertByOrdinerIdQuery = "INSERT INTO LibroOrdinato(quantita,prezzo,isbn,idordine) VALUES(?,?,?,?);";

    protected List<LibroOrdinato> doRetriveByOrderId(Ordine ordine) {
        List<LibroOrdinato> libriOrdinati = new ArrayList<>();

        try {
            Connection con = DriverManagerConnectionPool.getConnection();
            PreparedStatement prst = con.prepareStatement(doRetriveByOrderIdQuery);
            prst.setInt(1,ordine.getId());

            try {
                ResultSet rs = prst.executeQuery();
                while (rs.next()) {
                   LibroOrdinato libroOrdinato=  new LibroOrdinato(rs.getInt("id"),rs.getDouble("quantita"),rs.getFloat("prezzo"),rs.getString("isbn"));
                    libriOrdinati.add(libroOrdinato);
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

        return libriOrdinati;
    }


    protected int doInsertByOrderId(LibroOrdinato libroOrdinato , int idordine) {
        try {
            Connection con = DriverManagerConnectionPool.getConnection();
            try {
                PreparedStatement prst = con.prepareStatement(doInsertByOrdinerIdQuery);
                prst.setDouble(1, libroOrdinato.getQuantita());
                prst.setFloat(2, libroOrdinato.getPrezzo());
                prst.setString(3, libroOrdinato.getIsbn());
                prst.setInt(4, idordine);
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
