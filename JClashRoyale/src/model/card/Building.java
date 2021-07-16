package model.card;

import model.Settings;

public abstract class Building extends Attacker {
  public Building(int elixirCost, String deckElixirImageKey) {
    super(elixirCost, deckElixirImageKey);
  }

  @Override
  public CardGroups getCardGroup() {
    return CardGroups.BUILDING;
  }
}
