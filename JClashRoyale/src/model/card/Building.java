package model.card;

public abstract class Building extends Card {
  public Building(int elixirCost, String imageKey) {
    super(elixirCost, imageKey, CardType.BUILDING);
  }
}
