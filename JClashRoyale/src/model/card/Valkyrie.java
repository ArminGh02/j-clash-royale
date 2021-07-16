package model.card;

import javafx.scene.image.Image;
import util.Config;

public class Valkyrie extends Troop {

  private static final Image deckImage = new Image(
      Config.retrieveProperty("VALKYRIE_DECK_IMAGE"));

  public Valkyrie() {
    super(4, "VALKYRIE");
  }

  public static Image getDeckImage() {
    return deckImage;
  }
}
