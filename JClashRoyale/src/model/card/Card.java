package model.card;

/**
 * card class, saves card details and handles card usages
 * @author Adibov & Armin Gh
 * @version 1.0
 */
abstract public class Card {
  protected final int ELIXIR_COST;
  private int level;

  protected Card(int elixirCost) {
    this.ELIXIR_COST = elixirCost;
  }

  /**
   * elixirCost getter
   * @return elixirCost
   */
  public double getElixirCost() {
    return ELIXIR_COST;
  }
}
