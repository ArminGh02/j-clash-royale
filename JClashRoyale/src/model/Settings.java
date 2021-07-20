package model;

public class Settings {
  public static final int GAME_DURATION_IN_SECONDS = 3 * 60; // seconds
  public static final int FIRST_PERIOD_TO_INCREMENT_ELIXIR = 2000; // milliseconds
  public static final int SECOND_PERIOD_TO_INCREMENT_ELIXIR = 1000; // milliseconds
  public static final int INITIAL_ELIXIR = 4;
  public static final int ELIXIR_INCREASE = 1;
  public static final int DECK_SIZE = 8;

  public static final double MAP_SCALE = 2.5;
  public static final int MAP_ROW_COUNT = 13;
  public static final int MAP_COLUMN_COUNT = 7;
  public static final int CELL_HEIGHT = 64;
  public static final int CELL_WIDTH = 64;
  public static final int CELL_HEIGHT_SHIFT = CELL_HEIGHT / 2;
  public static final int CELL_WIDTH_SHIFT = CELL_WIDTH / 2;
  public static final double CELL_DIAGONAL_SHIFT = CELL_HEIGHT_SHIFT * Math.sqrt(2.0);
  public static final int LEFT_VBOX_WIDTH = 150;
  public static final int MAP_UP_HALF_HEIGHT = 384;
  public static final int MAP_WIDTH = 448;

  public static final double LEFT_BRIDGE_X = 246;
  public static final double LEFT_BRIDGE_Y = 416;
  public static final double RIGHT_BRIDGE_X = 502;
  public static final double RIGHT_BRIDGE_Y = 416;

  public static final double MELEE_ATTACK_RANGE = 0.3; // FIXME check how much range will be appropriate
  public static final double AREA_SPLASH_RANGE = 1;

  public static final double SLOW_SPEED = 0.2; // FIXME check how much speed will be appropriate
  public static final double MEDIUM_SPEED = 0.3;
  public static final double FAST_SPEED = 0.4;

  public static final double RAGE_SPELL_COEFFICIENT = 1.4;

  public static final double EPSILON = 0.1;
  public static final double UP_RIGHT_SLOPE = 2.41; // tan(90 - 45 / 2)
  public static final double UP_LEFT_SLOPE = -2.41; // tan(90 + 45 / 2)
  public static final double LEFT_UP_SLOPE = -0.41; // tan(180 - 45 / 2)
  public static final double LEFT_DOWN_SLOPE = 0.41; // tan(180 + 45 / 2)
  public static final double DOWN_LEFT_SLOPE = 2.41; // tan(270 - 45 / 2)
  public static final double DOWN_RIGHT_SLOPE = -2.41; // tan(270 + 45 / 2)
  public static final double RIGHT_DOWN_SLOPE = -0.41; // tan(360 - 45 / 2)
  public static final double RIGHT_UP_SLOPE = 0.41; // tan(45 / 2)

  public static final int BOT_LEVEL = 2;

  public static final int WINNING_POINT = 200;
  public static final int LOOSING_POINT = 70;

  public static final int MAXIMUM_LEVEL = 5;
  public static final int[] LEVEL_POINTS = {300, 500, 900, 1700, 2500};

  public static final int INF = (int) 1e9;
}
