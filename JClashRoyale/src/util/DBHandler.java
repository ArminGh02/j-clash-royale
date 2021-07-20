package util;

import model.GameResult;
import model.Password;
import model.player.Person;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

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
      makeTables();
    } catch (ClassNotFoundException | SQLException e) {
      e.printStackTrace();
    }
  }

  /**
   * make tables
   */
  private static void makeTables() {
    try {
      dbStatement.execute("SHOW TABLES LIKE 'Persons'");
      if (resultSetRowCount(dbStatement.getResultSet()) == 0)
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

      dbStatement.execute("SHOW TABLES LIKE 'Battles'");
      if (resultSetRowCount(dbStatement.getResultSet()) == 0)
        dbStatement.execute(
                "CREATE TABLE Battles (\n" +
                "    id INT,\n" +
                "    firstPlayerUsername varchar(20),\n" +
                "    secondPlayerUsername varchar(20),\n" +
                "    firstPlayerCrownCount INT,\n" +
                "    secondPlayerCrownCount INT,\n" +
                "    PRIMARY KEY (id)\n" +
                ");");
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
      resultSet.close();
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
        boolean result = resultSetRowCount(resultSet) > 0;
        resultSet.close();
        return result;
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
              + "INSERT INTO Persons (id, username, password, level, points)"
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
        resultSet.close();
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
      if (dbStatement.execute("SELECT * FROM Persons " + "WHERE username='" + username + "';")) {
        ResultSet resultSet = dbStatement.getResultSet();
        resultSet.next();
        String deckAsString = resultSet.getString("deck");
        if (deckAsString == null)
          return null;
        String[] deck = deckAsString.split(",");
        ArrayList<Integer> result = new ArrayList<>();
        for (String index : deck)
          result.add(Integer.parseInt(index));
        resultSet.close();
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

  /**
   * update level for the given person
   * @param person the given person
   */
  public static void updatePersonsLevel(Person person) {
    String username = person.getUsername();
    int level = person.getLevel();
    try {
      dbStatement.execute("UPDATE Persons SET level = " + level + " WHERE username = '" + username + "';");
    }
    catch (SQLException exception) {
      exception.printStackTrace();
    }
  }

  /**
   * get the list of game result for the given person
   * @param person the given person
   * @return the result list
   */
  public static ArrayList<GameResult> getPersonsGameResults(Person person) {
    String username = person.getUsername();
    ArrayList<GameResult> resultList = new ArrayList<>();
    try {
      dbStatement.execute("SELECT * FROM Battles WHERE firstPlayerUsername = '" + username + "';");
      ResultSet resultSet = dbStatement.getResultSet();
      while (resultSet.next()) {
        GameResult newGameResult = new GameResult(resultSet.getInt("id"), resultSet.getString("firstPlayerUsername"), resultSet.getString("secondPlayerUsername"),
                resultSet.getInt("firstPlayerCrownCount"), resultSet.getInt("secondPlayerCrownCount"));
        resultList.add(newGameResult);
      }
      resultSet.close();
    }
    catch (SQLException exception) {
      exception.printStackTrace();
    }
    return resultList;
  }

  /**
   * add the given game result to the table
   * @param gameResult the given game result
   */
  public static void addGameResult(GameResult gameResult) {
    try {
      dbStatement.execute(
          "INSERT INTO Battles (id, firstPlayerUsername, secondPlayerUsername, firstPlayerCrownCount, secondPlayerCrownCount) "
              + "VALUES("
              + gameResult.getId()
              + ", '"
              + gameResult.getFirstPlayerUsername()
              + "', '"
              + gameResult.getSecondPlayerUsername()
              + "', "
              + gameResult.getFirstPlayerCrownCount()
              + ", "
              + gameResult.getSecondPlayerCrownCount()
              + ");");
    }
    catch (SQLException exception) {
      exception.printStackTrace();
    }
  }
}
