package model.card.spell;

import javafx.scene.image.Image;
import model.card.Card;
import util.Config;

public class Fireball extends DamagingSpell {

  private static final Image deckImage = new Image(
      Config.retrieveProperty("FIREBALL_DECK_IMAGE"));

  public Fireball() {
    super(4, "FIREBALL");
    radius = 2.5;
    areaDamage = 325;
  }

  public static Image getDeckImage() {
    return deckImage;
  }

  @Override
  public Card newInstance() {
    return new Fireball();
  }
}
