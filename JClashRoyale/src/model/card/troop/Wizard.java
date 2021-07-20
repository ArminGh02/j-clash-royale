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

public class Wizard extends Troop {

  private static final Image deckImage = new Image(Config.retrieveProperty("WIZARD_DECK_IMAGE"));

  public Wizard(int level) {
    super(
        level,
        5,
        "WIZARD",
        new int[] {340, 374, 411, 452, 496},
        new int[] {130, 143, 157, 172, 189},
        1700);
    range = Range.RANGED;
    movement = Movement.GROUND;
    target = Target.ALL;
    speed = Speed.MEDIUM;
    rangeDistance = 5.0;
    count = 1;
  }

  public static Image getDeckImage() {
    return deckImage;
  }

  @Override
  public Card newInstance() {
    return new Wizard(level);
  }

  @Override
  public Cards asEnumMember() {
    return Cards.WIZARD;
  }
}
