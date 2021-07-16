package model.card;

import javafx.scene.image.Image;
import model.Settings;
import util.Config;

public class BabyDragon extends Troop {
  public BabyDragon() {
    super(4, "BABY_DRAGON_DECK_ELIXIR_IMAGE");
  }

  public static Image getDeckImage() {
    return new Image(Config.retrieveProperty("BABY_DRAGON_DECK_IMAGE"));
  }

  @Override
  public String getImageKey() {
    return "BABY_DRAGON";
  }

  /**
   * get euclidean range in double
   *
   * @return range distance
   */
  @Override
  public double getRangeDistance() {
    return Settings.BABY_DRAGON_ATTACK_RANGE;
  }
}
