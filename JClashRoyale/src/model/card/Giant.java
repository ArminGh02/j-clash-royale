package model.card;

import javafx.scene.image.Image;
import util.Config;

public class Giant extends Troop {
  public static Image getDeckImage() {
    return new Image(Config.retrieveProperty("GIANT_DECK_IMAGE"));
  }
}
