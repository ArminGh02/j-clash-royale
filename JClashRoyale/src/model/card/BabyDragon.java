package model.card;

import javafx.scene.image.Image;
import util.Config;

public class BabyDragon extends Troop {
  public static Image getDeckImage() {
    return new Image(Config.retrieveProperty("BABY_DRAGON_DECK_IMAGE"));
  }
}
