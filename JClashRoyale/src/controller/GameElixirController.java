package controller;

import java.util.Timer;
import java.util.TimerTask;
import javafx.application.Platform;
import javafx.scene.control.Label;
import model.player.Player;
import model.player.Robot;
import model.Settings;

/**
 * <strong>this class controls value of elixir of a {@code Player} and its corresponding
 * {@code Label}</strong>
 * @author Adibov & Armin Gh
 * @version 1.0
 */
public class GameElixirController {
  /**
   * the player that his/her elixir gets updated by calling start method
   */
  private final Player player;

  /**
   * the label that gets updated by calling start method
   */
  private final Label elixirLabel;

  private final Timer timer = new Timer();

  /**
   * @param elixirLabel label that should be updated
   * @param player player that his/her/its elixir should be updated
   */
  public GameElixirController(Label elixirLabel, Player player) {
    this.elixirLabel = elixirLabel;
    this.player = player;
  }

  /**
   * @param robot the robot that its elixir should be updated
   */
  public GameElixirController(Robot robot) {
    this(null, robot);
  }

  /**
   * starts updating {@code elixirLabel} and {@code player} elixir
   */
  public void start() {
    TimerTask firstPeriodTask =
        new increasingElixirTask((Settings.GAME_DURATION_IN_SECONDS / 3));
    TimerTask secondPeriodTask =
        new increasingElixirTask((Settings.GAME_DURATION_IN_SECONDS / 3));

    // every one fifth of second, increase player elixir by 0.1 (Settings.ELIXIR_INCREASE)
    timer.scheduleAtFixedRate(firstPeriodTask, 0, Settings.FIRST_PERIOD_TO_INCREMENT_ELIXIR);

    // after 2 minutes delay, every one tenth of second, increase player elixir by 0.1
    timer.scheduleAtFixedRate(secondPeriodTask, (Settings.GAME_DURATION_IN_SECONDS / 3) * 2 * 1000,
        Settings.SECOND_PERIOD_TO_INCREMENT_ELIXIR);
  }

  private class increasingElixirTask extends TimerTask {
    private int executionCount;

    public increasingElixirTask(int executionCount) {
      if (executionCount < 1) {
        throw new IllegalArgumentException();
      }
      this.executionCount = executionCount;
    }

    @Override
    public void run() {
      if (player.getElixir() < 10) {
        player.increaseElixir();
        if (elixirLabel != null) {
          Platform.runLater(() -> elixirLabel.setText(String.valueOf(player.getElixir())));
        }
      }

      if (executionCount <= 1) {
        timer.cancel();
      } else {
        executionCount--;
      }
    }
  }
}
