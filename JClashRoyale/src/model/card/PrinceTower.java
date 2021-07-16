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
    }

    /**
     * get image key
     * @return image key
     */
    @Override
    public String getImageKey() {
        return "PRINCE_TOWER";
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
