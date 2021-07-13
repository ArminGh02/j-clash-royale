package model.card;

import javafx.scene.image.Image;
import util.Config;

public class Archer extends Troop {
  public Archer() {
    super(3);
  }

  public static Image getDeckImage() {
    return new Image(Config.retrieveProperty("ARCHER_DECK_IMAGE"));
  }
}
