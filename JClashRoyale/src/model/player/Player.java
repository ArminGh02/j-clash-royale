package model.player;

import java.util.List;
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
  protected Deck deck;

  public int getElixir() {
    return elixir;
  }

  public void increaseElixir() {
    this.elixir += Settings.ELIXIR_INCREASE;
  }

  /**
   * deploy chosen card for the given player if possible
   * @return chosen card
   */
  public boolean deploy(Card toDeploy) {
    int cardElixirCost = toDeploy.getElixirCost();
    if (elixir >= cardElixirCost) {
      elixir -= cardElixirCost;
      return true;
    }
    return false;
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

  public void setDeck(List<Card> deck) {
    this.deck = new Deck(deck);
  }
}
