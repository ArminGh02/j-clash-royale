package model;

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
