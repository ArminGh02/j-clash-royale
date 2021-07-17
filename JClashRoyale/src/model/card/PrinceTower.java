package model.card;

import model.Settings;

/**
 * PrinceTower class, implements prince tower behaviours
 * @author Adibov & Armin Gh
 * @version 1.0
 */
public class PrinceTower extends Tower {
    /**
     * class constructor
     */
    public PrinceTower() {
        super(0, "PRINCE_TOWER");
        hp = 1400;
        damage = 50;
        hitSpeed = 800;
    }

    /**
     * get euclidean range in double
     *
     * @return range distance
     */
    @Override
    public double getRangeDistance() {
        return Settings.PRINCE_TOWER_ATTACK_RANGE;
    }
}
