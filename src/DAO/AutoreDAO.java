package DAO;

import Entities.Autore;
import Entities.Libro;
import Utils.DriverManagerConnectionPool;

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
 * @since 28/01/2021
 *
 */

public class AutoreDAO {

    private final String doRetrieveByLibroQuery = "SELECT a.* FROM Autore a, libroordinato lo, libro l WHERE a.id = libroordinato.id and l.isbn = lo.isbn and l.isbn = ?";
    private final String doInsertQueryInAutore = "INSERT INTO Autore(nome) VALUES(?);";
    private final String doInsertQueryInLibroAutore= "INSERT INTO LibroAutore(id,isbn) VALUES(?,?);";
    private final String doUpdateQueryInAutore = "UPDATE Autore SET nome = ? where id= ? ";


    
    protected List<Autore> doRetrieveByLibro (Libro libro){
        List<Autore> autori = new ArrayList<>();
        try {
            Connection con = DriverManagerConnectionPool.getConnection();

            PreparedStatement prst = con.prepareStatement(doRetrieveByLibroQuery);
            prst.setString(1, libro.getIsbn());

            try {
                ResultSet rs = prst.executeQuery();
                while (rs.next()) {
                    Autore autore = new Autore(rs.getInt("id"),rs.getString("nome"));
                    autori.add(autore);
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

        return autori;
    }

    protected int doInsertByLibro(Libro libro){
        List<Autore> autori = libro.getAutori();
        try {
            Connection con = DriverManagerConnectionPool.getConnection();
            String generatedColumns[] = { "ID" };
            try {
                for(Autore a: autori){
                    PreparedStatement prst = con.prepareStatement(doInsertQueryInAutore,generatedColumns);
                    prst.setString(1, a.getNome());
                    try{
                        prst.execute();
                        con.commit();
                        ResultSet rs = prst.getGeneratedKeys();
                        if (rs.next()) {
                            a.setId(rs.getInt("id"));
                        }

                        PreparedStatement prst2 = con.prepareStatement(doInsertQueryInLibroAutore);
                        prst2.setInt(1, a.getId());
                        prst2.setString(2, a.getNome());
                        prst2.execute();
                        con.commit();
                        prst2.close();

                    } catch(SQLException e){
                        con.rollback();
                        e.printStackTrace();
                        return -1;
                    } finally {
                        prst.close();
                        DriverManagerConnectionPool.releaseConnection(con);
                    }
                    prst.close();
                }
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

    protected int doUpdateByLibro(Libro libro){
        List<Autore> autori = libro.getAutori();
        try {
            Connection con = DriverManagerConnectionPool.getConnection();
            try {
                for(Autore a: autori){
                    PreparedStatement prst = con.prepareStatement(doUpdateQueryInAutore);
                    prst.setString(1, a.getNome());
                    prst.setInt(2, a.getId());
                    try{
                        prst.execute();
                        con.commit();

                    } catch(SQLException e){
                        con.rollback();
                        e.printStackTrace();
                        return -1;
                    } finally {
                        prst.close();
                        DriverManagerConnectionPool.releaseConnection(con);
                    }
                    prst.close();
                }
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
