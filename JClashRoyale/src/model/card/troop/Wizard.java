package model.card.troop;

import javafx.scene.image.Image;
import model.Settings;
import model.card.Card;
import model.card.Movement;
import model.card.Range;
import util.Config;

public class Wizard extends Troop {

  private static final Image deckImage = new Image(Config.retrieveProperty("WIZARD_DECK_IMAGE"));

  public Wizard() {
    super(
        5,
        "WIZARD",
        new int[] {340, 374, 411, 452, 496},
        new int[] {130, 143, 157, 172, 189},
        1700);
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
  public Movement getMovement() {
    return Movement.GROUND;
  }

  @Override
  public Card newInstance() {
    return new Wizard();
  }
}
