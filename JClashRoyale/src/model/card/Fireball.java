package model.card;

import javafx.scene.image.Image;
import util.Config;

public class Fireball extends Spell {
  public static Image getDeckImage() {
    return new Image(Config.retrieveProperty("FIREBALL_DECK_IMAGE"));
  }
}
