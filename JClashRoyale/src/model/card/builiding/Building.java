package model.card.builiding;

import model.card.Attacker;
import model.card.CardType;

public abstract class Building extends Attacker {
  protected boolean isTower = false;
  protected long lifeTime; // in milliseconds
  protected long deploymentTime;

  public Building(
      int level,
      int elixirCost,
      String imageKey,
      int[] hpPerLevel,
      int[] damagePerLevel,
      int hitSpeed) {
    super(level, elixirCost, imageKey, CardType.BUILDING, hpPerLevel, damagePerLevel, hitSpeed);
  }

  /**
   * isTower getter
   * @return isTower
   */
  public boolean isTower() {
    return isTower;
  }

  /**
   * deploymentTime getter
   * @return deploymentTime
   */
  public long getDeploymentTime() {
    return deploymentTime;
  }

  /**
   * lifeTime getter
   * @return lifeTime
   */
  public long getLifeTime() {
    return lifeTime;
  }

  /**
   * deploymentTime setter
   * @param deploymentTime deploymentTime new value
   */
  public void setDeploymentTime(long deploymentTime) {
    this.deploymentTime = deploymentTime;
  }
}
