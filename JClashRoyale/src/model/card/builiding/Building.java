package model.card.builiding;

import model.card.Attacker;
import model.card.CardType;

public abstract class Building extends Attacker {
  public Building(
      int elixirCost, String imageKey, int[] hpPerLevel, int[] damagePerLevel, int hitSpeed) {
    super(elixirCost, imageKey, CardType.BUILDING, hpPerLevel, damagePerLevel, hitSpeed);
  }
}
