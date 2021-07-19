package model.player;

import model.Password;

/**
 * Person class, implements real player
 * @author Adibov & Armin Gh
 * @version 1.0
 */
public class Person extends Player {
    private String username;
    private Password password;
    private int level;
    private int chosenSlotIndex = -1;

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
     * get player group of the player
     *
     * @return PlayerGroup
     */
    @Override
    public PlayerGroup getPlayerGroup() {
        return PlayerGroup.PERSON;
    }
}
