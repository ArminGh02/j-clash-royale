package model.card;

import javafx.scene.image.Image;
import model.card.troop.Archer;
import model.card.troop.Barbarians;
import model.card.troop.Troop;
import util.Config;

/**
 * card class, saves card details and handles card usages
 *
 * @author Adibov & Armin Gh
 * @version 1.0
 */
public abstract class Card {
  private final int ELIXIR_COST;
  private Image deckSlotImage;
  private Image deployedImage; // TODO: make it final
  private int level;
  private int teamNumber;
  private final CardType type;
  private final String imageKey;

  protected Card(int elixirCost, String imageKey, CardType cardType) {
    this.ELIXIR_COST = elixirCost;
    this.imageKey = imageKey;
    if (this instanceof Troop) { // TODO: add other gifs and remove
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
    if (deckSlotImage == null)
      deckSlotImage = new Image(Config.retrieveProperty(imageKey + "_DECK_SLOT_IMAGE"));
    return deckSlotImage;
  }

  public CardType getType() {
    return type;
  }

  public Image getDeployedImage() {
    return deployedImage;
  }

  public Card newInstance() {
    throw new AbstractMethodError();
  }

  /**
   * imageKey getter
   * @return imageKey
   */
  public String getImageKey() {
    return imageKey;
  }
}
