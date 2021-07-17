package model.card;

import javafx.scene.image.Image;
import model.Settings;
import util.Config;

public class Wizard extends Troop {

  private static final Image deckImage = new Image(
      Config.retrieveProperty("WIZARD_DECK_IMAGE"));

  public Wizard() {
    super(5, "WIZARD");
    range = Range.RANGED;
  }

  public static Image getDeckImage() {
    return deckImage;
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
