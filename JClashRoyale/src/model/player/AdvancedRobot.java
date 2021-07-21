package model.player;

import controller.FrameController;
import controller.GameTimerController;
import controller.MapViewController;
import java.util.Timer;
import java.util.TimerTask;
import model.Settings;
import model.card.Card;

public class AdvancedRobot extends Robot {
  /**
   * class constructor
   */
  public AdvancedRobot() {
    robotType = RobotType.ADVANCED_BOT;
  }

  @Override
  public void play(GameTimerController gameTimer, MapViewController map,
      FrameController gameLoop) {
    Timer timer = new Timer();
    timer.scheduleAtFixedRate(new TimerTask() {
      @Override
      public void run() {
        if (!gameTimer.isEnded()) {
          // TODO: add conditions in this line
          Card toDeploy = deck.deployRandomCard(elixir, rand);
          if (toDeploy != null) {
            int randomX = Settings.LEFT_VBOX_WIDTH + rand.nextInt(Settings.MAP_WIDTH - Settings.CELL_WIDTH) + Settings.CELL_WIDTH_SHIFT;
            int randomY = rand.nextInt(Settings.MAP_UP_HALF_HEIGHT - Settings.CELL_HEIGHT) + Settings.CELL_HEIGHT_SHIFT;
            while (!gameLoop.canDeployCard(toDeploy, randomX, randomY, true)) {
              randomX = Settings.LEFT_VBOX_WIDTH + rand.nextInt(Settings.MAP_WIDTH - Settings.CELL_WIDTH) + Settings.CELL_WIDTH_SHIFT;
              randomY = rand.nextInt(Settings.MAP_UP_HALF_HEIGHT - Settings.CELL_HEIGHT) + Settings.CELL_HEIGHT_SHIFT;
            }
            map.deployCard(toDeploy, randomX, randomY, true);
          }
        } else {
          timer.cancel();
        }
      }
    }, 0, 3000);
  }
}
