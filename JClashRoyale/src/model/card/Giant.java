package model.card;

import javafx.scene.image.Image;
import util.Config;

public class Giant extends Troop {
  public Giant() {
    super(5);
  }

  public static Image getDeckImage() {
    return new Image(Config.retrieveProperty("GIANT_DECK_IMAGE"));
  }

  @Override
  public double getElixirCost() {
    return ELIXIR_COST;
  }
}
