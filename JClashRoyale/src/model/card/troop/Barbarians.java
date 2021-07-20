package model.card.troop;

import javafx.scene.image.Image;
import model.card.Card;
import model.card.Cards;
import model.card.Movement;
import model.card.Range;
import model.card.Speed;
import model.card.*;
import util.Config;

public class Barbarians extends Troop {

  private static final Image deckImage =
      new Image(Config.retrieveProperty("BARBARIANS_DECK_IMAGE"));

  public Barbarians(int level) {
    super(
        level,
        5,
        "BARBARIANS",
        new int[] {300, 330, 363, 438, 480},
        new int[] {75, 82, 90, 99, 109},
        1500);
    range = Range.MELEE;
    target = Target.GROUND;
    movement = Movement.GROUND;
    count = 5;
    speed = Speed.MEDIUM;
  }

  public static Image getDeckImage() {
    return deckImage;
  }

  @Override
  public Card newInstance() {
    return new Barbarians(level);
  }

  @Override
  public Cards asEnumMember() {
    return Cards.BARBARIANS;
  }
}
