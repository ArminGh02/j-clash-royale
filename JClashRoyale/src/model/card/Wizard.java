package model.card;

import javafx.scene.image.Image;
import util.Config;

public class Wizard extends Troop {
  public Wizard() {
    super(5, Cards.WIZARD);
  }

  public static Image getDeckImage() {
    return new Image(Config.retrieveProperty("WIZARD_DECK_IMAGE"));
  }

  /**
   * return the image key of the card with respect to config file
   *
   * @return image key
   */
  @Override
  public String getImageKey() {
    return "WIZARD";
  }
}
