package model.card;

import javafx.scene.image.Image;
import util.Config;

public class InfernoTower extends Building {
  public InfernoTower() {
    super(5, "INFERNO_TOWER_DECK_ELIXIR_IMAGE");
  }

  public static Image getDeckImage() {
    return new Image(Config.retrieveProperty("INFERNO_TOWER_DECK_IMAGE"));
  }
}
