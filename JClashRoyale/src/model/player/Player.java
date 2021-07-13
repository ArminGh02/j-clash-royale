package model.player;

import model.Deck;
import model.Settings;

import javafx.scene.image.ImageView;
import model.card.Card;

/**
 * player class, handles information/action for players
 *
 * @author Adibov & Armin Gh
 * @version 1.0
 */
public abstract class Player {
  protected double elixirCount = Settings.INITIAL_ELIXIR;
  protected Deck deck = new Deck();

  public double getElixirCount() {
    return elixirCount;
  }

  public void increaseElixir() {
    this.elixirCount += Settings.ELIXIR_INCREASE;
  }

  public void decreaseElixir(int decreaseAmount) {
    this.elixirCount -= decreaseAmount;
  }

  /**
   * deploy chosen card for the given player if possible
   */
  public void deployChosenCard() {
    if (!canDeployCard())
      return;
    decreaseElixir(deck.getChosenCard().getElixirCost());
    deck.pickChosenCard();
  }

  /**
   * check if the player can deploy chosen card
   *
   * @return boolean result
   */
  public boolean canDeployCard() {
    Card chosenCard = deck.getChosenCard();
    if (chosenCard == null) return false;
    return chosenCard.getElixirCost() <= elixirCount;
  }
}
