package model.card;

import javafx.scene.image.Image;
import util.Config;

public class MiniPekka extends Troop {
  public static Image getDeckImage() {
    return new Image(Config.retrieveProperty("MINI_PEKKA_DECK_IMAGE"));
  }
}
