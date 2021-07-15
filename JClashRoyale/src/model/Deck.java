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
  private Card chosenCard;

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
   * pick the chosen card from active cards and add it to the end of the cards queue
   */
  public void pickChosenCard() {
    if (chosenCard == null || !activeCards.contains(chosenCard))
      return;
    activeCards.remove(chosenCard);
    cards.add(chosenCard);
    chosenCard = null;
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

  /**
   * chosenCard getter
   * @return chosenCard
   */
  public Card getChosenCard() {
    return chosenCard;
  }

  /**
   * chosenCard setter
   * @param chosenCard new chosenCard
   */
  public void setChosenCard(Card chosenCard) {
    this.chosenCard = chosenCard;
  }

  /**
   * return the active card with the given index
   * @param index the given index
   * @return result card
   */
  public Card getActiveCard(int index) {
    if (index >= activeCards.size())
      return null;
    return activeCards.get(index);
  }

  /**
   * clear the current deck
   */
  public void clear() {
    cards.clear();
    activeCards.clear();
    chosenCard = null;
  }

  /**
   * return the number of total cards
   * @return total cards' count
   */
  public int size() {
    return cards.size() + activeCards.size();
  }
}
