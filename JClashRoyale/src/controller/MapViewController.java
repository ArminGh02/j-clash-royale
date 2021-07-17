package controller;

import java.util.List;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import model.Settings;
import model.card.Card;
import model.player.Person;
import util.Config;

public class MapViewController {
  public final int mapRowCount = 13;
  public final int mapColumnCount = 7;

  @FXML private GridPane mapGrid;
  @FXML private AnchorPane basePane;

  @FXML private Label elixirLabel;
  @FXML private Label timerLabel;

  @FXML private VBox deckSlotsView;

  private ImageView[] deckSlots;
  private int chosenSlot;

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
    List<Card> personActiveCards = gameController.getPersonPlayer().getDeck().getActiveCards();

    deckSlots[0] = new ImageView(personActiveCards.get(0).getDeckSlotImage());
    deckSlots[1] = new ImageView(personActiveCards.get(1).getDeckSlotImage());
    deckSlots[2] = new ImageView(personActiveCards.get(2).getDeckSlotImage());
    deckSlots[3] = new ImageView(personActiveCards.get(3).getDeckSlotImage());

    deckSlots[0].setUserData(personActiveCards.get(0));
    deckSlots[1].setUserData(personActiveCards.get(1));
    deckSlots[2].setUserData(personActiveCards.get(2));
    deckSlots[3].setUserData(personActiveCards.get(3));

    deckSlots[0].setOnMouseClicked(event -> chosenSlot = 0);
    deckSlots[1].setOnMouseClicked(event -> chosenSlot = 1);
    deckSlots[2].setOnMouseClicked(event -> chosenSlot = 2);
    deckSlots[3].setOnMouseClicked(event -> chosenSlot = 3);

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
    mapGrid.add(
        new ImageView(Config.retrieveProperty("RIGHT_WATER_IMAGE")), mapColumnCount - 1, waterRow);
    for (int j = 1; j < mapColumnCount - 1; j++) {
      mapGrid.add(new ImageView(Config.retrieveProperty("FULL_WATER_IMAGE")), j, waterRow);
    }

    mapGrid.add(new ImageView(Config.retrieveProperty("BRIDGE_IMAGE")), 1, waterRow);
    mapGrid.add(
        new ImageView(Config.retrieveProperty("BRIDGE_IMAGE")), mapColumnCount - 2, waterRow);
  }

  /**
   * player clicked on map and wants to deploy card
   *
   * @param event happened event
   */
  @FXML
  void deployCard(MouseEvent event) {
    if (chosenSlot != -1) { // player has pressed a deck slot
      Card toDeploy = (Card) deckSlots[chosenSlot].getUserData();
      Person person = gameController.getPersonPlayer();
      if (person.deploy(toDeploy)) {
        toDeploy.setTeamNumber(0);
        gameLoop.addToActiveCards(toDeploy);
        addImageOfCard(toDeploy, event.getSceneX() - 32, event.getSceneY() - 32);

        Card nextCard = person.getDeck().nextCard(toDeploy);
        deckSlots[chosenSlot].setUserData(nextCard);
        deckSlots[chosenSlot].setImage(nextCard.getDeckSlotImage());

        chosenSlot = -1; // reset
      }
    }
  }

  private void addImageOfCard(Card toDeploy, double x, double y) {
    ImageView imageToAdd = new ImageView(toDeploy.getDeployedImage());
    imageToAdd.setX(x);
    imageToAdd.setY(y);
    gameLoop.addImageOfCard(toDeploy, imageToAdd);
    basePane.getChildren().add(imageToAdd);
  }

  /**
   * add the given image view to the map grid
   * @param imageView the given image view
   * @param j column index
   * @param i row index
   */
  public void addMapGrid(ImageView imageView, int j, int i) {
    mapGrid.add(imageView, j, i);
    imageView.setX(Settings.LEFT_VBOX_WIDTH + j * Settings.CELL_WIDTH + Settings.CELL_WIDTH_SHIFT);
    imageView.setY(i * Settings.CELL_HEIGHT + Settings.CELL_HEIGHT_SHIFT);
  }
}
