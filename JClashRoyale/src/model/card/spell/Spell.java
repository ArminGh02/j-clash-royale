package model.card.spell;

import model.Settings;
import model.card.Card;
import model.card.CardType;

public abstract class Spell extends Card {
  protected double radius; // FIXME fix radius
  private double xDeployment, yDeployment;

  /**
   * class constructor
   * @param elixirCost elixirCost
   * @param imageKey imageKey
   * @param cardType cardType
   */
  public Spell(int level, int elixirCost, String imageKey, CardType cardType) {
    super(level, elixirCost, imageKey, cardType);
  }

  /**
   * radius getter
   * @return radius
   */
  public double getRadius() {
    return radius * Settings.CELL_WIDTH / Settings.MAP_SCALE;
  }

  /**
   * xDeployment getter
   * @return xDeployment
   */
  public double getXDeployment() {
    return xDeployment;
  }

  /**
   * xDeployment setter
   * @param xDeployment xDeployment new value
   */
  public void setXDeployment(double xDeployment) {
    this.xDeployment = xDeployment;
  }

  /**
   * yDeployment getter
   * @return yDeployment
   */
  public double getYDeployment() {
    return yDeployment;
  }

  /**
   * yDeployment setter
   * @param yDeployment yDeployment new value
   */
  public void setYDeployment(double yDeployment) {
    this.yDeployment = yDeployment;
  }
}
