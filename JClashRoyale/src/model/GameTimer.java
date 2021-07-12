package model;

import java.util.Timer;
import java.util.TimerTask;
import javafx.application.Platform;
import javafx.scene.control.Label;

/**
 * <strong>This class handles the timer {@code Label} of the game</strong>
 * and updates it after calling {@code start} method.
 * It can be deduced that the game time has come to an end
 * by checking return value of {@code isEnded} method.
 * @author Adibov & Armin Gh
 * @version 1.0
 */
public class GameTimer {
  private boolean ended;
  private int GAME_DURATION = 3 * 60;
  private Timer timer = new Timer();

  /**
   * the label that this class should update after calling {@code start} method
   */
  private Label timerLabel;

  /**
   * constructs a {@code GameTimer}
   * @param timerLabel label that should be updated
   */
  public GameTimer(Label timerLabel) {
    this.timerLabel = timerLabel;
  }

  /**
   * starts the game timer and updates the {@code timerLabel}
   */
  public void start() {
    TimerTask updatingTimerTask = new TimerTask() {
      private int currentSeconds = GAME_DURATION;

      @Override
      public void run() {
        currentSeconds--;

        Platform.runLater(() -> {
          int min = currentSeconds / 60;
          int sec = currentSeconds % 60;
          timerLabel.setText(String.format("%02d:%02d", min, sec));
        });

        if (currentSeconds <= 0) {
          ended = true;
          timer.cancel();
        }
      }
    };
    timer.scheduleAtFixedRate(updatingTimerTask, 0, 1000);
  }

  public boolean isEnded() {
    return ended;
  }
}
