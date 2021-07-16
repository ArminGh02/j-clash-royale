package model.card;

import javafx.scene.image.Image;
import util.Config;

/**
 * card class, saves card details and handles card usages
 *
 * @author Adibov & Armin Gh
 * @version 1.0
 */
public abstract class Card {
  private final int ELIXIR_COST;
  private final Image deckSlotImage;
  private Image deployedImage; // TODO: make it final
  private int level;
  private int teamNumber;
  private final CardType type;

  protected Card(int elixirCost, String imageKey, CardType cardType) {
    this.ELIXIR_COST = elixirCost;
    this.deckSlotImage = new Image(Config.retrieveProperty(imageKey + "_DECK_SLOT_IMAGE"));
    if (this instanceof Barbarians) { // TODO: add other gifs and remove this if-condition
      this.deployedImage = new Image(Config.retrieveProperty(imageKey));
    }
    this.type = cardType;
  }

  /**
   * teamNumber setter
   *
   * @param teamNumber teamNumber new value
   */
  public void setTeamNumber(int teamNumber) {
    this.teamNumber = teamNumber;
  }

  /**
   * teamNumber getter
   *
   * @return teamNumber
   */
  public int getTeamNumber() {
    return teamNumber;
  }

  /**
   * elixirCost getter
   *
   * @return elixirCost
   */
  public int getElixirCost() {
    return ELIXIR_COST;
  }

  public Image getDeckSlotImage() {
    return deckSlotImage;
  }

  public CardType getType() {
    return type;
  }

  public Image getDeployedImage() {
    return deployedImage;
  }
}
