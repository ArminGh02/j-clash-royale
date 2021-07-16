package model.card;

import javafx.scene.image.Image;
import model.Settings;
import util.Config;

public class Archer extends Troop {
  public Archer() {
    super(3, "ARCHER_DECK_ELIXIR_IMAGE");
  }

  public static Image getDeckImage() {
    return new Image(Config.retrieveProperty("ARCHER_DECK_IMAGE"));
  }

  @Override
  public String getImageKey() {
    return "ARCHER";
  }

  /**
   * get euclidean range in double
   *
   * @return range distance
   */
  @Override
  public double getRangeDistance() {
    return Settings.ARCHER_ATTACK_RANGE;
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
