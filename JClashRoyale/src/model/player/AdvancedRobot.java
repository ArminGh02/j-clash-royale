package model.player;

import controller.FrameController;
import controller.GameTimerController;
import controller.MapViewController;

public class AdvancedRobot extends Robot {
  /**
   * class constructor
   */
  public AdvancedRobot() {
    robotType = RobotType.ADVANCED_BOT;
  }

  @Override
  public void play(GameTimerController timer, MapViewController mapViewController,
      FrameController gameLoop) {

  }
}
