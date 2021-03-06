package Utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.TimeZone;

/**
 * @author Vincenzo Raia
 * @version 0.1
 * @since 27/01/2021
 */

public class DriverManagerConnectionPool {

    private static List<Connection> freeDbConnections;

    static {
        freeDbConnections = new LinkedList<>();

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.err.println("Driver not found!");
        }
    }

    private static Connection createDBConnection() throws SQLException {
        Connection newConnection = null;
        String db = "bookstoredb";
        String username = "root";
        String password = "password";

        newConnection = DriverManager.getConnection("jdbc:mysql://localhost:3306/" + db + "?characterEncoding=UTF8&useUnicode=yes&serverTimezone=" + TimeZone.getDefault().getID(), username, password);
        newConnection.setAutoCommit(false);
        return newConnection;
    }

    public static synchronized Connection getConnection() throws SQLException {
        Connection connection;
        if (!freeDbConnections.isEmpty()) {
            connection = freeDbConnections.get(0);
            DriverManagerConnectionPool.freeDbConnections.remove(0);
            try {
                if (connection.isClosed()) {
                    connection = DriverManagerConnectionPool.getConnection();
                }
            } catch (SQLException e) {
                connection = DriverManagerConnectionPool.getConnection();
            }
        } else {
            connection = DriverManagerConnectionPool.createDBConnection();
        }


        return connection;
    }

    public static synchronized void releaseConnection(Connection connection) {
        DriverManagerConnectionPool.freeDbConnections.add(connection);
    }

}
