package model.card;

import java.awt.geom.Point2D;

/**
 * Troop class, saves
 * @author Adibov & Armin Gh
 * @version 1.0
 */
abstract public class Troop extends Card {
    private int hp, damage, hitSpeed, areaSplash, count;
    private Speed speed;
    private Range range;
    private Target target;
    private Point2D velocity;

    public Troop(int elixirCost, String deckElixirImageKey) {
        super(elixirCost, deckElixirImageKey);
    }

    /**
     * hp getter
     * @return hp
     */
    public int getHp() {
        return hp;
    }

    /**
     * damage getter
     * @return damage
     */
    public int getDamage() {
        return damage;
    }

    /**
     * hitSpeed getter
     * @return hitSpeed
     */
    public int getHitSpeed() {
        return hitSpeed;
    }

    /**
     * areaSplash getter
     * @return areaSplash
     */
    public int getAreaSplash() {
        return areaSplash;
    }

    /**
     * count getter
     * @return count
     */
    public int getCount() {
        return count;
    }

    /**
     * speed getter
     * @return speed
     */
    public Speed getSpeed() {
        return speed;
    }

    /**
     * range getter
     * @return range
     */
    public Range getRange() {
        return range;
    }

    /**
     * target getter
     * @return target
     */
    public Target getTarget() {
        return target;
    }

    /**
     * velocity getter
     * @return velocity
     */
    public Point2D getVelocity() {
        return velocity;
    }
}
