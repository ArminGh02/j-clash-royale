package model.card;

import javafx.scene.image.Image;
import util.Config;

public class Rage extends Spell {
  public static Image getDeckImage() {
    return new Image(Config.retrieveProperty("RAGE_DECK_IMAGE"));
  }
}
