package model.card.builiding;

/**
 * Tower class, implements tower behaviours
 *
 * @author Adibov & Armin Gh
 * @version 1.0
 */
public abstract class Tower extends Building {
  /**
   * class constructor
   *
   * @param elixirCost elixirCost
   * @param deckElixirImageKey deckElixirImageKey
   * @param hpPerLevel
   * @param damagePerLevel
   */
  public Tower(
      int elixirCost,
      String deckElixirImageKey,
      int[] hpPerLevel,
      int[] damagePerLevel,
      int hitSpeed) {
    super(elixirCost, deckElixirImageKey, hpPerLevel, damagePerLevel, hitSpeed);
  }
}
