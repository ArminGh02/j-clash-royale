package model.card;

/**
 * Troop class, saves
 * @author Adibov & Armin Gh
 * @version 1.0
 */
public abstract class Troop extends Attacker {
  private int areaSplash;
  private int count;
  private Speed speed;

    public Troop(int elixirCost, String imageKey) {
        super(elixirCost, imageKey, CardType.TROOP);
    }

  /**
   * areaSplash getter
   *
   * @return areaSplash
   */
  public int getAreaSplash() {
    return areaSplash;
  }

  /**
   * count getter
   *
   * @return count
   */
  public int getCount() {
    return count;
  }

  /**
   * speed getter
   *
   * @return speed
   */
  public Speed getSpeed() {
    return speed;
  }

  /**
   * movement getter
   * @return movement
   */
  abstract public Movement getMovement();
}
