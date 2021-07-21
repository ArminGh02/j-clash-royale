package model.card.builiding;

import model.card.Movement;
import model.card.Target;

/**
 * Tower class, implements tower behaviours
 *
 * @author Adibov & Armin Gh
 * @version 1.0
 */
public abstract class Tower extends Building {
  /**
   * class constructor
   *
   * @param elixirCost elixirCost
   * @param imageKey imageKey
   * @param hpPerLevel
   * @param damagePerLevel
   */
  public Tower(
      int level,
      int elixirCost,
      String imageKey,
      int[] hpPerLevel,
      int[] damagePerLevel,
      int hitSpeed) {
    super(level, elixirCost, imageKey, hpPerLevel, damagePerLevel, hitSpeed);
    movement = Movement.GROUND;
    target = Target.ALL;
    isTower = true;
  }
}
