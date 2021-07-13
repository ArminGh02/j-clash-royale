package model;

/**
 * player class, handles information/action for players
 * @author Adibov & Armin Gh
 * @version 1.0
 */
public abstract class Player {
    private double elixir = Settings.INITIAL_ELIXIR;

    public double getElixir() {
        return elixir;
    }

    public void increaseElixir() {
        this.elixir += Settings.ELIXIR_INCREASE;
    }

    public void decreaseElixir(int decreaseAmount) {
        this.elixir -= decreaseAmount;
    }
}
