package model.player;

import controller.FrameController;
import controller.GameTimerController;
import controller.MapViewController;
import java.util.Timer;
import java.util.TimerTask;
import model.Settings;
import model.card.Card;

public class BeginnerRobot extends Robot {
  @Override
  public void play(GameTimerController gameTimer, MapViewController map, FrameController gameLoop) {
    Timer timer = new Timer();
    timer.scheduleAtFixedRate(new TimerTask() {
      @Override
      public void run() {
        if (!gameTimer.isEnded()) {
          Card toDeploy = deck.deployRandomCard(elixir, rand);
          if (toDeploy != null) {
            int randomX = Settings.LEFT_VBOX_WIDTH + rand.nextInt(Settings.MAP_WIDTH);
            int randomY = rand.nextInt(Settings.MAP_UP_HALF_HEIGHT);
            map.deployCard(toDeploy, randomX, randomY, true);
          }
        } else {
          timer.cancel();
        }
      }
    }, 0, 3000);
  }
}
