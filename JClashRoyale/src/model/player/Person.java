package model.player;

import model.GameResult;
import model.Password;
import model.Settings;
import util.DBHandler;

import java.util.ArrayList;

/**
 * Person class, implements real player
 * @author Adibov & Armin Gh
 * @version 1.0
 */
public class Person extends Player {
    private String username;
    private Password password;
    private int level;
    private int points;
    private int chosenSlotIndex = -1;
    private ArrayList<GameResult> gameResults;

    /**
     * class constructor
     * @param username person username
     * @param password person password
     */
    public Person(String username, String password) {
        this.username = username;
        this.password = new Password(password);
    }

    /**
     * username getter
     * @return username
     */
    public String getUsername() {
        return username;
    }

    /**
     * level getter
     * @return level
     */
    public int getLevel() {
        return level;
    }

    /**
     * password getter
     * @return password
     */
    public Password getPassword() {
        return password;
    }

    /**
     * password setter
     * @param password new password
     */
    public void setPassword(String  password) {
        this.password = new Password(password);
        this.password.setHashedPassword(password);
    }

    /**
     * level setter
     * @param level level new value
     */
    public void setLevel(int level) {
        this.level = level;
    }

    /**
     * increment level
     */
    public void incrementLevel() {
        if (level == Settings.MAXIMUM_LEVEL)
            return;
        level++;
        DBHandler.updatePersonsLevel(this);
    }

    /**
     * check if the person has been leveled up
     */
    private void checkLevelUp() {
        if (level < Settings.MAXIMUM_LEVEL && points >= Settings.LEVEL_POINTS[level])
            incrementLevel();
    }

    /**
     * increase points
     * @param increaseAmount increase amount
     */
    public void increasePoints(int increaseAmount) {
        points += increaseAmount;
        checkLevelUp();
        DBHandler.updatePersonsPoints(this);
    }

    /**
     * points setter
     * @param points points new value
     */
    public void setPoints(int points) {
        this.points = points;
    }

    /**
     * gameResults setter
     * @param gameResults gameResults new value
     */
    public void setGameResults(ArrayList<GameResult> gameResults) {
        this.gameResults = gameResults;
    }

    /**
     * add the given new gameResult to the list of gameResults
     * @param gameResult the given gameResult
     */
    public void addGameResult(GameResult gameResult) {
        gameResults.add(gameResult);
        DBHandler.addGameResult(gameResult);
    }

    /**
     * get player group of the player
     *
     * @return PlayerGroup
     */
    @Override
    public PlayerGroup getPlayerGroup() {
        return PlayerGroup.PERSON;
    }

    public int getPoints() {
        return points;
    }
}
