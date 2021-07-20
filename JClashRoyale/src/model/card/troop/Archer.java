package model.card.troop;

import javafx.scene.image.Image;
import model.card.Card;
import model.card.Cards;
import model.card.Movement;
import model.card.Range;
import model.card.Speed;
import model.Settings;
import model.card.*;
import util.Config;

public class Archer extends Troop {

  private static final Image deckImage = new Image(Config.retrieveProperty("ARCHER_DECK_IMAGE"));

  public Archer(int level) {
    super(level, 3, "ARCHER", new int[] {125, 127, 151, 166, 182}, new int[] {33, 44, 48, 53, 58}, 1200);
    range = Range.RANGED;
    speed = Speed.MEDIUM;
    target = Target.ALL;
    movement = Movement.GROUND;
    rangeDistance = 5.0;
    count = 2;
  }

  public static Image getDeckImage() {
    return deckImage;
  }

  @Override
  public Card newInstance() {
    return new Archer(level);
  }

  @Override
  public Cards asEnumMember() {
    return Cards.ARCHER;
  }
}
