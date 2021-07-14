package model.card;

import javafx.scene.image.Image;
import util.Config;

public class Giant extends Troop {
  public Giant() {
    super(5, Cards.GIANT);
  }

  public static Image getDeckImage() {
    return new Image(Config.retrieveProperty("GIANT_DECK_IMAGE"));
  }

  /**
   * return the image key of the card with respect to config file
   *
   * @return image key
   */
  @Override
  public String getImageKey() {
    return "GIANT";
  }
}
