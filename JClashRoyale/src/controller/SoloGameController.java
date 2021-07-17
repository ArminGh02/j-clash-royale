package controller;

import javafx.animation.AnimationTimer;
import model.card.Card;
import model.player.*;

/**
 * SoloGameController class, manages turns and players in a solo player game using singleton design
 * pattern
 *
 * @author Adibov & Armin Gh
 * @version 1.0
 */
public class SoloGameController {
  private Person personPlayer;
  private Robot robotPlayer = new BeginnerRobot();
  private GameTimerController timer;
  private AnimationTimer gameLoop;
  private static SoloGameController instance;

  /** class constructor */
  private SoloGameController() {}

  /**
   * return an instance of the class
   *
   * @return class object
   */
  public static SoloGameController getInstance() {
    if (instance == null) instance = new SoloGameController();
    return instance;
  }

  /**
   * deploy the given card for the given player
   * @param player the given player
   * @return deployed card
   */
  public Card deployCard(Player player) {
    if (!player.canDeployCard())
      return null;
    Card deployedCard = player.deployChosenCard();
    if (player.getPlayerGroup().equals(PlayerGroup.PERSON))
      deployedCard.setTeamNumber(0);
    else
      deployedCard.setTeamNumber(1);
    return deployedCard;
  }

  public Person getPersonPlayer() {
    return personPlayer;
  }

  public Robot getRobotPlayer() {
    return robotPlayer;
  }

  public void setTimer(GameTimerController timer) {
    this.timer = timer;
  }

  /**
   * personPlayer setter
   *
   * @param personPlayer new personPlayer value
   */
  public void setPersonPlayer(Person personPlayer) {
    this.personPlayer = personPlayer;
  }

  /**
   * robotPlayer setter
   *
   * @param robotPlayer new robotPlayer value
   */
  public void setRobotPlayer(Robot robotPlayer) {
    this.robotPlayer = robotPlayer;
  }

  /**
   * gameLoop setter
   * @param gameLoop gameLoop new value
   */
  public void setGameLoop(AnimationTimer gameLoop) {
    this.gameLoop = gameLoop;
  }
}
