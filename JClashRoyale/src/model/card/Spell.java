package model.card;

public abstract class Spell extends Card {

  public Spell(int elixirCost, Cards cardType) {
    super(elixirCost, cardType, CardGroups.SPELL);
  }
}
