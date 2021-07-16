package model.card;

import javafx.scene.image.Image;
import util.Config;

public class MiniPekka extends Troop {

  private static final Image deckImage = new Image(
      Config.retrieveProperty("MINI_PEKKA_DECK_IMAGE"));

  public MiniPekka() {
    super(4, "MINI_PEKKA");
  }

  public static Image getDeckImage() {
    return deckImage;
  }
}
