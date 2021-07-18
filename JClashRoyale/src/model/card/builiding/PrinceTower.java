package model.card.builiding;

import model.Settings;

/**
 * PrinceTower class, implements prince tower behaviours
 *
 * @author Adibov & Armin Gh
 * @version 1.0
 */
public class PrinceTower extends Tower {
  /** class constructor */
  public PrinceTower() {
    super(
        0,
        "PRINCE_TOWER",
        new int[] {1400, 1512, 1624, 1750, 1890},
        new int[] {0, 54, 58, 62, 69},
        800);
    rangeDistance = 7.5;
  }
}
