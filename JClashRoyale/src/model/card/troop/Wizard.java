package model.card.troop;

import javafx.scene.image.Image;
import model.Settings;
import model.card.*;
import util.Config;

public class Wizard extends Troop {

  private static final Image deckImage = new Image(Config.retrieveProperty("WIZARD_DECK_IMAGE"));

  public Wizard() {
    super(
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
    return new Wizard();
  }
}
