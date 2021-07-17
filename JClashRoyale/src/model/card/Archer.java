package model.card;

import javafx.scene.image.Image;
import model.Settings;
import util.Config;

public class Archer extends Troop {

  private static final Image deckImage = new Image(
      Config.retrieveProperty("ARCHER_DECK_IMAGE"));

  public Archer() {
    super(3, "ARCHER");
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
    return Settings.ARCHER_ATTACK_RANGE;
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
    return new Archer();
  }
}
