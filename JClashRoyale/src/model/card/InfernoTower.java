package model.card;

import javafx.scene.image.Image;
import model.Settings;
import util.Config;

public class InfernoTower extends Building {

  private static final Image infernoTowerDeckImage = new Image(
      Config.retrieveProperty("INFERNO_TOWER_DECK_IMAGE"));

  public InfernoTower() {
    super(5, "INFERNO_TOWER");
    range = Range.RANGED;
  }

  public static Image getDeckImage() {
    return infernoTowerDeckImage;
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
