package model.card.troop;

import javafx.scene.image.Image;
import model.card.*;
import util.Config;

public class Barbarians extends Troop {

  private static final Image deckImage =
      new Image(Config.retrieveProperty("BARBARIANS_DECK_IMAGE"));

  public Barbarians() {
    super(
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
    return new Barbarians();
  }
}
