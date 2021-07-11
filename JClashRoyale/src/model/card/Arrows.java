package model.card;

import javafx.scene.image.Image;
import util.Config;

public class Arrows extends Spell {
  public static Image getDeckImage() {
    return new Image(Config.retrieveProperty("ARROWS_DECK_IMAGE"));
  }
}
