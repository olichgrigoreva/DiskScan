package services;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
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

    public static void createDB(String dbName, Connection connection) {
        try (Statement statement = connection.createStatement()) {
            String sql = "CREATE DATABASE " + dbName;
            System.out.printf("Creating database '%s'...\n", dbName);
            statement.executeUpdate(sql);
            System.out.printf("Database created successfully '%s'\n", dbName);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

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

            //ResultSet resultSet = statement.executeQuery(query);
            System.out.println("Retrieving data from database...");
            printResultSet(resultSet);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public static void printResultSet(ResultSet resultSet) throws SQLException {
        try (FileOutputStream out = new FileOutputStream("queryResults.txt");
             BufferedOutputStream bos = new BufferedOutputStream(out)) {
            while (resultSet.next()) {
                ResultSetMetaData rsmd = resultSet.getMetaData();
                int columnsNumber = rsmd.getColumnCount();
                while (resultSet.next()) {
                    for (int i = 1; i <= columnsNumber; i++) {
                        if (i > 1) System.out.print(",  ");
                        String columnValue = resultSet.getString(i);
                        System.out.print(columnValue + " " + rsmd.getColumnName(i));
                    }
                    System.out.println("");
                }
//                String type = resultSet.getString("type");
//                String minimal = resultSet.getString("minimal");
//                String maximum = resultSet.getString("maximum");
//                String sum = resultSet.getString("sum");
//                String average = resultSet.getString("average");
//
//                bos.write(("----------------------------\n").getBytes());
//                bos.write(("Type: " + type + "\n").getBytes());
//                bos.write(("Minimal: " + minimal + "\n").getBytes());
//                bos.write(("Maximum: " + maximum + "\n").getBytes());
//                bos.write(("Sum: " + sum + "\n").getBytes());
//                bos.write(("Average: " + average + "\n").getBytes());
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        resultSet.close();
    }
}
