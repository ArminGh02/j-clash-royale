package model.card;

import javafx.scene.image.Image;
import model.Settings;
import util.Config;

public class Cannon extends Building {
  public Cannon() {
    super(3, "CANNON_DECK_ELIXIR_IMAGE");
  }

  public static Image getDeckImage() {
    return new Image(Config.retrieveProperty("CANNON_DECK_IMAGE"));
  }

  @Override
  public String getImageKey() {
    return "CANNON";
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
