package model.card.builiding;

import model.Settings;

/**
 * KingTower class, implements king tower behaviours
 *
 * @author Adibov & Armin Gh
 * @version 1.0
 */
public class KingTower extends Tower {
  /** class constructor */
  public KingTower() {
    super(
        0,
        "KING_TOWER",
        new int[] {2400, 2568, 2736, 2904, 3096},
        new int[] {50, 53, 57, 60, 64},
        1000);
  }

  /**
   * get euclidean range in double
   *
   * @return range distance
   */
  @Override
  public double getRangeDistance() {
    return Settings.KING_TOWER_ATTACK_RANGE;
  }
}
