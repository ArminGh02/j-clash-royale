package model.card.troop;

import javafx.scene.image.Image;
import model.card.Card;
import model.card.Cards;
import model.card.Movement;
import model.card.Range;
import model.card.Speed;
import model.card.*;
import util.Config;

public class Valkyrie extends Troop {

  private static final Image deckImage = new Image(Config.retrieveProperty("VALKYRIE_DECK_IMAGE"));

  public Valkyrie(int level) {
    super(level, 4,
        "VALKYRIE",
        new int[] {880, 968, 1064, 1170, 1284},
        new int[] {120, 132, 145, 159, 175},
        1500);
    range = Range.MELEE;
    movement = Movement.GROUND;
    target = Target.GROUND;
    speed = Speed.MEDIUM;
    count = 1;
    isAreaSplash = true;
  }

  public static Image getDeckImage() {
    return deckImage;
  }

  @Override
  public Card newInstance() {
    return new Valkyrie(level);
  }

  @Override
  public Cards asEnumMember() {
    return Cards.VALKYRIE;
  }
}
