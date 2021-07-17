package controller;

import javafx.animation.AnimationTimer;
import javafx.application.Platform;
import javafx.scene.image.Image;
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
 *
 * @author Adibov & Armin Gh
 * @version 1.0
 */
public class FrameController extends AnimationTimer {
  private MapViewController mapViewController;
  private List<Troop> activeTroops = new ArrayList<>();
  private List<Spell> activeSpells = new ArrayList<>();
  private List<Building> activeBuildings = new ArrayList<>();
  private Map<Card, ImageView> cardsImage = new HashMap<>();

  private KingTower friendlyKingTower;
  private PrinceTower friendlyPrinceTowerL, friendlyPrinceTowerR;
  private KingTower enemyKingTower;
  private PrinceTower enemyPrinceTowerL, enemyPrinceTowerR;

  /**
   * class constructor
   *
   * @param mapViewController mapViewController field value
   */
  public FrameController(MapViewController mapViewController) {
    this.mapViewController = mapViewController;
  }

  public void initialize() {
    addTowersToMap();
  }

  /** add tower images to map */
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

    ImageView friendlyKingTowerImage =
        new ImageView(Config.retrieveProperty("KING_TOWER_FRIENDLY"));
    ImageView friendlyPrinceTowerLImage =
        new ImageView(Config.retrieveProperty("PRINCE_TOWER_FRIENDLY"));
    ImageView friendlyPrinceTowerRImage =
        new ImageView(Config.retrieveProperty("PRINCE_TOWER_FRIENDLY"));
    ImageView enemyKingTowerImage = new ImageView(Config.retrieveProperty("KING_TOWER_ENEMY"));
    ImageView enemyPrinceTowerLImage = new ImageView(Config.retrieveProperty("PRINCE_TOWER_ENEMY"));
    ImageView enemyPrinceTowerRImage = new ImageView(Config.retrieveProperty("PRINCE_TOWER_ENEMY"));

    cardsImage.put(friendlyKingTower, friendlyKingTowerImage);
    cardsImage.put(friendlyPrinceTowerL, friendlyPrinceTowerLImage);
    cardsImage.put(friendlyPrinceTowerR, friendlyPrinceTowerRImage);
    cardsImage.put(enemyKingTower, enemyKingTowerImage);
    cardsImage.put(enemyPrinceTowerL, enemyPrinceTowerLImage);
    cardsImage.put(enemyPrinceTowerR, enemyPrinceTowerRImage);

