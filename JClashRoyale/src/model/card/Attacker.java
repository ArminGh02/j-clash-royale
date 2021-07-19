package model.card;

import javafx.geometry.Point2D;
import model.Settings;

/**
 * Attacker class, is used by Troop and Building classes, so it prevents from code duplication
 *
 * @author Adibov & Armin Gh
 * @version 1.0
 */
public abstract class Attacker extends Card {
  private int[] hpPerLevel;
  private final int[] damagePerLevel;
  protected int hitSpeed;
  protected Range range;
  protected double rangeDistance;
  protected Target target;
  private Point2D velocity;
  private Card currentTarget;
  private boolean isAttacking;
  private String currentImageKey;
  private long lastAttackTime = 0;
  protected double attributeMultiplier = 1.0;

  /**
   * class constructor
   *  @param elixirCost elixirCost
   * @param imageKey imageKey
   * @param hpPerLevel hp per level
   * @param damagePerLevel damage per level
   * @param hitSpeed hit speed
   */
  public Attacker(int elixirCost, String imageKey, CardType type, int[] hpPerLevel,
      int[] damagePerLevel, int hitSpeed) {
    super(elixirCost, imageKey, type);
    this.hpPerLevel = hpPerLevel;
    this.damagePerLevel = damagePerLevel;
    this.hitSpeed = hitSpeed;
  }

  /**
   * currentImageKey setter
   *
   * @param currentImageKey currentImageKey new value
   */
  public void setCurrentImageKey(String currentImageKey) {
    this.currentImageKey = currentImageKey;
  }

  /**
   * lastAttackTime setter
   * @param lastAttackTime lastAttackTime new value
   */
  public void setLastAttackTime(long lastAttackTime) {
    this.lastAttackTime = lastAttackTime;
  }

  /**
   * rageMultiplier setter
   * @param attributeMultiplier rageMultiplier new value
   */
  public void setAttributeMultiplier(double attributeMultiplier) {
    this.attributeMultiplier = Math.max(attributeMultiplier, 1.0); // to make sure that the rage spell won't unapply in the wrong way
  }

  /**
   * attributeMultiplier getter
   * @return attributeMultiplier
   */
  public double getAttributeMultiplier() {
    return attributeMultiplier;
  }

  public void decreaseHp(int decreaseAmount) {
      hpPerLevel[level - 1] -= decreaseAmount;
  }

  /**
   * hp getter
   *
   * @return hp
   */
  public int getHp() {
    return hpPerLevel[level - 1];
  }

  /**
   * damage getter
   *
   * @return damage
   */
  public int getDamage() {
    return (int) (damagePerLevel[level - 1] * attributeMultiplier);
  }

  /**
   * hitSpeed getter
   *
   * @return hitSpeed
   */
  public int getHitSpeed() {
    return (int) (hitSpeed / attributeMultiplier);
  }

  /**
   * get euclidean range in double
   *
   * @return range distance
   */
  public double getRangeDistance() {
    if (range == Range.MELEE)
      rangeDistance = Settings.MELEE_ATTACK_RANGE;
    return rangeDistance * Settings.CELL_WIDTH / Settings.MAP_SCALE;
  }

  /**
   * target getter
   *
   * @return target
   */
  public Target getTarget() {
    return target;
  }

  /**
   * velocity setter
   *
   * @param x x velocity new value
   * @param y y velocity new value
   */
  public void setVelocity(double x, double y) {
    //        if (velocity == null) FIXME How to set new velocity?
    //            velocity = new Point2D(0, 0);
    //        velocity.subtract(velocity.getX(), velocity.getY());
    //        velocity.add(x, y);
    velocity = new Point2D(x, y);
  }

  /**
   * currentTarget setter
   *
   * @param currentTarget currentTarget new value
   */
  public void setCurrentTarget(Card currentTarget) {
    this.currentTarget = currentTarget;
  }

  /**
   * isAttacking setter
   *
   * @param attacking isAttacking new value
   */
  public void setAttacking(boolean attacking) {
    isAttacking = attacking;
  }

  /**
   * velocity getter
   *
   * @return velocity
   */
  public Point2D getVelocity() {
    return velocity;
  }

  /**
   * currentTarget getter
   *
   * @return currentTarget
   */
  public Card getCurrentTarget() {
    return currentTarget;
  }

  /**
   * isAttacking getter
   *
   * @return isAttacking
   */
  public boolean isAttacking() {
    return isAttacking;
  }

  /**
   * currentImageKey getter
   *
   * @return currentImageKey
   */
  public String getCurrentImageKey() {
    return currentImageKey;
  }

  /**
   * lastAttackTime getter
   * @return lastAttackTime
   */
  public long getLastAttackTime() {
    return lastAttackTime;
  }
}
