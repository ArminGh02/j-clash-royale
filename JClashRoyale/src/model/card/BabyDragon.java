package model.card;

import javafx.scene.image.Image;
import model.Settings;
import util.Config;

public class BabyDragon extends Troop {

  private static final Image deckImage = new Image(
      Config.retrieveProperty("BABY_DRAGON_DECK_IMAGE"));

  public BabyDragon() {
    super(4, "BABY_DRAGON");
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
    return Settings.BABY_DRAGON_ATTACK_RANGE;
  }

  /**
   * movement getter
   *
   * @return movement
   */
  @Override
  public MOVEMENT getMovement() {
    return MOVEMENT.AIR;
  }
}
