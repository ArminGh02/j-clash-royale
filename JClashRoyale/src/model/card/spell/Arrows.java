package model.card.spell;

import javafx.scene.image.Image;
import model.card.Card;
import util.Config;

public class Arrows extends DamagingSpell {

  private static final Image deckImage = new Image(
      Config.retrieveProperty("ARROWS_DECK_IMAGE"));

  public Arrows() {
    super(3, "ARROWS");
    radius = 4.0;
    areaDamage = 144;
  }

  public static Image getDeckImage() {
    return deckImage;
  }

  @Override
  public Card newInstance() {
    return new Arrows();
  }
}
