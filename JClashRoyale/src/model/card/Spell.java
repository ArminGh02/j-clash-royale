package model.card;

public abstract class Spell extends Card {

  public Spell(int elixirCost, String deckElixirImageKey) {
    super(elixirCost, deckElixirImageKey);
  }

  @Override
  public CardType getCardGroup() {
    return CardType.SPELL;
  }
}
