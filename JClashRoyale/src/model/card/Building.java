package model.card;

public abstract class Building extends Card {
  public Building(int elixirCost, String deckElixirImageKey) {
    super(elixirCost, deckElixirImageKey);
  }

  @Override
  public CardType getCardGroup() {
    return CardType.BUILDING;
  }
}
