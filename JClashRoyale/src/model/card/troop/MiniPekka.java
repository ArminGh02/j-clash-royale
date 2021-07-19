package model.card.troop;

import javafx.scene.image.Image;
import model.card.Card;
import model.card.Cards;
import model.card.Movement;
import model.card.Range;
import model.card.Speed;
import util.Config;

public class MiniPekka extends Troop {

  private static final Image deckImage =
      new Image(Config.retrieveProperty("MINI_PEKKA_DECK_IMAGE"));

  public MiniPekka(int level) {
    super(level,
        4, "MINI_PEKKA", new int[] {600, 660, 726, 798, 876}, new int[] {325, 357, 393, 432, 474},
        1500);
    range = Range.MELEE;
    speed = Speed.FAST;
    count = 1;
  }

  public static Image getDeckImage() {
    return deckImage;
  }

  /**
   * movement getter
   *
   * @return movement
   */
  @Override
  public Movement getMovement() {
    return Movement.GROUND;
  }

  @Override
  public Card newInstance() {
    return new MiniPekka(level);
  }

  @Override
  public Cards asEnumMember() {
    return Cards.MINI_PEKKA;
  }
}
