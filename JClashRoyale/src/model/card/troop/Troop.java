package model.card.troop;

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
  private Speed speed;

  public Troop(
      int elixirCost, String imageKey, int[] hpPerLevel, int[] damagePerLevel, int hitSpeed) {
    super(elixirCost, imageKey, CardType.TROOP, hpPerLevel, damagePerLevel, hitSpeed);
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
   * speed getter
   *
   * @return speed
   */
  public Speed getSpeed() {
    return speed;
  }

  /**
   * movement getter
   *
   * @return movement
   */
  public abstract Movement getMovement();
}
