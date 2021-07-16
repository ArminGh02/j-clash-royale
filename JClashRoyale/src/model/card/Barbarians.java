package model.card;

import javafx.scene.image.Image;
import util.Config;

public class Barbarians extends Troop {

  private static final Image deckImage = new Image(
      Config.retrieveProperty("BARBARIANS_DECK_IMAGE"));

  public Barbarians() {
    super(5, "BARBARIANS");
  }

  public static Image getDeckImage() {
    return deckImage;
  }
}
