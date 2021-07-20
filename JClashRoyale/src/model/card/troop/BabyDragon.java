package model.card.troop;

import javafx.scene.image.Image;
import model.Settings;
import model.card.*;
import util.Config;

public class BabyDragon extends Troop {

  private static final Image deckImage =
      new Image(Config.retrieveProperty("BABY_DRAGON_DECK_IMAGE"));

  public BabyDragon() {
    super(
        4,
        "BABY_DRAGON",
        new int[] {800, 880, 968, 1064, 1168},
        new int[] {100, 110, 121, 133, 146}, 1800);
    range = Range.RANGED;
    speed = Speed.FAST;
    movement = Movement.AIR;
    target = Target.ALL;
    rangeDistance = 3.0;
    count = 1;
  }

  public static Image getDeckImage() {
    return deckImage;
  }

  @Override
  public Card newInstance() {
    return new BabyDragon();
  }
}
