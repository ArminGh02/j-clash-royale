package controller;

import java.util.List;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import model.Settings;
import model.card.Card;
import model.card.CardType;
import model.card.spell.Spell;
import model.card.troop.Troop;
import model.player.Person;
import util.Config;

public class MapViewController {
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
    // gameController.getRobotPlayer().play(gameController.getTimer(), this, gameLoop);
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
      if (!gameLoop.canDeployCard(event.getSceneX(), event.getSceneY(), false))
        return;

      Card toDeploy = (Card) deckSlots[chosenSlot].getUserData();
      Person person = gameController.getPersonPlayer();
      if (person.deploy(toDeploy)) {
        if (toDeploy.getDeployedImage() != null)
          deployCard(toDeploy, event.getSceneX() - toDeploy.getDeployedImage().getWidth() / 2.0,
              event.getSceneY() - toDeploy.getDeployedImage().getHeight() / 2.0, false);
        else
          deployCard(toDeploy, event.getSceneX(), event.getSceneY(), false);

        Card nextCard = person.getDeck().nextCard(toDeploy);
        deckSlots[chosenSlot].setUserData(nextCard);
        deckSlots[chosenSlot].setImage(nextCard.getDeckSlotImage());

        chosenSlot = -1; // reset
      }
    }
  }

  /**
   * deploy the given card by the number of card's count
   * @param card the given card
   */
  public void deployCard(Card card, double x, double y, boolean isBot) {
    int count = 1;
    if (card.getType().equals(CardType.TROOP)) {
      Troop temp = (Troop) card;
      count = temp.getCount();
    }

    int coefficient = 1;
    if (x > (Settings.MAP_COLUMN_COUNT / 2.0) * Settings.CELL_WIDTH + Settings.LEFT_VBOX_WIDTH)
      coefficient = -1;
    for (int i = 0; i < count; i++) {
      Card newCard;
      if (i == count - 1)
        newCard = card;
      else
        newCard = card.newInstance();
      newCard.setTeamNumber(isBot ? 1 : 0);
      gameLoop.addToActiveCards(newCard);
      addImageOfCard(newCard, x + coefficient * i * Settings.CELL_WIDTH_SHIFT, y);
    }
  }

  public void addImageOfCard(Card toDeploy, double x, double y) {
    if (toDeploy.getType().equals(CardType.DAMAGING_SPELL) || toDeploy.getType().equals(CardType.RAGE_SPELL)) {
      Spell spellCard = (Spell) toDeploy;
      spellCard.setXDeployment(x);
      spellCard.setYDeployment(y);
      return;
    }

    ImageView imageToAdd = new ImageView(toDeploy.getDeployedImage());
    imageToAdd.setX(x);
    imageToAdd.setY(y);
    gameLoop.addImageOfCard(toDeploy, imageToAdd);
    Platform.runLater(() -> basePane.getChildren().add(imageToAdd));
  }

  /**
   * delete the given node from the base pane
   * @param node the given node
   */
  public void deleteNode(Node node) {
    Platform.runLater(() -> basePane.getChildren().remove(node));
  }

  /**
   * add the given image view to the map grid
   * @param imageView the given image view
   * @param j column index
   * @param i row index
   */
  public void addMapGrid(ImageView imageView, int j, int i) {
    mapGrid.add(imageView, j, i);
    imageView.setX(Settings.LEFT_VBOX_WIDTH + j * Settings.CELL_WIDTH);
    imageView.setY(i * Settings.CELL_HEIGHT);
  }
}
