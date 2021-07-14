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
  private int level;
  private final Image deckElixirImage;

  protected Card(int elixirCost, String deckElixirImageKey) {
    this.ELIXIR_COST = elixirCost;
    this.deckElixirImage = new Image(Config.retrieveProperty(deckElixirImageKey));
  }

  /**
   * elixirCost getter
   * @return elixirCost
   */
  public double getElixirCost() {
    return ELIXIR_COST;
  }

  public Image getDeckElixirImage() {
    return deckElixirImage;
  }
}
