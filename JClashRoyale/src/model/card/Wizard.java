package model.card;

import javafx.scene.image.Image;
import util.Config;

public class Wizard extends Troop {
  public Wizard() {
    super(5, "WIZARD_DECK_ELIXIR_IMAGE");
  }

  public static Image getDeckImage() {
    return new Image(Config.retrieveProperty("WIZARD_DECK_IMAGE"));
  }
}
