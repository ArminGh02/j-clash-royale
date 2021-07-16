package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import model.Deck;
import model.Settings;
import model.card.Card;
import util.Config;

public class MapViewController {
  @FXML
  private GridPane mapGrid;
  @FXML
  private AnchorPane basePane;
  @FXML
  private Label elixirLabel;
  @FXML
  private Label timerLabel;
  @FXML
  private VBox deckSlotsView;

  private ImageView[] deckSlots;

  private SoloGameController gameController = SoloGameController.getInstance();
  private FrameController gameLoop = new FrameController(this);

  @FXML
  public void initialize() {
    initializeDeckSlots();
    makeMapBaseField();
    startElixirControllers();
    startTimer();
    startGameLoop();
  }

  private void initializeDeckSlots() {
    deckSlots = new ImageView[4];
    Deck deck = gameController.getPersonPlayer().getDeck();

    deckSlots[0] = new ImageView(deck.getCard().getDeckElixirImage());
    deckSlots[1] = new ImageView(deck.getCard().getDeckElixirImage());
    deckSlots[2] = new ImageView(deck.getCard().getDeckElixirImage());
    deckSlots[3] = new ImageView(deck.getCard().getDeckElixirImage());

    deckSlots[0].setUserData(deck.getActiveCard(0));
    deckSlots[1].setUserData(deck.getActiveCard(1));
    deckSlots[2].setUserData(deck.getActiveCard(2));
    deckSlots[3].setUserData(deck.getActiveCard(3));

    deckSlots[0].setOnMouseClicked(event -> gameController.getPersonPlayer().setChosenSlotIndex(0));
    deckSlots[1].setOnMouseClicked(event -> gameController.getPersonPlayer().setChosenSlotIndex(1));
    deckSlots[2].setOnMouseClicked(event -> gameController.getPersonPlayer().setChosenSlotIndex(2));
    deckSlots[3].setOnMouseClicked(event -> gameController.getPersonPlayer().setChosenSlotIndex(3));

    deckSlots[0].idProperty().setValue("deck-slot");
    deckSlots[1].idProperty().setValue("deck-slot");
    deckSlots[2].idProperty().setValue("deck-slot");
    deckSlots[3].idProperty().setValue("deck-slot");

    deckSlotsView.getChildren().addAll(deckSlots);
  }

  private void startElixirControllers() {
    new GameElixirController(elixirLabel, gameController.getPersonPlayer()).start();
    new GameElixirController(gameController.getRobotPlayer()).start();
  }

  private void startTimer() {
    GameTimerController timer = new GameTimerController(timerLabel);
    timer.start();
    gameController.setTimer(timer);
  }

  private void startGameLoop() {
    gameLoop.initialize();
    gameLoop.start();
  }

  /**
   * add map base images to the grid pane
   */
  private void makeMapBaseField() {
    final int mapRowCount = Settings.MAP_ROW_COUNT, mapColumnCount = Settings.MAP_COLUMN_COUNT;
    for (int i = 0; i < mapRowCount; i++) {
      for (int j = 0; j < mapColumnCount; j++) {
        if (i == 0 && j == 0) {
          mapGrid.add(new ImageView(Config.retrieveProperty("TOP_LEFT_GRASS_IMAGE")), j, i);
        } else if (i == 0 && j == mapColumnCount - 1) {
          mapGrid.add(new ImageView(Config.retrieveProperty("TOP_RIGHT_GRASS_IMAGE")), j, i);
        } else if (i == mapRowCount - 1 && j == 0) {
          mapGrid.add(new ImageView(Config.retrieveProperty("BOTTOM_LEFT_GRASS_IMAGE")), j, i);
        } else if (i == mapRowCount - 1 && j == mapColumnCount - 1) {
          mapGrid.add(new ImageView(Config.retrieveProperty("BOTTOM_RIGHT_GRASS_IMAGE")), j, i);
        } else if (i == 0) {
          mapGrid.add(new ImageView(Config.retrieveProperty("TOP_GRASS_IMAGE")), j, i);
        } else if (j == 0) {
          mapGrid.add(new ImageView(Config.retrieveProperty("LEFT_GRASS_IMAGE")), j, i);
        } else if (j == mapColumnCount - 1) {
          mapGrid.add(new ImageView(Config.retrieveProperty("RIGHT_GRASS_IMAGE")), j, i);
        } else if (i == mapRowCount - 1) {
          mapGrid.add(new ImageView(Config.retrieveProperty("BOTTOM_GRASS_IMAGE")), j, i);
        } else {
          mapGrid.add(new ImageView(Config.retrieveProperty("FULL_GRASS_IMAGE")), j, i);
        }
      }
    }

    int waterRow = mapRowCount / 2;
    mapGrid.add(new ImageView(Config.retrieveProperty("LEFT_WATER_IMAGE")), 0, waterRow);
    mapGrid.add(new ImageView(Config.retrieveProperty("RIGHT_WATER_IMAGE")), mapColumnCount - 1,
        waterRow);
    for (int j = 1; j < mapColumnCount - 1; j++) {
      mapGrid.add(new ImageView(Config.retrieveProperty("FULL_WATER_IMAGE")), j, waterRow);
    }

    mapGrid.add(new ImageView(Config.retrieveProperty("BRIDGE_IMAGE")), 1, waterRow);
    mapGrid
        .add(new ImageView(Config.retrieveProperty("BRIDGE_IMAGE")), mapColumnCount - 2, waterRow);
  }

  /**
   * player clicked on map and wants to deploy card
   *
   * @param event happened event
   */
  @FXML
  void gridPaneMouseClicked(MouseEvent event) {
    double x = event.getSceneX() - 32, y = event.getSceneY() - 32;
    Card deployedCard = gameController.deployCard(gameController.getPersonPlayer());
    if (deployedCard == null)
      return;
    gameLoop.addCardToMap(deployedCard, x, y);
  }

  /**
   * add the given imageView to the base pane
   * @param imageView the given image view
   */
  public void addImageView(ImageView imageView) {
    basePane.getChildren().add(imageView);
  }

  /**
   * add the given image view to the map grid
   * @param imageView the given image view
   * @param j column index
   * @param i row index
   */
  public void addMapGrid(ImageView imageView, int j, int i) {
    mapGrid.add(imageView, j, i);
  }
}
