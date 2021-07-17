package model.card.troop;

import javafx.scene.image.Image;
import model.Settings;
import model.card.Card;
import model.card.Movement;
import model.card.Range;
import util.Config;

public class Archer extends Troop {

  private static final Image deckImage = new Image(Config.retrieveProperty("ARCHER_DECK_IMAGE"));

  public Archer() {
    super(3, "ARCHER", new int[] {125, 127, 151, 166, 182}, new int[] {33, 44, 48, 53, 58}, 1200);
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
