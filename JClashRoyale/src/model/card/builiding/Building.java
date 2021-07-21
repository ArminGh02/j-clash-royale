package model.card.builiding;

import model.card.Attacker;
import model.card.CardType;

public abstract class Building extends Attacker {
  protected boolean isTower = false;

  public Building(
      int level,
      int elixirCost,
      String imageKey,
      int[] hpPerLevel,
      int[] damagePerLevel,
      int hitSpeed) {
    super(level, elixirCost, imageKey, CardType.BUILDING, hpPerLevel, damagePerLevel, hitSpeed);
  }

  /**
   * isTower getter
   * @return isTower
   */
  public boolean isTower() {
    return isTower;
  }
}
