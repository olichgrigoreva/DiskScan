package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

    //параметры подключения к БД
    static final String JDBC_DRIVER = "org.postgresql.Driver";
    static final String DATABASE_URL = "jdbc:postgresql://localhost:port/files";
    static final String USER = "username";
    static final String PASSWORD = "password";

    /**
     * Подключение к БД
     * @return Connection connect
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public static Connection connect() throws ClassNotFoundException, SQLException {
        System.out.println("Registering JDBC driver...");
        Class.forName(JDBC_DRIVER);
        System.out.println("Creating database connection...");
        Connection connection = DriverManager.getConnection(DATABASE_URL, USER, PASSWORD);
        return connection;
    }

    /**
     * закрытие подключения с БД
     * @param connection параметр подключения
     */
    public static void closeConnect(Connection connection) {
        try {
            System.out.println("Closing connection and releasing resources...");
            connection.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}
