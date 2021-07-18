package model.card.troop;

import javafx.scene.image.Image;
import model.card.Card;
import model.card.Movement;
import model.card.Range;
import model.card.Speed;
import util.Config;

public class Giant extends Troop {

  private static final Image deckImage = new Image(Config.retrieveProperty("GIANT_DECK_IMAGE"));

  public Giant() {
    super(
        5, "GIANT", new int[] {2000, 2200, 2420, 2660, 2920}, new int[] {126, 138, 152, 167, 183},
        1500);
    range = Range.MELEE;
    speed = Speed.SLOW;
  }

  public static Image getDeckImage() {
    return deckImage;
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
    return new Giant();
  }
}
