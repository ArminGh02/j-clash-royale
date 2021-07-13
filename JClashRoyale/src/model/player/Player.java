package model.player;

import model.Settings;

/**
 * player class, handles information/action for players
 * @author Adibov & Armin Gh
 * @version 1.0
 */
public class Player {
    private int elixir = Settings.INITIAL_ELIXIR;

    public int getElixir() {
        return elixir;
    }

    public void incrementElixir() {
        elixir++;
    }

    public void decreaseElixir(int decreaseAmount) {
        this.elixir -= decreaseAmount;
    }
}
