package model.card;

import javafx.scene.image.Image;
import util.Config;

public class Wizard extends Troop {
  public Wizard() {
    super(5);
  }

  public static Image getDeckImage() {
    return new Image(Config.retrieveProperty("WIZARD_DECK_IMAGE"));
  }

  @Override
  public double getElixirCost() {
    return ELIXIR_COST;
  }
}
