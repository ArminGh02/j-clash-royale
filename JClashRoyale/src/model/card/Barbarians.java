package model.card;

import javafx.scene.image.Image;
import util.Config;

public class Barbarians extends Troop {
  public Barbarians() {
    super(5, Cards.BARBARIANS);
  }

  public static Image getDeckImage() {
    return new Image(Config.retrieveProperty("BARBARIANS_DECK_IMAGE"));
  }

  /**
   * return the image key of the card with respect to config file
   *
   * @return image key
   */
  @Override
  public String getImageKey() {
    return "BARBARIAN";
  }
}
