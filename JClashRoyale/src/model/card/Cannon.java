package model.card;

import javafx.scene.image.Image;
import util.Config;

public class Cannon extends Building {

  private static final Image cannonDeckImage = new Image(
      Config.retrieveProperty("CANNON_DECK_IMAGE"));

  public Cannon() {
    super(3, "CANNON_DECK_ELIXIR_IMAGE");
  }

  public static Image getDeckImage() {
    return cannonDeckImage;
  }
}
