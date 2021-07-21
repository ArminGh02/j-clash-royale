package controller;

import java.util.*;

import javafx.animation.AnimationTimer;
import javafx.application.Platform;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import model.GameResult;
import model.Settings;
import model.card.*;
import model.card.builiding.KingTower;
import model.card.builiding.PrinceTower;
import model.card.spell.DamagingSpell;
import model.card.spell.Rage;
import model.player.Person;
import model.player.Robot;
import model.player.RobotType;
import util.Config;
import model.card.builiding.Building;
import model.card.Card;
import model.card.spell.Spell;
import model.card.troop.Troop;
import view.ViewManager;

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
  private long lastNotificationTime;

  private KingTower friendlyKingTower;
  private PrinceTower friendlyPrinceTowerL, friendlyPrinceTowerR;
  private KingTower enemyKingTower;
  private PrinceTower enemyPrinceTowerL, enemyPrinceTowerR;

  private ImageView friendlyKingTowerImage;
  private ImageView friendlyPrinceTowerLImage;
  private ImageView friendlyPrinceTowerRImage;
  private ImageView enemyKingTowerImage;
  private ImageView enemyPrinceTowerLImage;
  private ImageView enemyPrinceTowerRImage;

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
    int personLevel = SoloGameController.getInstance().getPersonPlayer().getLevel();

    friendlyKingTower = new KingTower(personLevel);
    friendlyPrinceTowerL = new PrinceTower(personLevel);
    friendlyPrinceTowerR = new PrinceTower(personLevel);
    enemyKingTower = new KingTower(Settings.BOT_LEVEL);
    enemyPrinceTowerL = new PrinceTower(Settings.BOT_LEVEL);
    enemyPrinceTowerR = new PrinceTower(Settings.BOT_LEVEL);

    friendlyKingTower.setTeamNumber(0);
    friendlyPrinceTowerL.setTeamNumber(0);
    friendlyPrinceTowerR.setTeamNumber(0);
    enemyKingTower.setTeamNumber(1);
    enemyPrinceTowerL.setTeamNumber(1);
    enemyPrinceTowerR.setTeamNumber(1);

    friendlyKingTower.setDeploymentTime(System.currentTimeMillis());
    friendlyPrinceTowerL.setDeploymentTime(System.currentTimeMillis());
    friendlyPrinceTowerR.setDeploymentTime(System.currentTimeMillis());
    enemyKingTower.setDeploymentTime(System.currentTimeMillis());
    enemyPrinceTowerL.setDeploymentTime(System.currentTimeMillis());
    enemyPrinceTowerR.setDeploymentTime(System.currentTimeMillis());

    activeBuildings.add(friendlyKingTower);
    activeBuildings.add(friendlyPrinceTowerL);
    activeBuildings.add(friendlyPrinceTowerR);
    activeBuildings.add(enemyKingTower);
    activeBuildings.add(enemyPrinceTowerL);
    activeBuildings.add(enemyPrinceTowerR);

    friendlyKingTowerImage = new ImageView(Config.retrieveProperty("KING_TOWER_FRIENDLY"));
    friendlyPrinceTowerLImage = new ImageView(Config.retrieveProperty("PRINCE_TOWER_FRIENDLY"));
    friendlyPrinceTowerRImage = new ImageView(Config.retrieveProperty("PRINCE_TOWER_FRIENDLY"));
    enemyKingTowerImage = new ImageView(Config.retrieveProperty("KING_TOWER_ENEMY"));
    enemyPrinceTowerLImage = new ImageView(Config.retrieveProperty("PRINCE_TOWER_ENEMY"));
    enemyPrinceTowerRImage = new ImageView(Config.retrieveProperty("PRINCE_TOWER_ENEMY"));

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
        ((Building) card).setDeploymentTime(System.currentTimeMillis());
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
   * get x position of the given attacker's image
   * @param attacker the given attacker
   * @return x position
   */
  private double getX(Attacker attacker) {
    double width = Settings.CELL_WIDTH_SHIFT;
    ImageView attackerImage = cardsImage.get(attacker);
    if (attackerImage == null)
      return Settings.INF;
    if (attackerImage.getImage() != null)
      width = attackerImage.getImage().getWidth() / 2.0;
    return attackerImage.getX() + width;
  }

  /**
   * get y position of the given attacker's image
   * @param attacker the given attacker
   * @return y position
   */
  private double getY(Attacker attacker) {
    double height = Settings.CELL_HEIGHT_SHIFT;
    ImageView attackerImage = cardsImage.get(attacker);
    if (attackerImage == null)
      return Settings.INF;
    if (attackerImage.getImage() != null)
      height = attackerImage.getImage().getHeight() / 2.0;
    if (attacker.getTeamNumber() == 0)
      return attackerImage.getY();
    return attackerImage.getY() + height;
  }

  /**
   * check if card can be deployed on the given position
   * @param x x of the given position
   * @param y y of the given position
   * @param isBot is player a bot or not
   * @return boolean result
   */
  private boolean canDeployCard(double x, double y, boolean isBot) {
    if (getRegionNumber(x, y) == 0)
      return false;

    boolean isInLeftSide = (x <= Settings.LEFT_VBOX_WIDTH + Settings.MAP_COLUMN_COUNT * Settings.CELL_WIDTH / 2.0);
    if (isBot) {
      boolean isLeftTowerDestroyed = friendlyPrinceTowerL.getHp() <= 0;
      boolean isRightTowerDestroyed = friendlyPrinceTowerR.getHp() <= 0;
      if (y >= (Settings.MAP_ROW_COUNT - 2) * Settings.CELL_HEIGHT)
        return false;
      if (isInLeftSide)
        return (isLeftTowerDestroyed || getRegionNumber(x, y) == 2);
      return (isRightTowerDestroyed || getRegionNumber(x, y) == 2);
    }

    boolean isLeftTowerDestroyed = enemyPrinceTowerL.getHp() <= 0;
    boolean isRightTowerDestroyed = enemyPrinceTowerR.getHp() <= 0;
    if (y <= 2 * Settings.CELL_HEIGHT)
      return false;
    if (isInLeftSide)
      return (isLeftTowerDestroyed || getRegionNumber(x, y) == 1);
    return (isRightTowerDestroyed || getRegionNumber(x, y) == 1);
  }

  /**
   * check whether the given card can be deployed in the given position
   * @param card the given card
   * @param x x of the position
   * @param y y of the position
   * @param isBot whether the deployer is bot or not
   * @return the boolean result
   */
  public boolean canDeployCard(Card card, double x, double y, boolean isBot) {
    if (card.getType() == CardType.DAMAGING_SPELL || card.getType() == CardType.RAGE_SPELL)
      return true;
    return canDeployCard(x, y, isBot);
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
      if (building.getHp() <= 0 || System.currentTimeMillis() - building.getDeploymentTime() >= building.getLifeTime()) {
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
    Attacker target = attacker.getCurrentTarget();
    if (target == null || !attacker.isAttacking())
      return;
    if (currentMilliSecond - attacker.getLastAttackTime() >= attacker.getHitSpeed()) {
      if (attacker.isAreaSplash()) {
        double areaSplashDistance = Settings.AREA_SPLASH_RANGE * Settings.CELL_WIDTH / Settings.MAP_SCALE;
        for (Troop troop : activeTroops)
          if (getEuclideanDistance(attacker, troop) <= areaSplashDistance && isTargetValid(attacker, troop) && attacker.getTeamNumber() != troop.getTeamNumber())
            troop.decreaseHp(attacker.getDamage());
        for (Building building : activeBuildings)
          if (getEuclideanDistance(attacker, building) <= areaSplashDistance && isTargetValid(attacker, building) && attacker.getTeamNumber() != building.getTeamNumber())
            building.decreaseHp(attacker.getDamage());
      }
      else
        target.decreaseHp(attacker.getDamage());
      System.out.println(attacker.getImageKey() + ": " + attacker.getCurrentTarget().getImageKey() + " " + getDistance(attacker, attacker.getCurrentTarget()) + " " +
              attacker.isAttacking());
      attacker.setLastAttackTime(currentMilliSecond);
    }
  }

  /**
   * check if the towers has been destroyed
   */
  private void checkTowers() {
    if (friendlyPrinceTowerL.getHp() <= 0)
      Platform.runLater(() -> friendlyPrinceTowerLImage.setImage(new Image(Config.retrieveProperty("DESTROYED_TOWER"))));
    if (friendlyPrinceTowerR.getHp() <= 0)
      Platform.runLater(() -> friendlyPrinceTowerRImage.setImage(new Image(Config.retrieveProperty("DESTROYED_TOWER"))));
    if (enemyPrinceTowerL.getHp() <= 0)
      Platform.runLater(() -> enemyPrinceTowerLImage.setImage(new Image(Config.retrieveProperty("DESTROYED_TOWER"))));
    if (enemyPrinceTowerR.getHp() <= 0)
      Platform.runLater(() -> enemyPrinceTowerRImage.setImage(new Image(Config.retrieveProperty("DESTROYED_TOWER"))));
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
        if (isInRange(spell, troop) && troop.getTeamNumber() != spell.getTeamNumber())
          troop.decreaseHp(damagingSpell.getAreaDamage());
      for (Building building : activeBuildings)
        if (isInRange(spell, building) && building.getTeamNumber() != spell.getTeamNumber())
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
    double minimumDistance = Settings.INF;
    for (Troop troop : activeTroops) {
      double distance = getDistance(attackingCard, troop);
      if (attackingCard.getTeamNumber() != troop.getTeamNumber() && distance < minimumDistance && isTargetValid(attackingCard, troop)) {
        minimumDistance = distance;
        attackingCard.setCurrentTarget(troop);
      }
    }

    for (Building building : activeBuildings) {
      double distance = getDistance(attackingCard, building);
      if (building.getTeamNumber() != attackingCard.getTeamNumber() && distance < minimumDistance && isTargetValid(attackingCard, building)) {
        minimumDistance = distance;
        attackingCard.setCurrentTarget(building);
      }
    }

    if (getDistance(attackingCard, attackingCard.getCurrentTarget())
            <= attackingCard.getRangeDistance()) attackingCard.setAttacking(true);
    else attackingCard.setAttacking(false);

//    if (getRegionNumber(attackingCard) == 0 && !attackingCard.isAttacking()) {
//      if (attackingCard.getTeamNumber() == 0)
//        attackingCard.setCurrentTarget(enemyKingTower);
//      else
//        attackingCard.setCurrentTarget(friendlyKingTower);
//      attackingCard.setAttacking(false);
//    }
  }

  /**
   * check whether if the given attacker can attack the given target or not
   * @param attacker the given attacker
   * @param target his target
   * @return boolean result
   */
  private boolean isTargetValid(Attacker attacker, Attacker target) {
    switch (attacker.getTarget()) {
      case BUILDING:
        return target.getType() == CardType.BUILDING;
      case GROUND:
        return target.getMovement() == Movement.GROUND;
      case AIR:
        return target.getMovement() == Movement.AIR;
      default:
        return true;
    }
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

    for (Building building : activeBuildings) {
      if (building.isTower())
        continue;
      updateVelocity(building);
      updateImage(building);
    }
  }

  /**
   * update velocity for the given attacker
   *
   * @param attacker the given attacker
   */
  private void updateVelocity(Attacker attacker) {
    if (attacker.isAttacking() || attacker.getCurrentTarget() == null) {
      attacker.setVelocity(0.0, 0.0);
      return;
    }

    boolean hasCrossedBridge = (attacker.getTeamNumber() == 0 && Settings.LEFT_BRIDGE_Y - getY(attacker) >= 0) ||
            (attacker.getTeamNumber() == 1 && getY(attacker) - Settings.LEFT_BRIDGE_Y >= 0);
    if (attacker.getMovement() == Movement.AIR
        || getDistance(attacker, attacker.getCurrentTarget()) <= Settings.CELL_DIAGONAL_SHIFT
        || (getRegionNumber(attacker) == getRegionNumber(attacker.getCurrentTarget()) && getRegionNumber(attacker) != 0)
        || hasCrossedBridge
        || attacker.getType() == CardType.BUILDING
    ) { // straight line
      attacker.setVelocity(getX(attacker.getCurrentTarget()) - getX(attacker), getY(attacker.getCurrentTarget()) - getY(attacker));
      return;
    }

    double leftBridge = getEuclideanDistance(getX(attacker), getY(attacker), Settings.LEFT_BRIDGE_X, Settings.LEFT_BRIDGE_Y)
            + getEuclideanDistance(
                Settings.LEFT_BRIDGE_X,
                Settings.LEFT_BRIDGE_Y,
                getX(attacker.getCurrentTarget()),
                getY(attacker.getCurrentTarget()));

    if (Math.abs(getDistance(attacker, attacker.getCurrentTarget()) - leftBridge) <= Settings.EPSILON)
      attacker.setVelocity(Settings.LEFT_BRIDGE_X - getX(attacker), Settings.LEFT_BRIDGE_Y - getY(attacker));
    else
      attacker.setVelocity(Settings.RIGHT_BRIDGE_X - getX(attacker), Settings.RIGHT_BRIDGE_Y - getY(attacker));
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
   * check if game is finished or not
   */
  private void checkGameEnding() {
    boolean isEnded = false;
    if (SoloGameController.getInstance().getTimer().isEnded() || friendlyKingTower.getHp() <= 0 || enemyKingTower.getHp() <= 0)
      isEnded = true;
    if (!isEnded)
      return;
    stop();

    Person person = SoloGameController.getInstance().getPersonPlayer();
    int personCrownCount = (enemyPrinceTowerL.getHp() <= 0? 1: 0) + (enemyPrinceTowerR.getHp() <= 0? 1: 0);
    int enemyCrownCount = (friendlyPrinceTowerL.getHp() <= 0? 1: 0) + (friendlyPrinceTowerR.getHp() <= 0? 1: 0);
    if (friendlyKingTower.getHp() > enemyKingTower.getHp())
      person.increasePoints(Settings.WINNING_POINT);
    else
      person.increasePoints(Settings.LOOSING_POINT);
    if (enemyKingTower.getHp() <= 0)
      personCrownCount = 3;
    if (friendlyKingTower.getHp() <= 0)
      enemyCrownCount = 3;

    Robot enemy = SoloGameController.getInstance().getRobotPlayer();
    String enemyUsername = (enemy.getRobotType() == RobotType.BEGINNER_BOT? "Beginner Bot": "Advanced Bot");
    person.addGameResult(new GameResult(person.getUsername(), enemyUsername, personCrownCount, enemyCrownCount));
    ViewManager.loadMainMenuView();
  }

  /**
   * get minimum distance between two given card
   *
   * @param source source card (attacker)
   * @param destination destination card
   * @return distance value
   */
  private double getDistance(Attacker source, Attacker destination) {
    int sourceRegion = getRegionNumber(source);
    int destinationRegion = getRegionNumber(destination);

    ImageView sourceImage = cardsImage.get(source);
    ImageView destinationImage = cardsImage.get(destination);
    if (sourceImage == null || destinationImage == null) return Settings.INF;

    if (source.getMovement() == Movement.AIR || sourceRegion == destinationRegion && sourceRegion != 0 || getEuclideanDistance(source, destination) <= Settings.CELL_DIAGONAL_SHIFT)
      return getEuclideanDistance(getX(source), getY(source), getX(destination), getY(destination));

    double firstPath =
        getEuclideanDistance(
                getX(source),
                getY(source),
                Settings.LEFT_BRIDGE_X,
                Settings.LEFT_BRIDGE_Y)
            + getEuclideanDistance(
                Settings.LEFT_BRIDGE_X,
                Settings.LEFT_BRIDGE_Y,
                getX(destination),
                getY(destination));
    double secondPath =
        getEuclideanDistance(
                getX(source),
                getY(source),
                Settings.RIGHT_BRIDGE_X,
                Settings.RIGHT_BRIDGE_Y)
            + getEuclideanDistance(
                Settings.RIGHT_BRIDGE_X,
                Settings.RIGHT_BRIDGE_Y,
                getX(destination),
                getY(destination));
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
   * calculate the euclidean distance between the two given attacker
   * @param firstAttacker firstAttacker
   * @param secondAttacker secondAttacker
   * @return distance
   */
  private double getEuclideanDistance(Attacker firstAttacker, Attacker secondAttacker) {
    ImageView firstAttackerImage = cardsImage.get(firstAttacker);
    ImageView secondAttackerImage = cardsImage.get(secondAttacker);
    if (firstAttackerImage == null || secondAttackerImage == null)
      return Settings.INF;
    return getEuclideanDistance(getX(firstAttacker), getY(firstAttacker), getX(secondAttacker), getY(secondAttacker));
  }

  /**
   * calculate region number of the given card
   *
   * @param attacker the given card
   * @return 0 for bridges and 1 for friendly half and 2 for enemy's half
   */
  private int getRegionNumber(Attacker attacker) {
    ImageView cardImage = cardsImage.get(attacker);
    if (cardImage == null)
      return 3;
    return getRegionNumber(getX(attacker), getY(attacker));
  }

  /**
   * calculate region number of the given card
   * @param x x of the given position
   * @param y y of the given position
   * @return 0 for bridge lane, 1 for friendly half and 2 for enemy's half
   */
  private int getRegionNumber(double x, double y) {
    int middleRow = Settings.MAP_ROW_COUNT / 2;
    int cellRow = (int) (y / Settings.CELL_HEIGHT);
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
    updateVelocities();
    updateHps();
    checkTowers();
    unapplySpells();
    moveTroops();
    checkGameEnding();
    handleNotification();
  }

  /**
   * prints information about active cards
   */
  private void handleNotification() {
    if (lastNotificationTime == -1) {
      lastNotificationTime = currentMilliSecond;
    } else if (currentMilliSecond - lastNotificationTime >= 5000L) {
      System.out.println("-Active Buildings:");
      for (Building activeBuilding : activeBuildings) {
        System.out.println(activeBuilding.getImageKey().toLowerCase()
            + ": "
            + activeBuilding.getHp()
            + " Hp");
      }

      System.out.println("-Active Troops:");
      for (Troop activeTroop : activeTroops) {
        System.out.println(activeTroop.getImageKey().toLowerCase()
            + ": "
            + activeTroop.getHp());
      }

      lastNotificationTime = currentMilliSecond;
    }
  }
}
