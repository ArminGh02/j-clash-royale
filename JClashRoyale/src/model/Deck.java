package model;

import model.card.Card;

import java.util.ArrayList;

/**
 * Deck class, handles deck usages
 *
 * @author Adibov & Armin Gh
 * @version 1.0
 */
public class Deck {
  final private ArrayList<Card> activeCards = new ArrayList<>();
  final private ArrayList<Card> cards = new ArrayList<>();

  /**
   * add the given card to the list of cards
   * @param card given card
   */
  public void addCard(Card card) {
    cards.add(card);
  }

  /**
   * get a card and add it to active cards
   * @return result card
   */
  public Card getCard() {
    if (cards.size() == 0)
      return null;
    Card resultCard = cards.get(0);
    activeCards.add(resultCard);
    cards.remove(0);
    return resultCard;
  }

  /**
   * choose the given card from active cards and add it to the end of the cards queue
   * @param card chosen card
   */
  public void chooseCard(Card card) {
    if (!activeCards.contains(card))
      return;
    activeCards.remove(card);
    cards.add(card);
    Card newCard = cards.get(0);
    cards.remove(0);
    activeCards.add(newCard);
  }

  /**
   * check if the given card is active or not
   * @param card the given card
   * @return boolean result
   */
  public boolean isCardActive(Card card) {
    return activeCards.contains(card);
  }
}
