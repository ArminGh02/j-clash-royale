package model.card;

import javafx.scene.image.Image;
import util.Config;

public class Fireball extends Spell {
  public Fireball() {
    super(4);
  }

  public static Image getDeckImage() {
    return new Image(Config.retrieveProperty("FIREBALL_DECK_IMAGE"));
  }

  @Override
  public double getElixirCost() {
    return ELIXIR_COST;
  }
}
