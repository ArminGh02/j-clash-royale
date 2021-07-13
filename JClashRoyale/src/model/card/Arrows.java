package model.card;

import javafx.scene.image.Image;
import util.Config;

public class Arrows extends Spell {
  public Arrows() {
    super(3);
  }

  public static Image getDeckImage() {
    return new Image(Config.retrieveProperty("ARROWS_DECK_IMAGE"));
  }

  @Override
  public double getElixirCost() {
    return ELIXIR_COST;
  }
}
