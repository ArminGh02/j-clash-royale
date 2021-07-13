package model.card;

import javafx.scene.image.Image;
import util.Config;

public class Cannon extends Building {
  public Cannon() {
    super(3);
  }

  public static Image getDeckImage() {
    return new Image(Config.retrieveProperty("CANNON_DECK_IMAGE"));
  }
}
