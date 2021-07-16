package model.card;

import javafx.scene.image.Image;
import model.Settings;
import util.Config;

public class InfernoTower extends Building {
  public InfernoTower() {
    super(5, "INFERNO_TOWER_DECK_ELIXIR_IMAGE");
    range = Range.RANGED;
  }

  public static Image getDeckImage() {
    return new Image(Config.retrieveProperty("INFERNO_TOWER_DECK_IMAGE"));
  }

  @Override
  public String getImageKey() {
    return "INFERNO_TOWER";
  }

  /**
   * get euclidean range in double
   *
   * @return range distance
   */
  @Override
  public double getRangeDistance() {
    return Settings.INFERNO_TOWER_ATTACK_RANGE;
  }
}
