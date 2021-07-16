package controller;

import javafx.animation.AnimationTimer;
import javafx.scene.image.ImageView;
import model.Settings;
import model.card.*;
import util.Config;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

  private KingTower friendlyKingTower;
  private PrinceTower friendlyPrinceTowerL, friendlyPrinceTowerR;
  private KingTower enemyKingTower;
  private PrinceTower enemyPrinceTowerL, enemyPrinceTowerR;

  /**
   * class constructor
   * @param mapViewController mapViewController field value
   */
  public FrameController(MapViewController mapViewController) {
    this.mapViewController = mapViewController;
  }

  public void initialize() {
    addTowersToMap();
  }

  /**
   * add tower images to map
   */
  private void addTowersToMap() {
    friendlyKingTower = new KingTower();
    friendlyPrinceTowerL = new PrinceTower();
    friendlyPrinceTowerR = new PrinceTower();
    enemyKingTower = new KingTower();
    enemyPrinceTowerL = new PrinceTower();
    enemyPrinceTowerR = new PrinceTower();

    friendlyKingTower.setTeamNumber(0);
    friendlyPrinceTowerL.setTeamNumber(0);
    friendlyPrinceTowerR.setTeamNumber(0);
    enemyKingTower.setTeamNumber(1);
    enemyPrinceTowerL.setTeamNumber(1);
    enemyPrinceTowerR.setTeamNumber(1);

    activeBuildings.add(friendlyKingTower);
    activeBuildings.add(friendlyPrinceTowerL);
    activeBuildings.add(friendlyPrinceTowerR);
    activeBuildings.add(enemyKingTower);
    activeBuildings.add(enemyPrinceTowerL);
    activeBuildings.add(enemyPrinceTowerR);

    ImageView friendlyKingTowerImage = new ImageView(Config.retrieveProperty("KING_TOWER_FRIENDLY"));
    ImageView friendlyPrinceTowerLImage = new ImageView(Config.retrieveProperty("PRINCE_TOWER_FRIENDLY"));
    ImageView friendlyPrinceTowerRImage = new ImageView(Config.retrieveProperty("PRINCE_TOWER_FRIENDLY"));
    ImageView enemyKingTowerImage = new ImageView(Config.retrieveProperty("KING_TOWER_ENEMY"));
    ImageView enemyPrinceTowerLImage = new ImageView(Config.retrieveProperty("PRINCE_TOWER_ENEMY"));
    ImageView enemyPrinceTowerRImage = new ImageView(Config.retrieveProperty("PRINCE_TOWER_ENEMY"));

    cardImage.put(friendlyKingTower, friendlyKingTowerImage);
    cardImage.put(friendlyPrinceTowerL, friendlyPrinceTowerLImage);
    cardImage.put(friendlyPrinceTowerR, friendlyPrinceTowerRImage);
    cardImage.put(enemyKingTower, enemyKingTowerImage);
    cardImage.put(enemyPrinceTowerL, enemyPrinceTowerLImage);
    cardImage.put(enemyPrinceTowerR, enemyPrinceTowerRImage);

    int middleColumn = Settings.MAP_COLUMN_COUNT / 2;
    mapViewController.addMapGrid(friendlyKingTowerImage, middleColumn, Settings.MAP_ROW_COUNT - 1);
    mapViewController.addMapGrid(friendlyPrinceTowerLImage, 1, Settings.MAP_ROW_COUNT - 3);
    mapViewController.addMapGrid(friendlyPrinceTowerRImage, Settings.MAP_COLUMN_COUNT - 2, Settings.MAP_ROW_COUNT - 3);
    mapViewController.addMapGrid(enemyKingTowerImage, middleColumn, 0);
    mapViewController.addMapGrid(enemyPrinceTowerLImage, 1, 1);
    mapViewController.addMapGrid(enemyPrinceTowerRImage, Settings.MAP_COLUMN_COUNT - 2, 1);
  }

  /**
   * add the given card to the list of active cards
   * @param card the given card
   * @param x x position of the new card
   * @param y y position of the new card
   */
  public void addCardToMap(Card card, double x, double y) {
    if (card.getCardGroup().equals(CardGroups.TROOP))
      activeTroops.add((Troop) card);
    else if (card.getCardGroup().equals(CardGroups.SPELL))
      activeSpells.add((Spell) card);
    else
      activeBuildings.add((Building) card);
    ImageView newImageView = new ImageView(Config.retrieveProperty(card.getImageKey()));
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
