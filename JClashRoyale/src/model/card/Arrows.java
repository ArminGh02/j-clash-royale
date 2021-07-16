package model.card;

import javafx.scene.image.Image;
import util.Config;

public class Arrows extends Spell {

  private static final Image deckImage = new Image(
      Config.retrieveProperty("ARROWS_DECK_IMAGE"));

  public Arrows() {
    super(3, "ARROWS");
  }

  public static Image getDeckImage() {
    return deckImage;
  }
}
