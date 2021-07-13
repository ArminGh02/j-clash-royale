package model.card;

import javafx.scene.image.Image;
import util.Config;

public class Barbarians extends Troop {
  public Barbarians() {
    super(5);
  }

  public static Image getDeckImage() {
    return new Image(Config.retrieveProperty("BARBARIANS_DECK_IMAGE"));
  }
}
