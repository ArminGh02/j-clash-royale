package util;

import model.Person;
import model.Player;

import java.io.PrintWriter;
import java.sql.*;

/**
 * DBHandler class, handles queries and interactions with database
 * @author Adibov & Armin Gh
 * @version 1.0
 */
public class DBHandler {
    private static Connection dbConnection;
    private static Statement dbStatement;

    /**
     * initialize database
     */
    public static void initialize() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/ClashRoyaleDB";
            String dbUsername = "root", password = "rootroot";
            dbConnection = DriverManager.getConnection(url, dbUsername, password);
            dbStatement = dbConnection.createStatement();
        }
        catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * count the number of rows of the given resultSet
     * @param resultSet given result set
     * @return row count
     */
    private static int resultSetRowCount(ResultSet resultSet) {
        int count = 0;
        try {
            while (resultSet.next())
                count++;
        }
        catch (SQLException exception) {
            exception.printStackTrace();
        }
        return count;
    }

    /**
     * return the row count of the given table
     * @param tableName given table name
     * @return row count
     */
    public static int tableRowCount(String tableName) {
        try {
            if (dbStatement.execute("SELECT * FROM " + tableName + ";")) {
                ResultSet resultSet = dbStatement.getResultSet();
                return resultSetRowCount(resultSet);
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return 0;
    }

    /**
     * check if the given person exists in the table or not
     * @param person given person
     * @return true, if the player exists
     */
    public static boolean doesPersonExists(Person person) {
        String username = person.getUsername(), password = person.getPassword().toString();
        try {
            if (dbStatement.execute("" +
                    "SELECT * FROM Persons " +
                    "WHERE username='" + username + "';")) {
                ResultSet resultSet = dbStatement.getResultSet();
                return resultSetRowCount(resultSet) > 0;
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return false;
    }

    /**
     * add the given person to the table
     * @param person the given person
     */
    public static void addPerson(Person person) {
        String username = person.getUsername(), password = person.getPassword().toString();
        if (doesPersonExists(person))
            return;
        int personCount = tableRowCount("Persons");
        try {
            dbStatement.execute("" +
                    "INSERT INTO Persons " +
                    "VALUES (" + "'" + personCount + "', '" + username + "', '" + password + "');");
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }
}
