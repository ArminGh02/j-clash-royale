package model;

/**
 * Person class, implements real player
 * @author Adibov & Armin Gh
 * @version 1.0
 */
public class Person {
    private String username;
    private Password password;

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
     * check if the player can deploy chosen card
     * @return boolean result
     */
    public boolean canDeployCard() {
        if (false) // FIXME chosenCard == null
            return false;
        return true; // FIXME
    }

    /**
     * username getter
     * @return username
     */
    public String getUsername() {
        return username;
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
}
