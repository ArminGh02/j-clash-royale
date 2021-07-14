package model.player;

import java.util.List;
import model.Password;
import model.card.Card;
import model.player.Player;

import model.card.Card;

/**
 * Person class, implements real player
 * @author Adibov & Armin Gh
 * @version 1.0
 */
public class Person extends Player {
    private String username;
    private Password password;

    /**
     * class constructor
     * @param username person username
     * @param password person password
     */
    public Person(String username, String password) {
        super();
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
