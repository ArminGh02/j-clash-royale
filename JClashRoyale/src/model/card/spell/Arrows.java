package model.card.spell;

import javafx.scene.image.Image;
import model.card.Card;
import model.card.Cards;
import util.Config;

public class Arrows extends DamagingSpell {

  private static final Image deckImage = new Image(
      Config.retrieveProperty("ARROWS_DECK_IMAGE"));

  public Arrows(int level) {
    super(level, 3, "ARROWS");
    radius = 4.0;
    areaDamage = 144;
  }

  public static Image getDeckImage() {
    return deckImage;
  }

  @Override
  public Card newInstance() {
    return new Arrows(level);
  }

  @Override
  public Cards asEnumMember() {
    return Cards.ARROWS;
  }
}
