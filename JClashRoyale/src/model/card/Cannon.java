package model.card;

import javafx.scene.image.Image;
import util.Config;

public class Cannon extends Building {
  public static Image getDeckImage() {
    return new Image(Config.retrieveProperty("CANNON_DECK_IMAGE"));
  }
}
