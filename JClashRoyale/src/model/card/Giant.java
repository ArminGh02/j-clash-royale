package model.card;

import javafx.scene.image.Image;
import util.Config;

public class Giant extends Troop {
  public Giant() {
    super(5, "GIANT_DECK_ELIXIR_IMAGE");
    range = Range.MELEE;
  }

  public static Image getDeckImage() {
    return new Image(Config.retrieveProperty("GIANT_DECK_IMAGE"));
  }

  @Override
  public String getImageKey() {
    return "GIANT";
  }

  /**
   * movement getter
   *
   * @return movement
   */
  @Override
  public MOVEMENT getMovement() {
    return MOVEMENT.GROUND;
  }
}
