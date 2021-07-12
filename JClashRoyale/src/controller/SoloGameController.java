package controller;

import model.Person;
import model.Robot;

/**
 * SoloGameController class, manages turns and players in a solo player game using singleton design pattern
 * @author Adibov & Armin Gh
 * @version 1.0
 */
public class SoloGameController {
    private Person personPlayer;
    private Robot robotPlayer;
    private static SoloGameController instance;

    /**
     * class constructor
     */
    private SoloGameController() {

    }

    /**
     * return an instance of the class
     * @return class object
     */
    public static SoloGameController getInstance() {
        if (instance == null)
            instance = new SoloGameController();
        return instance;
    }

    /**
     * personPlayer setter
     * @param personPlayer new personPlayer value
     */
    public void setPersonPlayer(Person personPlayer) {
        this.personPlayer = personPlayer;
    }

    /**
     * robotPlayer setter
     * @param robotPlayer new robotPlayer value
     */
    public void setRobotPlayer(Robot robotPlayer) {
        this.robotPlayer = robotPlayer;
    }
}
