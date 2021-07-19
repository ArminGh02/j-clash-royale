package util;

import model.Password;
import model.player.Person;

import java.sql.*;
import java.util.ArrayList;

/**
 * DBHandler class, handles queries and interactions with database
 *
 * @author Adibov & Armin Gh
 * @version 1.0
 */
public class DBHandler {
  private static Connection dbConnection;
  private static Statement dbStatement;

  /** initialize database */
  public static void initialize() {
    try {
      Class.forName("com.mysql.cj.jdbc.Driver");
      String url = "jdbc:mysql://localhost:3306/ClashRoyaleDB";
      String dbUsername = "root", password = "rootroot";
      dbConnection = DriverManager.getConnection(url, dbUsername, password);
      dbStatement = dbConnection.createStatement();
      if (dbStatement.execute("SHOW TABLES LIKE 'Persons'")) {
        ResultSet resultSet = dbStatement.getResultSet();
        if (resultSetRowCount(resultSet) == 0)
          makeTables();
      }
    } catch (ClassNotFoundException | SQLException e) {
      e.printStackTrace();
    }
  }

  /**
   * make tables
   */
  private static void makeTables() {
    try {
      dbStatement.execute(
          "CREATE TABLE Persons (\n"
              + "    id INT,\n"
              + "    username varchar(20),\n"
              + "    password varchar(100),\n"
              + "    level INT DEFAULT 1,\n"
              + "    points INT DEFAULT 0,\n"
              + "    deck varchar(30) DEFAULT NULL,\n"
              + "    PRIMARY KEY (id)\n"
              + ");");
    }
    catch (SQLException exception) {
      exception.printStackTrace();
    }
  }

  /**
   * count the number of rows of the given resultSet
   *
   * @param resultSet given result set
   * @return row count
   */
  private static int resultSetRowCount(ResultSet resultSet) {
    int count = 0;
    try {
      while (resultSet.next()) count++;
    } catch (SQLException exception) {
      exception.printStackTrace();
    }
    return count;
  }

  /**
   * return the row count of the given table
   *
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
   *
   * @param person given person
   * @return true, if the player exists
   */
  public static boolean doesPersonExists(Person person) {
    String username = person.getUsername(), password = person.getPassword().toString();
    try {
      if (dbStatement.execute(
          "" + "SELECT * FROM Persons " + "WHERE username='" + username + "';")) {
        ResultSet resultSet = dbStatement.getResultSet();
        return resultSetRowCount(resultSet) > 0;
      }
    } catch (SQLException exception) {
      exception.printStackTrace();
    }
    return false;
  }

  /**
   * check if a person with the given username exists in database
   *
   * @param username given username
   * @return boolean result
   */
  public static boolean doesPersonExists(String username) {
    return doesPersonExists(new Person(username, "123"));
  }

  /**
   * check if a person with the given username and password exists in database
   *
   * @param username given username
   * @param password given password
   * @return boolean result
   */
  public static boolean doesPersonExists(String username, String password) {
    Person person = getPerson(username);
    if (person == null) return false;
    return person.getPassword().toString().equals(Password.hashPassword(password));
  }

  /**
   * add the given person to the table
   *
   * @param person the given person
   */
  public static void addPerson(Person person) {
    String username = person.getUsername(), password = person.getPassword().toString();
    int level = person.getLevel();
    int points = person.getPoints();
    if (doesPersonExists(person)) return;
    int personCount = tableRowCount("Persons");
    try {
      dbStatement.execute(
          ""
              + "INSERT INTO Persons "
              + "VALUES ("
              + "'"
              + personCount
              + "', '"
              + username
              + "', '"
              + password
              + "', '"
              + level
              + "', '"
              + points
              + "');");
    } catch (SQLException exception) {
      exception.printStackTrace();
    }
  }

  /**
   * retrieve the person with the given username from database
   *
   * @param username the given username
   * @return result person
   */
  public static Person getPerson(String username) {
    if (!doesPersonExists(username)) return null;
    try {
      if (dbStatement.execute(
          "" + "SELECT * FROM Persons " + "WHERE username='" + username + "';")) {
        ResultSet resultSet = dbStatement.getResultSet();
        resultSet.next();
        Person person =
            new Person(resultSet.getString("username"), resultSet.getString("password"));
        person.setPassword(resultSet.getString("password"));
        person.setLevel(resultSet.getInt("level"));
        person.setPoints(resultSet.getInt("points"));
        return person;
      }
    } catch (SQLException exception) {
      exception.printStackTrace();
    }
    return null;
  }

  /**
   * get deck for the given person
   * @param person the given person
   * @return person's deck
   */
  public static ArrayList<Integer> getPersonsDeck(Person person) {
    String username = person.getUsername();
    if (!doesPersonExists(username))
      return null;
    try {
      if (dbStatement.execute("" + "SELECT * FROM Persons " + "WHERE username='" + username + "';")) {
        ResultSet resultSet = dbStatement.getResultSet();
        resultSet.next();
        String[] deck = resultSet.getString("deck").split(",");
        ArrayList<Integer> result = new ArrayList<>();
        for (String index : deck)
          result.add(Integer.parseInt(index));
        return result;
      }
    } catch (SQLException exception) {
      exception.printStackTrace();
    }
    return null;
  }

  /**
   * update deck for the given person
   * @param person the given person
   * @param deck person's deck
   */
  public static void updatePersonsDeck(Person person, ArrayList<Integer> deck) {
    StringBuilder deckSerialized = new StringBuilder();
    for (int i = 0; i < deck.size(); i++) {
      deckSerialized.append(deck.get(i).toString());
      if (i != deck.size() - 1)
        deckSerialized.append(',');
    }

    try {
      dbStatement.execute("UPDATE Persons SET deck = '" + deckSerialized + "' WHERE username = '" + person.getUsername() + "';");
    }
    catch (SQLException exception) {
      exception.printStackTrace();
    }
  }

  /**
   * update points of the given person
   * @param person the given person
   */
  public static void updatePersonsPoints(Person person) {
    String username = person.getUsername();
    int points = person.getPoints();
    try {
      dbStatement.execute("UPDATE Persons SET points = " + points + " WHERE username = '" + username + "';");
    }
    catch (SQLException exception) {
      exception.printStackTrace();
    }
  }
}
