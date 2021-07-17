package model.card;

import javafx.scene.image.Image;
import model.Settings;
import util.Config;

public class Cannon extends Building {

  private static final Image cannonDeckImage = new Image(
      Config.retrieveProperty("CANNON_DECK_IMAGE"));

  public Cannon() {
    super(3, "CANNON_DECK_ELIXIR_IMAGE");
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
}
