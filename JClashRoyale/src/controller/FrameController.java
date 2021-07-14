package controller;

import javafx.animation.AnimationTimer;
import javafx.scene.image.ImageView;
import model.card.*;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * FrameController class, handles each frame's update
 * @author Adibov & Armin Gh
 * @version 1.0
 */
public class FrameController extends AnimationTimer {
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
    if (card.getCardGroup().equals(CardGroups.TROOP))
      activeTroops.add((Troop) card);
    else if (card.getCardGroup().equals(CardGroups.SPELL))
      activeSpells.add((Spell) card);
    else
      activeBuildings.add((Building) card);
    ImageView newImageView = new ImageView(card.getImageKey());
    newImageView.setX(x);
    newImageView.setY(y);
    cardImage.put(card, newImageView);
    mapViewController.addImageView(newImageView);
  }

  /**
   * update hp of the active troops and building
   */
  private void updateHp() {

  }

  /**
   * update spells' state
   */
  private void updateSpells() {

  }

  /**
   * update troops and buildings' target
   */
  private void updateTargets() {
    for (Troop troop : activeTroops)
      updateTroopTarget(troop);
    for (Building building : activeBuildings)
      updateBuildingTarget(building);
  }

  /**
   * update target for the given troop
   * @param troop the given troop
   */
  private void updateTroopTarget(Troop troop) {

  }

  /**
   * update target for the given building
   * @param building the given building
   */
  private void updateBuildingTarget(Building building) {

  }

  /**
   * handle method will be called every frame
   * @param currentNanoTime current time in nanoseconds
   */
  @Override
  public void handle(long currentNanoTime) {
    updateHp();
    updateSpells();
    updateTargets();
  }
}
