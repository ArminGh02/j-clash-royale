package model.card.spell;

/**
 * DamagingSpell class, handles damaging spells actions
 * @author Adibov & Armin Gh
 * @version 1.0
 */
abstract public class DamagingSpell extends Spell {
    protected int areaDamage; // TODO make it dependent to level

    public DamagingSpell(int elixirCost, String imageKey) {
        super(elixirCost, imageKey);
    }
}
