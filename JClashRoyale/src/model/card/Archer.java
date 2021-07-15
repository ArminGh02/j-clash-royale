package model.card;

import javafx.scene.image.Image;
import util.Config;

public class Archer extends Troop {
  public Archer() {
    super(3, "ARCHER_DECK_ELIXIR_IMAGE");
  }

  public static Image getDeckImage() {
    return new Image(Config.retrieveProperty("ARCHER_DECK_IMAGE"));
  }

  @Override
  public String getImageKey() {
    return "ARCHER";
  }
}
