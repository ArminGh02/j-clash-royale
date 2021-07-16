package model.card;

import javafx.scene.image.Image;
import util.Config;

public class Archer extends Troop {

  private static final Image deckImage = new Image(
      Config.retrieveProperty("ARCHER_DECK_IMAGE"));

  public Archer() {
    super(3, "ARCHER");
  }

  public static Image getDeckImage() {
    return deckImage;
  }
}
