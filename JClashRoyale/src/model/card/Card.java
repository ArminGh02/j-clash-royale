package model.card;

import javafx.scene.image.Image;
import util.Config;

import java.awt.geom.Point2D;

/**
 * card class, saves card details and handles card usages
 * @author Adibov & Armin Gh
 * @version 1.0
 */
abstract public class Card {
  protected final int ELIXIR_COST;
  private int level, teamNumber;
  private final Image deckElixirImage;
  private Point2D velocity;
  private Card currentTarget;
  private boolean isAttacking;

  protected Card(int elixirCost, String deckElixirImageKey) {
    this.ELIXIR_COST = elixirCost;
    this.deckElixirImage = new Image(Config.retrieveProperty(deckElixirImageKey));
  }

  /**
   * teamNumber setter
   * @param teamNumber teamNumber new value
   */
  public void setTeamNumber(int teamNumber) {
    this.teamNumber = teamNumber;
  }

  /**
   * velocity setter
   * @param velocity velocity new value
   */
  public void setVelocity(Point2D velocity) {
    this.velocity = velocity;
  }

  /**
   * currentTarget setter
   * @param currentTarget currentTarget new value
   */
  public void setCurrentTarget(Card currentTarget) {
    this.currentTarget = currentTarget;
  }

  /**
   * isAttacking setter
   * @param attacking isAttacking new value
   */
  public void setAttacking(boolean attacking) {
    isAttacking = attacking;
  }

  /**
   * teamNumber getter
   * @return teamNumber
   */
  public int getTeamNumber() {
    return teamNumber;
  }

  /**
   * elixirCost getter
   * @return elixirCost
   */
  public int getElixirCost() {
    return ELIXIR_COST;
  }

  public Image getDeckElixirImage() {
    return deckElixirImage;
  }

  abstract public CardGroups getCardGroup();

  abstract public String getImageKey();

  /**
   * velocity getter
   * @return velocity
   */
  public Point2D getVelocity() {
      return velocity;
  }

  /**
   * currentTarget getter
   * @return currentTarget
   */
  public Card getCurrentTarget() {
    return currentTarget;
  }

  /**
   * isAttacking getter
   * @return isAttacking
   */
  public boolean isAttacking() {
    return isAttacking;
  }
}
