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
  private int level;
  private int[] hpPerLevel;
  private final int[] damagePerLevel;
  protected int hitSpeed;
  protected Range range;
  protected Target target;
  private Point2D velocity;
  private Card currentTarget;
  private boolean isAttacking;
  private String currentImageKey;
  private long lastAttackTime = 0;

  /**
   * class constructor
   *  @param elixirCost elixirCost
   * @param imageKey imageKey
   * @param hpPerLevel
   * @param damagePerLevel
   * @param hitSpeed
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

  public void decreaseHp(int decreaseAmount) {
      hpPerLevel[level] -= decreaseAmount;
  }

  /**
   * hp getter
   *
   * @return hp
   */
  public int getHp() {
    return hpPerLevel[level];
  }

  /**
   * damage getter
   *
   * @return damage
   */
  public int getDamage() {
    return damagePerLevel[level];
  }

  /**
   * hitSpeed getter
   *
   * @return hitSpeed
   */
  public int getHitSpeed() {
    return hitSpeed;
  }

  /**
   * range getter
   *
   * @return range
   */
  public Range getRange() {
    return range;
  }

  /**
   * get euclidean range in double
   *
   * @return range distance
   */
  public double getRangeDistance() {
    if (range.equals(Range.MELEE)) return Settings.MELEE_ATTACK_RANGE;
    return 0;
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
