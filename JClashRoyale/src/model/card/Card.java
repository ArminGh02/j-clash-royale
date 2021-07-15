package model.card;

import javafx.scene.image.Image;
import util.Config;

/**
 * card class, saves card details and handles card usages
 * @author Adibov & Armin Gh
 * @version 1.0
 */
abstract public class Card {
  protected final int ELIXIR_COST;
  private int level, teamNumber;
  private final Image deckElixirImage;

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
}
