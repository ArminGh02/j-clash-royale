package controller;

import java.util.*;

import javafx.animation.AnimationTimer;
import javafx.application.Platform;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import model.Settings;
import model.card.*;
import model.card.builiding.KingTower;
import model.card.builiding.PrinceTower;
import model.card.spell.DamagingSpell;
import model.card.spell.Rage;
import util.Config;
import model.card.builiding.Building;
import model.card.Card;
import model.card.spell.Spell;
import model.card.troop.Troop;

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
  private long currentMilliSecond;

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
   */
  public void addToActiveCards(Card card) {
    switch (card.getType()) {
      case BUILDING:
        activeBuildings.add((Building) card);
        break;
      case TROOP:
        activeTroops.add((Troop) card);
        break;
      default:
        activeSpells.add((Spell) card);
        break;
    }
  }

  /**
   * remove the given card from the list of active cards
   * @param card the given card
   */
  private void removeImageCard(Card card) {
    ImageView cardImage = cardsImage.get(card);
    if (cardImage == null)
      return;
    cardsImage.remove(card);
    mapViewController.deleteNode(cardImage);
  }

  public void addImageOfCard(Card card, ImageView newImageView) {
    cardsImage.put(card, newImageView);
  }

  /** update hp of the active troops and building */
  private void updateHps() {
    for (Troop troop : activeTroops)
      updateHp(troop);
    for (Building building : activeBuildings)
      updateHp(building);


    for (Iterator<Troop> troopIterator = activeTroops.iterator(); troopIterator.hasNext(); ) {
      Troop troop = troopIterator.next();
      if (troop.getHp() <= 0) {
        troopIterator.remove();
        removeImageCard(troop);
      }
    }
    for (Iterator<Building> buildingIterator = activeBuildings.iterator(); buildingIterator.hasNext();) {
      Building building = buildingIterator.next();
      if (building.getHp() <= 0) {
        buildingIterator.remove();
        removeImageCard(building);
      }
    }
  }

  /**
   * up hp for the given attacker
   * @param attacker the give attacker
   */
  private void updateHp(Attacker attacker) {
    Attacker target = (Attacker) attacker.getCurrentTarget();
    if (target == null || !attacker.isAttacking())
      return;
    if (currentMilliSecond - attacker.getLastAttackTime() >= attacker.getHitSpeed()) {
      target.decreaseHp(attacker.getDamage());
      attacker.setLastAttackTime(currentMilliSecond);
    }
  }

  /** update spells' state */
  private void applySpells() {
    for (Iterator<Spell> spellIterator = activeSpells.iterator(); spellIterator.hasNext();) {
      Spell spell = spellIterator.next();
      if (applySpell(spell)) {
        spellIterator.remove();
        removeImageCard(spell);
      }
    }
  }

  /**
   * apply the given spell
   * @param spell the given spell
   * @return true, if the spell must be removed from the list of active spells
   */
  private boolean applySpell(Spell spell) {
    if (spell.getType().equals(CardType.DAMAGING_SPELL)) {
      DamagingSpell damagingSpell = (DamagingSpell) spell;
      for (Troop troop : activeTroops)
        if (isInRange(spell, troop) && troop.getTeamNumber() == spell.getTeamNumber())
          troop.decreaseHp(damagingSpell.getAreaDamage());
      for (Building building : activeBuildings)
        if (isInRange(spell, building) && building.getTeamNumber() == spell.getTeamNumber())
          building.decreaseHp(damagingSpell.getAreaDamage());
      return true;
    }

    Rage rageSpell = (Rage) spell;
    if (rageSpell.getStartingTime() == 0)
      rageSpell.setStartingTime(currentMilliSecond);
    for (Troop troop : activeTroops)
      if (isInRange(rageSpell, troop) && troop.getTeamNumber() == spell.getTeamNumber())
        troop.setAttributeMultiplier(troop.getAttributeMultiplier() * Settings.RAGE_SPELL_COEFFICIENT);
    for (Building building : activeBuildings)
      if (isInRange(rageSpell, building) && building.getTeamNumber() == spell.getTeamNumber())
        building.setAttributeMultiplier(building.getAttributeMultiplier() * Settings.RAGE_SPELL_COEFFICIENT);
    return false;
  }

  /**
   * check if the given attackerCard in the spell's range
   * @param spell the given spell
   * @param attackerCard the given attackerCard
   * @return boolean result
   */
  private boolean isInRange(Spell spell, Attacker attackerCard) {
    ImageView attackerImage = cardsImage.get(attackerCard);
    if (attackerImage == null)
      return false;
    return getEuclideanDistance(spell.getXDeployment(), spell.getYDeployment(), attackerImage.getX(), attackerImage.getY()) <= spell.getRadius();
  }

  /**
   * unapply all active spells
   */
  private void unapplySpells() {
    for (Iterator<Spell> spellIterator = activeSpells.iterator(); spellIterator.hasNext();) {
      Spell spell = spellIterator.next();
      if (unapplySpell(spell)) {
        spellIterator.remove();
        removeImageCard(spell);
      }
    }
  }

  /**
   * unapply the given spell
   * @param spell the given spell
   * @return true, if the given spell must be removed from the list of active spells
   */
  private boolean unapplySpell(Spell spell) {
    if (spell.getType().equals(CardType.DAMAGING_SPELL))
      return false;

    for (Troop troop : activeTroops)
      if (isInRange(spell, troop) && troop.getTeamNumber() == spell.getTeamNumber()) // it is guaranteed that the troop is still in the range of spell
        troop.setAttributeMultiplier(troop.getAttributeMultiplier() / Settings.RAGE_SPELL_COEFFICIENT);
    for (Building building : activeBuildings)
      if (isInRange(spell, building) && building.getTeamNumber() == spell.getTeamNumber())
        building.setAttributeMultiplier(building.getAttributeMultiplier() / Settings.RAGE_SPELL_COEFFICIENT);

    Rage rageSpell = (Rage) spell;
    return (currentMilliSecond - rageSpell.getStartingTime() > rageSpell.getDuration());
  }

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
      if (length > 0) {
        troop.setVelocity(x / length, y / length); // make it a unit vector
        x = troop.getVelocity().getX();
        y = troop.getVelocity().getY();
        troop.setVelocity(x * troop.getSpeed(), y * troop.getSpeed());
      }
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
    if (troop.getMovement().equals(Movement.AIR)
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
      troop.setVelocity(
          Settings.RIGHT_BRIDGE_X - source.getX(), Settings.RIGHT_BRIDGE_Y - source.getY());
  }

  /**
   * update image for the given attacker
   * @param attacker the given attacker
   */
  private void updateImage(Attacker attacker) {
    ImageView attackerImage = cardsImage.get(attacker);
    if (attackerImage == null)
      return;

    String resultImageKey = attacker.getImageKey();
    double x = attacker.getVelocity().getX(), y = -attacker.getVelocity().getY();
    if (attacker.isAttacking()) {
      ImageView targetImage = cardsImage.get(attacker.getCurrentTarget());
      if (targetImage != null) {
        x = targetImage.getX() - attackerImage.getX();
        y = attackerImage.getY() - targetImage.getY();
      }
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

    if (attacker.getType().equals(CardType.TROOP) && attacker.isAttacking())
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
    if (source.getType().equals(CardType.TROOP)) {
      Troop tempTroop = (Troop) source;
      euclideanDistance |= tempTroop.getMovement().equals(Movement.AIR);
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
    currentMilliSecond = currentNanoTime / 1000000;
    applySpells();
    updateTargets();
    updateHps();
    updateVelocities();
    unapplySpells();
    moveTroops();
  }
}
