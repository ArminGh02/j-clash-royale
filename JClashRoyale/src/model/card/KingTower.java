package model.card;

import model.Settings;

/**
 * KingTower class, implements king tower behaviours
 * @author Adibov & Armin Gh
 * @version 1.0
 */
public class KingTower extends Tower {
    /**
     * class constructor
     */
    public KingTower() {
        super(0, "KING_TOWER");
        hp = 2400;
        damage = 50;
        hitSpeed = 1000;
    }

    /**
     * get euclidean range in double
     *
     * @return range distance
     */
    @Override
    public double getRangeDistance() {
        return Settings.KING_TOWER_ATTACK_RANGE;
    }
}
