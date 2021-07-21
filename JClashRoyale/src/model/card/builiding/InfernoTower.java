package model.card.builiding;

import javafx.scene.image.Image;
import model.Settings;
import model.card.Card;
import model.card.Cards;
import model.card.Movement;
import model.card.Range;
import model.card.Target;
import util.Config;

public class InfernoTower extends Building {

  private static final Image infernoTowerDeckImage =
      new Image(Config.retrieveProperty("INFERNO_TOWER_DECK_IMAGE"));

  public InfernoTower(int level) {
    super(
        level,
        5,
        "INFERNO_TOWER",
        new int[] {800, 880, 968, 1064, 1168},
        new int[] {200, 220, 242, 266, 292},
        400);
    range = Range.RANGED;
    movement = Movement.GROUND;
    target = Target.ALL;
    rangeDistance = 6.0;
    lifeTime = 40 * 1000;
  }

  public static Image getDeckImage() {
    return infernoTowerDeckImage;
  }

  @Override
  public Card newInstance() {
    return new InfernoTower(level);
  }

  @Override
  public Cards asEnumMember() {
    return Cards.INFERNO_TOWER;
  }
}
