package model.card;

/**
 * card class, saves card details and handles card usages
 * @author Adibov & Armin Gh
 * @version 1.0
 */
abstract public class Card {
  protected final int ELIXIR_COST;
  private int level;
  private int teamNumber;

  protected Card(int elixirCost) {
    this.ELIXIR_COST = elixirCost;
  }

  /**
   * teamNumber setter
   * @param teamNumber teamNumber new value
   */
  public void setTeamNumber(int teamNumber) {
    this.teamNumber = teamNumber;
  }

  /**
   * elixirCost getter
   * @return elixirCost
   */
  public int getElixirCost() {
    return ELIXIR_COST;
  }

  /**
   * teamNumber getter
   * @return teamNumber
   */
  public int getTeamNumber() {
    return teamNumber;
  }
}
