package model.card;

import javafx.scene.image.Image;
import util.Config;

public class Archer extends Troop {
  public static Image getDeckImage() {
    return new Image(Config.retrieveProperty("ARCHER_DECK_IMAGE"));
  }
}
