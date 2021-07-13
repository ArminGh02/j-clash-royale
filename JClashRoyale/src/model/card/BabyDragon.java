package model.card;

import javafx.scene.image.Image;
import util.Config;

public class BabyDragon extends Troop {
  public BabyDragon() {
    super(4);
  }

  public static Image getDeckImage() {
    return new Image(Config.retrieveProperty("BABY_DRAGON_DECK_IMAGE"));
  }

  @Override
  public double getElixirCost() {
    return ELIXIR_COST;
  }
}
