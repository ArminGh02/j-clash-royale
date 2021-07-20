package model;

import util.DBHandler;

/**
 * GameResult class, saves game results
 * @author Adibov & Armin Gh
 * @version 1.0
 */
public class GameResult {
    private final String firstPlayerUsername;
    private final String secondPlayerUsername;
    private final int firstPlayerCrownCount;
    private final int secondPlayerCrownCount;
    private final int id;

    /**
     * class constructor
     * @param firstPlayerUsername firstPlayerUsername
     * @param secondPlayerUsername secondPlayerUsername
     * @param firstPlayerCrownCount firstPlayerCrownCount
     * @param secondPlayerCrownCount secondPlayerCrownCount
     */
    public GameResult(String firstPlayerUsername, String secondPlayerUsername, int firstPlayerCrownCount, int secondPlayerCrownCount) {
        this.firstPlayerUsername = firstPlayerUsername;
        this.secondPlayerUsername = secondPlayerUsername;
        this.firstPlayerCrownCount = firstPlayerCrownCount;
        this.secondPlayerCrownCount = secondPlayerCrownCount;
        id = DBHandler.tableRowCount("Battles");
    }

    public GameResult(int id, String firstPlayerUsername, String secondPlayerUsername, int firstPlayerCrownCount, int secondPlayerCrownCount) {
        this.id = id;
        this.firstPlayerUsername = firstPlayerUsername;
        this.secondPlayerUsername = secondPlayerUsername;
        this.firstPlayerCrownCount = firstPlayerCrownCount;
        this.secondPlayerCrownCount = secondPlayerCrownCount;
    }

    /**
     * id getter
     * @return id
     */
    public int getId() {
        return id;
    }

    /**
     * firstPlayerUsername getter
     * @return firstPlayerUsername
     */
    public String getFirstPlayerUsername() {
        return firstPlayerUsername;
    }

    /**
     * secondPlayerUsername getter
     * @return secondPlayerUsername
     */
    public String getSecondPlayerUsername() {
        return secondPlayerUsername;
    }

    /**
     * firstPlayerCrownCount getter
     * @return firstPlayerCrownCount
     */
    public int getFirstPlayerCrownCount() {
        return firstPlayerCrownCount;
    }

    /**
     * secondPlayerCrownCount getter
     * @return secondPlayerCrownCount
     */
    public int getSecondPlayerCrownCount() {
        return secondPlayerCrownCount;
    }
}
