package model.card;

import javafx.scene.image.Image;
import util.Config;

public class Giant extends Troop {

  private static final Image deckImage = new Image(
      Config.retrieveProperty("GIANT_DECK_IMAGE"));

  public Giant() {
    super(5, "GIANT");
    range = Range.MELEE;
  }

  public static Image getDeckImage() {
    return deckImage;
  }

  /**
   * movement getter
   *
   * @return movement
   */
  @Override
  public Movement getMovement() {
    return Movement.GROUND;
  }

  @Override
  public Card newInstance() {
    return new Giant();
  }
}
