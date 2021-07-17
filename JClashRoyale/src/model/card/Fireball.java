package model.card;

import javafx.scene.image.Image;
import util.Config;

public class Fireball extends Spell {

  private static final Image deckImage = new Image(
      Config.retrieveProperty("FIREBALL_DECK_IMAGE"));

  public Fireball() {
    super(4, "FIREBALL");
  }

  public static Image getDeckImage() {
    return deckImage;
  }

  @Override
  public Card newInstance() {
    return new Fireball();
  }
}
