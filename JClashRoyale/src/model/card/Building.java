package model.card;

public abstract class Building extends Card {
  public Building(int ELIXIR_COST, Cards cardType) {
    super(ELIXIR_COST, cardType, CardGroups.BUILDING);
  }
}
