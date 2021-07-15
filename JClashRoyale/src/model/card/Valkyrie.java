package model.card;

import javafx.scene.image.Image;
import util.Config;

public class Valkyrie extends Troop {
  public Valkyrie() {
    super(4, "VALKYRIE_DECK_ELIXIR_IMAGE");
  }

  public static Image getDeckImage() {
    return new Image(Config.retrieveProperty("VALKYRIE_DECK_IMAGE"));
  }

  @Override
  public String getImageKey() {
    return "VALKYRIE";
  }
}
