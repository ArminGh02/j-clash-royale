package model.card;

import javafx.scene.image.Image;
import util.Config;

public class Giant extends Troop {
  public Giant() {
    super(5, "GIANT_DECK_ELIXIR_IMAGE");
  }

  public static Image getDeckImage() {
    return new Image(Config.retrieveProperty("GIANT_DECK_IMAGE"));
  }
}
