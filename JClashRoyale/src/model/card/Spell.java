package model.card;

public abstract class Spell extends Card {

  public Spell(int elixirCost, String imageKey) {
    super(elixirCost, imageKey, CardType.SPELL);
  }
}
