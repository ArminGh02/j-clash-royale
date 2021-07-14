package model.card;

import javafx.scene.image.Image;
import util.Config;

public class Rage extends Spell {
  public Rage() {
    super(3);
  }

  public static Image getDeckImage() {
    return new Image(Config.retrieveProperty("RAGE_DECK_IMAGE"));
  }

  /**
   * return the image key of the card with respect to config file
   *
   * @return image key
   */
  @Override
  public String getImageKey() {
    return "RAGE";
  }
}
