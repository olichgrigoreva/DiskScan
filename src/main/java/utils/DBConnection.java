package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    static final String JDBC_DRIVER = "org.postgresql.Driver";
    static final String DATABASE_URL = "jdbc:postgresql://localhost:5433/files";
    static final String USER = "postgres";
    static final String PASSWORD = "12345";

    public static Connection connect() throws ClassNotFoundException, SQLException {
        System.out.println("Registering JDBC driver...");
        Class.forName(JDBC_DRIVER);
        System.out.println("Creating database connection...");
        Connection connection = DriverManager.getConnection(DATABASE_URL, USER, PASSWORD);
        return connection;
    }

    public static void closeConnect(Connection connection) {
        try {
            System.out.println("Closing connection and releasing resources...");
            connection.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}
