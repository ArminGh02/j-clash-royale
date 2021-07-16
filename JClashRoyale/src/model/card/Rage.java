package model.card;

import javafx.scene.image.Image;
import util.Config;

public class Rage extends Spell {

  private static final Image deckImage = new Image(Config.retrieveProperty("RAGE_DECK_IMAGE"));

  public Rage() {
    super(3, "RAGE");
  }

  public static Image getDeckImage() {
    return deckImage;
  }
}
