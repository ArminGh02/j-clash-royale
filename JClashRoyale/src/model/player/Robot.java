package model.player;

import model.player.Player;

/**
 * Robot class, implements robots turn
 * @author Adibov & Armin Gh
 * @version 1.0
 */
abstract public class Robot extends Player {
    /**
     * get player group of the player
     *
     * @return PlayerGroup
     */
    @Override
    public PlayerGroup getPlayerGroup() {
        return PlayerGroup.ROBOT;
    }
}
