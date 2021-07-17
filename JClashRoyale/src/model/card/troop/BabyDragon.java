package model.card.troop;

import javafx.scene.image.Image;
import model.Settings;
import model.card.Card;
import model.card.Movement;
import model.card.Range;
import util.Config;

public class BabyDragon extends Troop {

  private static final Image deckImage =
      new Image(Config.retrieveProperty("BABY_DRAGON_DECK_IMAGE"));

  public BabyDragon() {
    super(
        4,
        "BABY_DRAGON",
        new int[] {800, 880, 968, 1064, 1168},
        new int[] {100, 110, 121, 133, 146}, 1800);
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
  public Movement getMovement() {
    return Movement.AIR;
  }

  @Override
  public Card newInstance() {
    return new BabyDragon();
  }
}
