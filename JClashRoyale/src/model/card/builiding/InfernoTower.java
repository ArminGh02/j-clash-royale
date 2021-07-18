package model.card.builiding;

import javafx.scene.image.Image;
import model.Settings;
import model.card.Card;
import model.card.Range;
import util.Config;

public class InfernoTower extends Building {

  private static final Image infernoTowerDeckImage =
      new Image(Config.retrieveProperty("INFERNO_TOWER_DECK_IMAGE"));

  public InfernoTower() {
    super(
        5,
        "INFERNO_TOWER",
        new int[] {800, 880, 968, 1064, 1168},
        new int[] {200, 220, 242, 266, 292},
        400);
    range = Range.RANGED;
    rangeDistance = 6.0;
  }

  public static Image getDeckImage() {
    return infernoTowerDeckImage;
  }

  @Override
  public Card newInstance() {
    return new InfernoTower();
  }
}
