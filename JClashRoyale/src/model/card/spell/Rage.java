package model.card.spell;

import javafx.scene.image.Image;
import model.card.Card;
import util.Config;

public class Rage extends Spell {
  private final long duration;

  private static final Image deckImage = new Image(Config.retrieveProperty("RAGE_DECK_IMAGE"));

  public Rage() {
    super(3, "RAGE");
    radius = 5.0;
    duration = 6000;
  }

  /**
   * duration getter
   * @return duration
   */
  public long getDuration() {
    return duration;
  }

  public static Image getDeckImage() {
    return deckImage;
  }

  @Override
  public Card newInstance() {
    return new Rage();
  }
}
