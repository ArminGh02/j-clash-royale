package model.card;

import javafx.scene.image.Image;
import util.Config;

public class Fireball extends Spell {
  public Fireball() {
    super(4, "FIREBALL_DECK_ELIXIR_IMAGE");
  }

  public static Image getDeckImage() {
    return new Image(Config.retrieveProperty("FIREBALL_DECK_IMAGE"));
  }

  @Override
  public String getImageKey() {
    return "FIREBALL";
  }
}
