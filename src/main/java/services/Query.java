package services;

import java.sql.*;

public class Query {
    static String query11 = "SELECT type,min(size) AS minimal, max(size) AS maximum, sum(size) AS sum, avg(size) AS average FROM doc GROUP BY type";
    static String query12 = "SELECT type, min(created) AS minimal, max(created) AS maximum FROM doc GROUP BY type";
    static String query13 = "SELECT type, count FROM (SELECT type, count(type) FROM doc GROUP BY type) count WHERE count = 1 GROUP BY type, count";
    static String query14 = "FROM (SELECT type FROM doc WHERE size BETWEEN ((SELECT max(size) FROM doc) * 0.9) AND (SELECT max(size) FROM doc) GROUP BY type) result GROUP BY type";

    static String query21 = "SELECT system, hidden, min(size) AS minimal, max(size) AS maximum, sum(size) AS sum, avg(size) AS average FROM doc GROUP BY system, hidden";
    static String query22 = "SELECT system, hidden, min(created) AS minimal, max(created) AS maximum FROM doc GROUP BY system, hidden";
    static String query23 = "SELECT system, hidden, type, count FROM (SELECT system, hidden, type, count(type) FROM doc GROUP BY system, hidden, type) count WHERE count = 1 GROUP BY system, hidden, type, count";
    static String query24 = "SELECT system, hidden, type, size, count(result) FROM (SELECT system, hidden, size, type FROM doc WHERE size BETWEEN ((SELECT max(size) FROM doc) * 0.9) AND (SELECT max(size) FROM doc) GROUP BY system, hidden, size, type) result GROUP BY system, hidden, size, type";

    /**
     * выполнение запроса (одного из указанных в полях класса)
     * @param connection подключение к БД
     * @param queryCase одна из строк с запросом
     */
    public static void createStatement(Connection connection, int queryCase) {
        try (Statement statement = connection.createStatement()) {
            System.out.println("Executing statement...");
            ResultSet resultSet = null;
            switch (queryCase) {
                case 11:
                    resultSet = statement.executeQuery(query11);
                    break;
                case 12:
                    resultSet = statement.executeQuery(query12);
                    break;
                case 13:
                    resultSet = statement.executeQuery(query13);
                    break;
                case 14:
                    resultSet = statement.executeQuery(query14);
                    break;
                case 21:
                    resultSet = statement.executeQuery(query21);
                    break;
                case 22:
                    resultSet = statement.executeQuery(query22);
                    break;
                case 23:
                    resultSet = statement.executeQuery(query23);
                    break;
                case 24:
                    resultSet = statement.executeQuery(query24);
                    break;
                default:
                    System.out.println("Doesn't have result set");
            }

            System.out.println("Retrieving data from database...");
            printResultSet(resultSet);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public static void printResultSet(ResultSet resultSet) throws SQLException {
            ResultSetMetaData rsmd = resultSet.getMetaData();
            int columnsNumber = rsmd.getColumnCount();
            for (int i = 1; i <= columnsNumber; i++) {
                if (i > 1) System.out.print(",  ");
                System.out.print(rsmd.getColumnName(i));
            }
            System.out.println();
            while (resultSet.next()) {
                for (int i = 1; i <= columnsNumber; i++) {
                    if (i > 1) System.out.print(",  ");
                    System.out.print(resultSet.getString(i));
                }
                System.out.println("");
            }
        resultSet.close();
    }
}
