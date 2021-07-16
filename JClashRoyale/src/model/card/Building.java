package model.card;

import model.Settings;

public abstract class Building extends Card {
  public Building(int elixirCost, String deckElixirImageKey) {
    super(elixirCost, deckElixirImageKey);
  }
  private Range range;

  @Override
  public CardGroups getCardGroup() {
    return CardGroups.BUILDING;
  }

  /**
   * get euclidean range in double
   * @return range distance
   */
  public double getRangeDistance() {
    if (range.equals(Range.MELEE))
      return Settings.MELEE_ATTACK_RANGE;
    return 0;
  }
}
