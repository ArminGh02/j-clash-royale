package model;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import model.card.Card;

/**
 * Deck class, handles deck usages
 *
 * @author Adibov & Armin Gh
 * @version 1.0
 */
public class Deck {
  private final List<Card> deck;

  private final List<Card> activeCards;
  private final Queue<Card> inactiveCards;

  public Deck(List<Card> deck) {
    this.deck = deck;
    activeCards = new ArrayList<>(deck.subList(0, 4));
    inactiveCards = new ArrayDeque<>(deck.subList(4, 8));
  }

  public Card nextCard(Card toDeploy) {
    Card nextCard = inactiveCards.remove();
    inactiveCards.add(toDeploy);
    activeCards.set(activeCards.indexOf(toDeploy), nextCard);
    return nextCard;
  }

  public List<Card> getActiveCards() {
    return activeCards;
  }
}