    int middleColumn = Settings.MAP_COLUMN_COUNT / 2;
    mapViewController.addMapGrid(friendlyKingTowerImage, middleColumn, Settings.MAP_ROW_COUNT - 1);
    mapViewController.addMapGrid(friendlyPrinceTowerLImage, 1, Settings.MAP_ROW_COUNT - 3);
    mapViewController.addMapGrid(
        friendlyPrinceTowerRImage, Settings.MAP_COLUMN_COUNT - 2, Settings.MAP_ROW_COUNT - 3);
    mapViewController.addMapGrid(enemyKingTowerImage, middleColumn, 0);
    mapViewController.addMapGrid(enemyPrinceTowerLImage, 1, 1);
    mapViewController.addMapGrid(enemyPrinceTowerRImage, Settings.MAP_COLUMN_COUNT - 2, 1);
  }

  /**
   * add the given card to the list of active cards
   *
   * @param card the given card
   * @param x x position of the new card
   * @param y y position of the new card
   */
  public void addCardToMap(Card card, double x, double y) {
    if (card.getCardGroup().equals(CardGroups.TROOP)) activeTroops.add((Troop) card);
    else if (card.getCardGroup().equals(CardGroups.SPELL)) activeSpells.add((Spell) card);
    else activeBuildings.add((Building) card);
    ImageView newImageView = new ImageView(Config.retrieveProperty(card.getImageKey()));
    newImageView.setX(x);
    newImageView.setY(y);
    cardsImage.put(card, newImageView);
    mapViewController.addImageView(newImageView);
  }

  /** update hp of the active troops and building */
  private void updateHps() {}

  /** update spells' state */
  private void updateSpells() {}

  /** update troops and buildings' target */
  private void updateTargets() {
    for (Troop troop : activeTroops) updateTarget(troop);
    for (Building building : activeBuildings) updateTarget(building);
  }

  /**
   * update target for the given attackingCard
   *
   * @param attackingCard the given attackingCard
   */
  private void updateTarget(Attacker attackingCard) {
    double minimumDistance = 1000;
    for (Troop troop : activeTroops) {
      double distance = getDistance(attackingCard, troop);
      if (attackingCard.getTeamNumber() != troop.getTeamNumber() && distance < minimumDistance) {
        minimumDistance = distance;
        attackingCard.setCurrentTarget(troop);
      }
    }

    for (Building building : activeBuildings) {
      double distance = getDistance(attackingCard, building);
      if (building.getTeamNumber() != attackingCard.getTeamNumber() && distance < minimumDistance) {
        minimumDistance = distance;
        attackingCard.setCurrentTarget(building);
      }
    }

    if (getDistance(attackingCard, attackingCard.getCurrentTarget())
        <= attackingCard.getRangeDistance()) attackingCard.setAttacking(true);
    else attackingCard.setAttacking(false);
  }

  /** update troops' velocity */
  private void updateVelocities() {
    for (Troop troop : activeTroops) {
      updateVelocity(troop);
      updateImage(troop);
      double x = troop.getVelocity().getX(), y = troop.getVelocity().getY();
      double length = Math.sqrt(x * x + y * y);
      if (length > 0)
        troop.setVelocity(x / length, y / length);
    }
  }

  /**
   * update velocity for the given troop
   *
   * @param troop the given troop
   */
  private void updateVelocity(Troop troop) {
    if (troop.isAttacking() || troop.getCurrentTarget() == null) {
      troop.setVelocity(0.0, 0.0);
      return;
    }

    ImageView source = cardsImage.get(troop);
    ImageView destination = cardsImage.get(troop.getCurrentTarget());
    if (troop.getMovement().equals(MOVEMENT.AIR)
        || getRegionNumber(troop) == 0
        || getRegionNumber(troop.getCurrentTarget()) == 0
        || getRegionNumber(troop) == getRegionNumber(troop.getCurrentTarget())) { // straight line
      troop.setVelocity(destination.getX() - source.getX(), destination.getY() - source.getY());
      return;
    }

    double leftBridge =
        getEuclideanDistance(
                source.getX(), source.getY(), Settings.LEFT_BRIDGE_X, Settings.LEFT_BRIDGE_Y)
            + getEuclideanDistance(
                Settings.LEFT_BRIDGE_X,
                Settings.LEFT_BRIDGE_Y,
                destination.getX(),
                destination.getY());
    if (Math.abs(getDistance(troop, troop.getCurrentTarget()) - leftBridge) <= Settings.EPSILON)
      troop.setVelocity(Settings.LEFT_BRIDGE_X - source.getX(), Settings.LEFT_BRIDGE_Y - source.getY());
    else
      troop.setVelocity(Settings.RIGHT_BRIDGE_X - source.getX(), Settings.RIGHT_BRIDGE_Y - source.getY());
  }

  /**
   * update image for the given attacker
   * @param attacker the given attacker
   */
  private void updateImage(Attacker attacker) {
    ImageView attackerImage = cardsImage.get(attacker);
    String resultImageKey = attacker.getImageKey();
    double x = attacker.getVelocity().getX(), y = -attacker.getVelocity().getY();
    if (attacker.isAttacking()) {
      ImageView targetImage = cardsImage.get(attacker.getCurrentTarget());
      x = targetImage.getX() - attackerImage.getX();
      y = attackerImage.getY() - targetImage.getY();
    }

    if (y == 0) {
      if (x >= 0)
        resultImageKey += "_RIGHT";
      else
        resultImageKey += "_LEFT";
    }
    else {
      double slope = y / x;
      if (x >= 0) {
        if (Settings.UP_RIGHT_SLOPE <= slope)
          resultImageKey += "_UP";
        else if (Settings.RIGHT_UP_SLOPE <= slope && slope <= Settings.UP_RIGHT_SLOPE)
          resultImageKey += "_UP_RIGHT";
        else if (Settings.RIGHT_DOWN_SLOPE <= slope && slope <= Settings.RIGHT_UP_SLOPE)
          resultImageKey += "_RIGHT";
        else if (Settings.DOWN_RIGHT_SLOPE <= slope && slope <= Settings.RIGHT_DOWN_SLOPE)
          resultImageKey += "_DOWN_RIGHT";
        else
          resultImageKey += "_DOWN";
      }
      else {
        if (slope <= Settings.UP_LEFT_SLOPE)
          resultImageKey += "_UP";
        else if (Settings.UP_LEFT_SLOPE <= slope && slope <= Settings.LEFT_UP_SLOPE)
          resultImageKey += "_UP_LEFT";
        else if (Settings.LEFT_UP_SLOPE <= slope && slope <= Settings.LEFT_DOWN_SLOPE)
          resultImageKey += "_LEFT";
        else if (Settings.LEFT_DOWN_SLOPE <= slope && slope <= Settings.DOWN_LEFT_SLOPE)
          resultImageKey += "_DOWN_LEFT";
        else
          resultImageKey += "_DOWN";
      }
    }

    if (attacker.getCardGroup().equals(CardGroups.TROOP) && attacker.isAttacking())
      resultImageKey += "_ATTACKING";
    final String finalResultImageKey = resultImageKey;
    if (attacker.getCurrentImageKey() == null || !attacker.getCurrentImageKey().equals(resultImageKey)) {
      Platform.runLater(() -> attackerImage.setImage(new Image(Config.retrieveProperty(finalResultImageKey))));
      attacker.setCurrentImageKey(resultImageKey);
    }
  }

  /** move troops by their velocities */
  private void moveTroops() {
    for (Troop troop : activeTroops) {
      ImageView troopImage = cardsImage.get(troop);
      Platform.runLater(() -> troopImage.setX(troopImage.getX() + troop.getVelocity().getX()));
      Platform.runLater(() -> troopImage.setY(troopImage.getY() + troop.getVelocity().getY()));
    }
  }

  /**
   * get minimum distance between two given card
   *
   * @param source source card (attacker)
   * @param destination destination card
   * @return distance value
   */
  private double getDistance(Card source, Card destination) {
    int sourceRegion = getRegionNumber(source);
    int destinationRegion = getRegionNumber(destination);

    ImageView sourceImage = cardsImage.get(source);
    ImageView destinationImage = cardsImage.get(destination);
    if (sourceImage == null || destinationImage == null) return 100;

    boolean euclideanDistance = false;
    if (source.getCardGroup().equals(CardGroups.TROOP)) {
      Troop tempTroop = (Troop) source;
      euclideanDistance |= tempTroop.getMovement().equals(MOVEMENT.AIR);
    }
    if (sourceRegion == destinationRegion || sourceRegion == 0 || destinationRegion == 0)
      euclideanDistance = true;
    if (euclideanDistance)
      return getEuclideanDistance(
          sourceImage.getX(), sourceImage.getY(), destinationImage.getX(), destinationImage.getY());

    double firstPath =
        getEuclideanDistance(
                sourceImage.getX(),
                sourceImage.getY(),
                Settings.LEFT_BRIDGE_X,
                Settings.LEFT_BRIDGE_Y)
            + getEuclideanDistance(
                Settings.LEFT_BRIDGE_X,
                Settings.LEFT_BRIDGE_Y,
                destinationImage.getX(),
                destinationImage.getY());
    double secondPath =
        getEuclideanDistance(
                sourceImage.getX(),
                sourceImage.getY(),
                Settings.RIGHT_BRIDGE_X,
                Settings.RIGHT_BRIDGE_Y)
            + getEuclideanDistance(
                Settings.RIGHT_BRIDGE_X,
                Settings.RIGHT_BRIDGE_Y,
                destinationImage.getX(),
                destinationImage.getY());
    return Math.min(firstPath, secondPath);
  }

  /**
   * calculate the euclidean distance between two given points
   *
   * @param x1 x position of the first point
   * @param y1 y position of the first point
   * @param x2 x position of the second point
   * @param y2 y position of the second point
   * @return euclidean distance
   */
  private double getEuclideanDistance(double x1, double y1, double x2, double y2) {
    return Math.sqrt((x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2));
  }

  /**
   * calculate region number of the given card
   *
   * @param card the given card
   * @return 0 for bridges and 1 for friendly half and 2 for enemy's half
   */
  private int getRegionNumber(Card card) {
    ImageView cardImage = cardsImage.get(card);
    if (cardImage == null) return 3;

    int middleRow = Settings.MAP_ROW_COUNT / 2;
    int cellRow = (int) ((cardImage.getY() + Settings.CELL_HEIGHT_SHIFT) / Settings.CELL_HEIGHT);
    if (cellRow == middleRow) return 0;
    else if (cellRow > middleRow) return 1;
    else return 2;
  }

  /**
   * handle method will be called every frame
   *
   * @param currentNanoTime current time in nanoseconds
   */
  @Override
  public void handle(long currentNanoTime) {
    updateHps();
    updateSpells();
    updateTargets();
    updateVelocities();
    moveTroops();
  }
}
