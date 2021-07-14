package model.card;

import javafx.scene.image.Image;
import util.Config;

public class InfernoTower extends Building {
  public InfernoTower() {
    super(5);
  }

  public static Image getDeckImage() {
    return new Image(Config.retrieveProperty("INFERNO_TOWER_DECK_IMAGE"));
  }

  /**
   * return the image key of the card with respect to config file
   *
   * @return image key
   */
  @Override
  public String getImageKey() {
    return "INFERNO_TOWER";
  }
}
