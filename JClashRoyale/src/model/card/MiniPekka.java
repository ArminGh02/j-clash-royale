package model.card;

import javafx.scene.image.Image;
import util.Config;

public class MiniPekka extends Troop {
  public MiniPekka() {
    super(4, "MINI_PEKKA_DECK_ELIXIR_IMAGE");
  }

  public static Image getDeckImage() {
    return new Image(Config.retrieveProperty("MINI_PEKKA_DECK_IMAGE"));
  }

  @Override
  public String getImageKey() {
    return "MINI_PEKKA";
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
