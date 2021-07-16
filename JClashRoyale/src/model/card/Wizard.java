package model.card;

import javafx.scene.image.Image;
import util.Config;

public class Wizard extends Troop {

  private static final Image deckImage = new Image(
      Config.retrieveProperty("WIZARD_DECK_IMAGE"));

  public Wizard() {
    super(5, "WIZARD");
  }

  public static Image getDeckImage() {
    return deckImage;
  }
}
