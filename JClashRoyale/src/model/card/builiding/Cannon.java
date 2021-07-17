package model.card.builiding;

import javafx.scene.image.Image;
import model.Settings;
import model.card.Card;
import model.card.Range;
import util.Config;

public class Cannon extends Building {

  private static final Image cannonDeckImage =
      new Image(Config.retrieveProperty("CANNON_DECK_IMAGE"));

  public Cannon() {
    super(
        3,
        "CANNON_DECK_ELIXIR_IMAGE",
        new int[] {380, 418, 459, 505, 554},
        new int[] {60, 66, 72, 79, 87},
        800);
    range = Range.RANGED;
  }

  public static Image getDeckImage() {
    return cannonDeckImage;
  }

  /**
   * get euclidean range in double
   *
   * @return range distance
   */
  @Override
  public double getRangeDistance() {
    return Settings.CANNON_ATTACK_RANGE;
  }

  @Override
  public Card newInstance() {
    return new Cannon();
  }
}
