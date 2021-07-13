package model.card;

/**
 * card class, saves card details and handles card usages
 * @author Adibov & Armin Gh
 * @version 1.0
 */
abstract public class Card {
  int elixirCost, level;

  protected Card(int elixirCost) {
    this.elixirCost = elixirCost;
  }

  /**
   * elixirCost getter
   * @return elixirCost
   */
  public int getElixirCost() {
    return elixirCost;
  }
}
