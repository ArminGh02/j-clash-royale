package model.card;

import javafx.scene.image.Image;
import util.Config;

public class Fireball extends Spell {
  public Fireball() {
    super(4);
  }

  public static Image getDeckImage() {
    return new Image(Config.retrieveProperty("FIREBALL_DECK_IMAGE"));
  }

  /**
   * return the image key of the card with respect to config file
   *
   * @return image key
   */
  @Override
  public String getImageKey() {
    return "FIRE_BALL";
  }
}
