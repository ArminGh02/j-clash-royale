package controller;

import javafx.scene.image.ImageView;
import model.card.Building;
import model.card.Card;
import model.card.Spell;
import model.card.Troop;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * FrameController class, handles each frame's update
 * @author Adibov & Armin Gh
 * @version 1.0
 */
public class FrameController {
  private MapViewController mapViewController;
  private ArrayList<Troop> activeTroops = new ArrayList<>();
  private ArrayList<Spell> activeSpells = new ArrayList<>();
  private ArrayList<Building> activeBuildings = new ArrayList<>();
  private HashMap<Card, ImageView> cardImage = new HashMap<>();

  /**
   * class constructor
   * @param mapViewController mapViewController field value
   */
  public FrameController(MapViewController mapViewController) {
    this.mapViewController = mapViewController;
  }

  /**
   * add the given card to the list of active cards
   * @param card the given card
   * @param x x position of the new card
   * @param y y position of the new card
   */
  public void addCard(Card card, double x, double y) {
    if (card instanceof Troop)
      activeTroops.add((Troop) card);
    else if (card instanceof Spell)
      activeSpells.add((Spell) card);
    else
      activeBuildings.add((Building) card);
    ImageView newImageView = new ImageView(card.getImageKey());
    newImageView.setX(x);
    newImageView.setY(y);
    cardImage.put(card, newImageView);
    mapViewController.addImageView(newImageView);
  }
}
