package model.card;

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
}
