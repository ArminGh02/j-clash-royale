package model.card;

import javafx.scene.image.Image;
import util.Config;

public class Cannon extends Building {
  public Cannon() {
    super(3, Cards.CANNON);
  }

  public static Image getDeckImage() {
    return new Image(Config.retrieveProperty("CANNON_DECK_IMAGE"));
  }

  /**
   * return the image key of the card with respect to config file
   *
   * @return image key
   */
  @Override
  public String getImageKey() {
    return "CANNON";
  }
}
