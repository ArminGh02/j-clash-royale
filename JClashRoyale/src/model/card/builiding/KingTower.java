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
  public KingTower(int level) {
    super(
        level,
        0,
        "KING_TOWER",
        new int[] {2400, 2568, 2736, 2904, 3096},
        new int[] {50, 53, 57, 60, 64},
        1000);
    rangeDistance = 7.0;
  }
}
