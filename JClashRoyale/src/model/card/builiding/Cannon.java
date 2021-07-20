package model.card.builiding;

import javafx.scene.image.Image;
import model.Settings;
import model.card.Card;
import model.card.Cards;
import model.card.Movement;
import model.card.Range;
import model.card.Target;
import util.Config;

public class Cannon extends Building {

  private static final Image cannonDeckImage =
      new Image(Config.retrieveProperty("CANNON_DECK_IMAGE"));

  public Cannon(int level) {
    super(
        level,
        3,
        "CANNON_DECK_ELIXIR_IMAGE",
        new int[] {380, 418, 459, 505, 554},
        new int[] {60, 66, 72, 79, 87},
        800);
    range = Range.RANGED;
    movement = Movement.GROUND;
    target = Target.GROUND;
    rangeDistance = 5.5;
  }

  public static Image getDeckImage() {
    return cannonDeckImage;
  }

  @Override
  public Card newInstance() {
    return new Cannon(level);
  }

  @Override
  public Cards asEnumMember() {
    return Cards.CANNON;
  }
}
