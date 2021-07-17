package model.card;

import java.awt.geom.Point2D;

public abstract class Spell extends Card {

  private Point2D velocity;
  private Card currentTarget;
  private boolean isAttacking;

  public Spell(int elixirCost, String imageKey) {
    super(elixirCost, imageKey, CardType.SPELL);
  }

  /**
   * velocity setter
   *
   * @param velocity velocity new value
   */
  public void setVelocity(Point2D velocity) {
    this.velocity = velocity;
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
}
