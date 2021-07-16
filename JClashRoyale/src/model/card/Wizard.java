package model.card;

import javafx.scene.image.Image;
import model.Settings;
import util.Config;

public class Wizard extends Troop {
  public Wizard() {
    super(5, "WIZARD_DECK_ELIXIR_IMAGE");
    range = Range.RANGED;
  }

  public static Image getDeckImage() {
    return new Image(Config.retrieveProperty("WIZARD_DECK_IMAGE"));
  }

  @Override
  public String getImageKey() {
    return "WIZARD";
  }

  /**
   * get euclidean range in double
   *
   * @return range distance
   */
  @Override
  public double getRangeDistance() {
    return Settings.WIZARD_ATTACK_RANGE;
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
