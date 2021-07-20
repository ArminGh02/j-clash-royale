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
   * @param deckElixirImageKey deckElixirImageKey
   * @param hpPerLevel
   * @param damagePerLevel
   */
  public Tower(
      int level,
      int elixirCost,
      String deckElixirImageKey,
      int[] hpPerLevel,
      int[] damagePerLevel,
      int hitSpeed) {
    super(level, elixirCost, deckElixirImageKey, hpPerLevel, damagePerLevel, hitSpeed);
    movement = Movement.GROUND;
    target = Target.ALL;
  }
}
