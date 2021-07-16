package model.card;

import javafx.scene.image.Image;
import util.Config;

public class BabyDragon extends Troop {

  private static final Image deckImage = new Image(
      Config.retrieveProperty("BABY_DRAGON_DECK_IMAGE"));

  public BabyDragon() {
    super(4, "BABY_DRAGON");
  }

  public static Image getDeckImage() {
    return deckImage;
  }
}
