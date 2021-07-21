package model.card.spell;

import javafx.scene.image.Image;
import model.Settings;
import model.card.Card;
import model.card.CardType;
import model.card.Cards;
import util.Config;

public class Rage extends Spell {
  private final long duration;
  private long startingTime; // in milliseconds

  private static final Image deckImage = new Image(Config.retrieveProperty("RAGE_DECK_IMAGE"));

  public Rage(int level) {
    super(level, 3, "RAGE", CardType.RAGE_SPELL);
    radius = 5.0;
    duration = 6000;
  }

  /**
   * startingTime setter
   * @param startingTime startingTime new value
   */
  public void setStartingTime(long startingTime) {
    this.startingTime = startingTime;
  }

  /**
   * duration getter
   * @return duration
   */
  public long getDuration() {
    return duration;
  }

  /**
   * startingTime getter
   * @return startingTime
   */
  public long getStartingTime() {
    return startingTime;
  }

  public static Image getDeckImage() {
    return deckImage;
  }

  @Override
  public Card newInstance() {
    return new Rage(level);
  }

  @Override
  public Cards asEnumMember() {
    return Cards.RAGE;
  }
}
