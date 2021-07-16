package controller;

import java.util.List;
import java.util.Map;
import javafx.animation.AnimationTimer;
import javafx.scene.image.ImageView;

import java.util.ArrayList;
import java.util.HashMap;
import model.card.Building;
import model.card.Card;
import model.card.Spell;
import model.card.Troop;

/**
 * FrameController class, handles each frame's update
 * @author Adibov & Armin Gh
 * @version 1.0
 */
public class FrameController extends AnimationTimer {
  private MapViewController mapViewController;
  private List<Troop> activeTroops = new ArrayList<>();
  private List<Spell> activeSpells = new ArrayList<>();
  private List<Building> activeBuildings = new ArrayList<>();
  private Map<Card, ImageView> cardImage = new HashMap<>();

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
   */
  public void addToActiveCards(Card card) {
    switch (card.getType()) {
      case BUILDING:
        activeBuildings.add((Building) card);
        break;
      case TROOP:
        activeTroops.add((Troop) card);
        break;
      case SPELL:
        activeSpells.add((Spell) card);
        break;
    }
  }

  public void addImageOfCard(Card card, ImageView newImageView) {
    cardImage.put(card, newImageView);
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
