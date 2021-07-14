package model.card;

/**
 * card class, saves card details and handles card usages
 * @author Adibov & Armin Gh
 * @version 1.0
 */
abstract public class Card {
  protected final int ELIXIR_COST;
  protected final Cards cardType;
  protected CardGroups cardGroup;
  private int level;
  private int teamNumber;

  public Card(int ELIXIR_COST, Cards cardType, CardGroups cardGroup) {
    this.ELIXIR_COST = ELIXIR_COST;
    this.cardType = cardType;
    this.cardGroup = cardGroup;
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

  /**
   * return the image key of the card with respect to config file
   * @return image key
   */
  abstract public String getImageKey();

  /**
   * cardType getter
   * @return cardType
   */
  public Cards getCardType() {
    return cardType;
  }

  /**
   * cardGroup getter
   * @return cardGroup
   */
  public CardGroups getCardGroup() {
    return cardGroup;
  }
}
