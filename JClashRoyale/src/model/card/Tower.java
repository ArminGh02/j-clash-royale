package model.card;

/**
 * Tower class, implements tower behaviours
 * @author Adibov & Armin Gh
 * @version 1.0
 */
abstract public class Tower extends Building {
    /**
     * class constructor
     * @param elixirCost elixirCost
     * @param deckElixirImageKey deckElixirImageKey
     */
    public Tower(int elixirCost, String deckElixirImageKey) {
        super(elixirCost, deckElixirImageKey);
    }
}
