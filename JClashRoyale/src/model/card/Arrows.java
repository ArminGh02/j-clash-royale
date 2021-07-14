package model.card;

import javafx.scene.image.Image;
import util.Config;

public class Arrows extends Spell {
  public Arrows() {
    super(3, "ARROWS_DECK_ELIXIR_IMAGE");
  }

  public static Image getDeckImage() {
    return new Image(Config.retrieveProperty("ARROWS_DECK_IMAGE"));
  }
}
