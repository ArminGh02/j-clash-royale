package model.card.troop;

import model.Settings;
import model.card.Attacker;
import model.card.CardType;
import model.card.Movement;
import model.card.Speed;

/**
 * Troop class, saves
 *
 * @author Adibov & Armin Gh
 * @version 1.0
 */
public abstract class Troop extends Attacker {
  private int areaSplash;
  protected int count;
  protected Speed speed;

  public Troop(
      int level,
      int elixirCost,
      String imageKey,
      int[] hpPerLevel,
      int[] damagePerLevel,
      int hitSpeed) {
    super(level, elixirCost, imageKey, CardType.TROOP, hpPerLevel, damagePerLevel, hitSpeed);
  }

  /**
   * areaSplash getter
   *
   * @return areaSplash
   */
  public int getAreaSplash() {
    return areaSplash;
  }

  /**
   * count getter
   *
   * @return count
   */
  public int getCount() {
    return count;
  }

  /**
   * get speed of the troop with respect to speed and attributeMultiplier field
   *
   * @return speed
   */
  public double getSpeed() {
    switch (speed) {
      case SLOW:
        return Settings.SLOW_SPEED * attributeMultiplier;
      case MEDIUM:
        return Settings.MEDIUM_SPEED * attributeMultiplier;
      case FAST:
        return Settings.FAST_SPEED * attributeMultiplier;
    }
    return Settings.SLOW_SPEED;
  }

  /**
   * movement getter
   *
   * @return movement
   */
  public abstract Movement getMovement();
}
