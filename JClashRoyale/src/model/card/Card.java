package model.card;

public abstract class Card {
  protected final int ELIXIR_COST;

  protected Card(int elixirCost) {
    this.ELIXIR_COST = elixirCost;
  }

  public abstract double getElixirCost();
}
