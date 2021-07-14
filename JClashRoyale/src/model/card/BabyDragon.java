package model.card;

import javafx.scene.image.Image;
import util.Config;

public class BabyDragon extends Troop {
  public BabyDragon() {
    super(4, Cards.BABY_DRAGON);
  }

  public static Image getDeckImage() {
    return new Image(Config.retrieveProperty("BABY_DRAGON_DECK_IMAGE"));
  }

  /**
   * return the image key of the card with respect to config file
   *
   * @return image key
   */
  @Override
  public String getImageKey() {
    return "BABY_DRAGON";
  }
}
