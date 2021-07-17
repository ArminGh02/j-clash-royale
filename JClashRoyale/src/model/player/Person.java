package model.player;

import java.util.List;
import javafx.scene.image.ImageView;
import model.Password;
import model.card.Card;
import model.player.Player;

/**
 * Person class, implements real player
 * @author Adibov & Armin Gh
 * @version 1.0
 */
public class Person extends Player {
    private String username;
    private Password password;
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

    public void setChosenSlotIndex(int i) {
//        this.chosenSlotIndex = i; FIXME delete this line
        deck.setChosenCard(deck.getActiveCard(i));
    }

    public int getChosenSlotIndex() {
        return chosenSlotIndex;
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
