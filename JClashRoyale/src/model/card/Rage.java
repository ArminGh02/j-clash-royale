package model.card;

import javafx.scene.image.Image;
import util.Config;

public class Rage extends Spell {
  public Rage() {
    super(3);
  }

  public static Image getDeckImage() {
    return new Image(Config.retrieveProperty("RAGE_DECK_IMAGE"));
  }

  @Override
  public double getElixirCost() {
    return ELIXIR_COST;
  }
}
