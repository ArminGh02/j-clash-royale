package model.card.spell;

import model.card.Card;
import model.card.CardType;

public abstract class Spell extends Card {
  protected double radius; // FIXME fix radius

  /**
   * class constructor
   * @param elixirCost elixirCost
   * @param imageKey imageKey
   */
  public Spell(int elixirCost, String imageKey) {
    super(elixirCost, imageKey, CardType.SPELL);
  }

  /**
   * radius getter
   * @return radius
   */
  public double getRadius() {
    return radius;
  }
}
