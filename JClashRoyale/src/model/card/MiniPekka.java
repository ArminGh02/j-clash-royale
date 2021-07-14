package model.card;

import javafx.scene.image.Image;
import util.Config;

public class MiniPekka extends Troop {
  public MiniPekka() {
    super(4, Cards.MINI_PEKKA);
  }

  public static Image getDeckImage() {
    return new Image(Config.retrieveProperty("MINI_PEKKA_DECK_IMAGE"));
  }

  /**
   * return the image key of the card with respect to config file
   *
   * @return image key
   */
  @Override
  public String getImageKey() {
    return "MINI_PEKKA";
  }
}
