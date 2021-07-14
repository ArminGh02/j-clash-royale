package model.card;

import javafx.scene.image.Image;
import util.Config;

public class Arrows extends Spell {
  public Arrows() {
    super(3);
  }

  public static Image getDeckImage() {
    return new Image(Config.retrieveProperty("ARROWS_DECK_IMAGE"));
  }

  /**
   * return the image key of the card with respect to config file
   *
   * @return image key
   */
  @Override
  public String getImageKey() {
    return "ARROWS";
  }
}
