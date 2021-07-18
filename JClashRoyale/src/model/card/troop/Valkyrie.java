package model.card.troop;

import javafx.scene.image.Image;
import model.card.Card;
import model.card.Movement;
import model.card.Range;
import model.card.Speed;
import util.Config;

public class Valkyrie extends Troop {

  private static final Image deckImage = new Image(Config.retrieveProperty("VALKYRIE_DECK_IMAGE"));

  public Valkyrie() {
    super(
        4,
        "VALKYRIE",
        new int[] {880, 968, 1064, 1170, 1284},
        new int[] {120, 132, 145, 159, 175},
        1500);
    range = Range.MELEE;
    speed = Speed.MEDIUM;
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
    return new Valkyrie();
  }
}
