package model.card;

import javafx.scene.image.Image;
import util.Config;

public class Barbarians extends Troop {
  public Barbarians() {
    super(5, "BARBARIANS_DECK_ELIXIR_IMAGE");
  }

  public static Image getDeckImage() {
    return new Image(Config.retrieveProperty("BARBARIANS_DECK_IMAGE"));
  }

  @Override
  public String getImageKey() {
    return "BARBARIAN";
  }
}
