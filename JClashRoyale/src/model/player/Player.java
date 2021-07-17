package model.player;

import model.Deck;
import model.Settings;

import model.card.Card;

/**
 * player class, handles information/action for players
 *
 * @author Adibov & Armin Gh
 * @version 1.0
 */
public abstract class Player {
  protected int elixir = Settings.INITIAL_ELIXIR;
  protected Deck deck = new Deck();

  public int getElixir() {
    return elixir;
  }

  public void increaseElixir() {
    this.elixir += Settings.ELIXIR_INCREASE;
  }

  public void decreaseElixir(int decreaseAmount) {
    this.elixir -= decreaseAmount;
  }

  /**
   * deploy chosen card for the given player if possible
   * @return chosen card
   */
  public Card deployChosenCard() {
    if (!canDeployCard())
      return null;
    Card result = deck.getChosenCard();
    decreaseElixir(deck.getChosenCard().getElixirCost());
    deck.pickChosenCard();
    return result;
  }

  /**
   * check if the player can deploy chosen card
   *
   * @return boolean result
   */
  public boolean canDeployCard() {
    Card chosenCard = deck.getChosenCard();
    if (chosenCard == null) return false;
    return chosenCard.getElixirCost() <= elixir;
  }

  /**
   * deck getter
   * @return deck
   */
  public Deck getDeck() {
    return deck;
  }

  /**
   * get player group of the player
   * @return PlayerGroup
   */
  abstract public PlayerGroup getPlayerGroup();
}
