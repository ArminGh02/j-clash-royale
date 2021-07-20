package model.player;

import controller.FrameController;
import controller.GameTimerController;
import controller.MapViewController;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import model.Deck;
import model.Settings;
import model.card.Card;
import model.card.Cards;
import model.card.builiding.Cannon;
import model.card.builiding.InfernoTower;
import model.card.spell.Arrows;
import model.card.spell.Fireball;
import model.card.spell.Rage;
import model.card.troop.Archer;
import model.card.troop.BabyDragon;
import model.card.troop.Barbarians;
import model.card.troop.Giant;
import model.card.troop.MiniPekka;
import model.card.troop.Valkyrie;
import model.card.troop.Wizard;

/**
 * Robot class, implements robots turn
 *
 * @author Adibov & Armin Gh
 * @version 1.0
 */
public abstract class Robot extends Player {
  protected static final Random rand = new Random();
  protected RobotType robotType;

  protected Deck deck;

  {
    deck = getRandomDeck();
  }

  private Deck getRandomDeck() {
    List<Integer> ordinalsOfCardsToAdd =
        new ArrayList<>(Arrays.asList(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11));
    Collections.shuffle(ordinalsOfCardsToAdd, rand);

    List<Card> deckCards = new ArrayList<>(Settings.DECK_SIZE);
    for (int i = 0; i < 8; i++) {
      int ordinal = ordinalsOfCardsToAdd.get(i);
      if (ordinal == Cards.ARCHER.ordinal()) {
        deckCards.add(new Archer(Settings.BOT_LEVEL));
      } else if (ordinal == Cards.ARROWS.ordinal()) {
        deckCards.add(new Arrows(Settings.BOT_LEVEL));
      } else if (ordinal == Cards.BABY_DRAGON.ordinal()) {
        deckCards.add(new BabyDragon(Settings.BOT_LEVEL));
      } else if (ordinal == Cards.BARBARIANS.ordinal()) {
        deckCards.add(new Barbarians(Settings.BOT_LEVEL));
      } else if (ordinal == Cards.CANNON.ordinal()) {
        deckCards.add(new Cannon(Settings.BOT_LEVEL));
      } else if (ordinal == Cards.FIREBALL.ordinal()) {
        deckCards.add(new Fireball(Settings.BOT_LEVEL));
      } else if (ordinal == Cards.GIANT.ordinal()) {
        deckCards.add(new Giant(Settings.BOT_LEVEL));
      } else if (ordinal == Cards.INFERNO_TOWER.ordinal()) {
        deckCards.add(new InfernoTower(Settings.BOT_LEVEL));
      } else if (ordinal == Cards.MINI_PEKKA.ordinal()) {
        deckCards.add(new MiniPekka(Settings.BOT_LEVEL));
      } else if (ordinal == Cards.RAGE.ordinal()) {
        deckCards.add(new Rage(Settings.BOT_LEVEL));
      } else if (ordinal == Cards.VALKYRIE.ordinal()) {
        deckCards.add(new Valkyrie(Settings.BOT_LEVEL));
      } else if (ordinal == Cards.WIZARD.ordinal()) {
        deckCards.add(new Wizard(Settings.BOT_LEVEL));
      }
    }
    return new Deck(deckCards);
  }

  /**
   * robotType getter
   * @return robotType
   */
  public RobotType getRobotType() {
    return robotType;
  }

  /**
   * get player group of the player
   *
   * @return PlayerGroup
   */
  @Override
  public PlayerGroup getPlayerGroup() {
    return PlayerGroup.ROBOT;
  }

  public abstract void play(
      GameTimerController timer, MapViewController mapViewController, FrameController gameLoop);
}
