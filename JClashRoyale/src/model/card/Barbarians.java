package model.card;

import javafx.scene.image.Image;
import util.Config;

public class Barbarians extends Troop {

  private static final Image deckImage = new Image(
      Config.retrieveProperty("BARBARIANS_DECK_IMAGE"));

  public Barbarians() {
    super(5, "BARBARIANS");
    range = Range.MELEE;
    hp = 300; // TODO make it dependent to level
    damage = 75;
    hitSpeed = 1500;
  }

  public static Image getDeckImage() {
    return deckImage;
  }

  /**
   * movement getter
   *
   * @return movement
   */
  @Override
  public Movement getMovement() {
    return Movement.GROUND;
  }

  @Override
  public Card newInstance() {
    return new Barbarians();
  }
}
