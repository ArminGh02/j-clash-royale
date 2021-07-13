package model.card;

import javafx.scene.image.Image;
import util.Config;

public class MiniPekka extends Troop {
  public MiniPekka() {
    super(4);
  }

  public static Image getDeckImage() {
    return new Image(Config.retrieveProperty("MINI_PEKKA_DECK_IMAGE"));
  }

  @Override
  public double getElixirCost() {
    return ELIXIR_COST;
  }
}
