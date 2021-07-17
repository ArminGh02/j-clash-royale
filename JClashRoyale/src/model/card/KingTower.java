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
    }

    /**
     * get image key
     * @return image key
     */
    @Override
    public String getImageKey() {
        return "KING_TOWER";
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
