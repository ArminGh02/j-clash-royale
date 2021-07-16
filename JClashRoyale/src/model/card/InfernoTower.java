package model.card;

import javafx.scene.image.Image;
import util.Config;

public class InfernoTower extends Building {

  private static final Image infernoTowerDeckImage = new Image(
      Config.retrieveProperty("INFERNO_TOWER_DECK_IMAGE"));

  public InfernoTower() {
    super(5, "INFERNO_TOWER");
  }

  public static Image getDeckImage() {
    return infernoTowerDeckImage;
  }
}
