package controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import model.Deck;
import model.player.AdvancedRobot;
import model.player.BeginnerRobot;
import model.Settings;
import model.card.Cards;
import model.card.troop.Archer;
import model.card.spell.Arrows;
import model.card.troop.BabyDragon;
import model.card.troop.Barbarians;
import model.card.builiding.Cannon;
import model.card.Card;
import model.card.spell.Fireball;
import model.card.troop.Giant;
import model.card.builiding.InfernoTower;
import model.card.troop.MiniPekka;
import model.card.spell.Rage;
import model.card.troop.Valkyrie;
import model.card.troop.Wizard;
import model.player.Person;
import util.DBHandler;
import view.DialogShower;
import view.ViewManager;

/**
 * MainMenuController class, handles interactions in main menu
 *
 * @author Adibov & Armin Gh
 * @version 1.0
 */
public class MainMenuController {
  private int personLevel;
  @FXML private Label personPointsLabel;
  @FXML private Label personLevelLabel;

  private List<Card> deck = new ArrayList<>(Settings.DECK_SIZE);
  private boolean[] isCardAdded = new boolean[12];
  @FXML private HBox deckView;
  @FXML private Label unableToPlaceCardLabel;

  @FXML
  public void initialize() {
    initDeck();

    Person person = SoloGameController.getInstance().getPersonPlayer();
    personLevel = person.getLevel();
    personLevelLabel.setText("Level: " + personLevel);
    personPointsLabel.setText("Points: " + person.getPoints());
  }

  private void initDeck() {
    Person person = SoloGameController.getInstance().getPersonPlayer();
    List<Integer> ordinalsToAdd = DBHandler.getPersonsDeck(person);
    if (ordinalsToAdd == null) {
      return;
    }
    for (int i = 0; i < Settings.DECK_SIZE; i++) {
      int ordinal = ordinalsToAdd.get(i);
      if (ordinal == Cards.ARCHER.ordinal()) {
        addArcher(null);
      } else if (ordinal == Cards.ARROWS.ordinal()) {
        addArrows(null);
      } else if (ordinal == Cards.BABY_DRAGON.ordinal()) {
        addBabyDragon(null);
      } else if (ordinal == Cards.BARBARIANS.ordinal()) {
        addBarbarians(null);
      } else if (ordinal == Cards.CANNON.ordinal()) {
        addCannon(null);
      } else if (ordinal == Cards.FIREBALL.ordinal()) {
        addFireball(null);
      } else if (ordinal == Cards.GIANT.ordinal()) {
        addGiant(null);
      } else if (ordinal == Cards.INFERNO_TOWER.ordinal()) {
        addInfernoTower(null);
      } else if (ordinal == Cards.MINI_PEKKA.ordinal()) {
        addMiniPekka(null);
      } else if (ordinal == Cards.RAGE.ordinal()) {
        addRage(null);
      } else if (ordinal == Cards.VALKYRIE.ordinal()) {
        addValkyrie(null);
      } else if (ordinal == Cards.WIZARD.ordinal()) {
        addWizard(null);
      }
    }
  }

  @FXML
  void startGameVsEasyBot(ActionEvent event) {
    startGame(true);
  }

  @FXML
  void startGameVsAdvancedBot(ActionEvent event) {
    startGame(false);
  }

  private void startGame(boolean vsEasyBot) {
    if (deck.size() == Settings.DECK_SIZE) {
      SoloGameController gameController = SoloGameController.getInstance();
      Person person = gameController.getPersonPlayer();
      DBHandler.updatePersonsDeck(person, Deck.toOrdinals(deck));
      gameController.setRobotPlayer(vsEasyBot ? new BeginnerRobot() : new AdvancedRobot());
      person.setDeck(deck);
      ViewManager.loadMapView();
    } else {
      DialogShower.show("Unable to Start the Game", "You need to set your battle deck first!");
    }
  }

  @FXML
  public void resetDeck(ActionEvent event) {
    deck.clear();
    deckView.getChildren().clear();
    Arrays.fill(isCardAdded, false);
    unableToPlaceCardLabel.setVisible(false);
  }

  /**
   * logout from the current account and return to log in view
   *
   * @param event occurred event
   */
  @FXML
  void logoutPressed(ActionEvent event) {
    ViewManager.loadLoginView();
  }

  @FXML
  public void addArcher(MouseEvent event) {
    if (!isCardAdded[Cards.ARCHER.ordinal()] && deck.size() < Settings.DECK_SIZE) {
      deck.add(new Archer(personLevel));
      deckView.getChildren().add(new ImageView(Archer.getDeckImage()));
      isCardAdded[Cards.ARCHER.ordinal()] = true;
      unableToPlaceCardLabel.setVisible(false);
    } else {
      unableToPlaceCardLabel.setVisible(true);
    }
  }

  @FXML
  public void addArrows(MouseEvent event) {
    if (!isCardAdded[Cards.ARROWS.ordinal()] && deck.size() < Settings.DECK_SIZE) {
      deck.add(new Arrows(personLevel));
      deckView.getChildren().add(new ImageView(Arrows.getDeckImage()));
      isCardAdded[Cards.ARROWS.ordinal()] = true;
      unableToPlaceCardLabel.setVisible(false);
    } else {
      unableToPlaceCardLabel.setVisible(true);
    }
  }

  @FXML
  public void addBabyDragon(MouseEvent event) {
    if (!isCardAdded[Cards.BABY_DRAGON.ordinal()] && deck.size() < Settings.DECK_SIZE) {
      deck.add(new BabyDragon(personLevel));
      deckView.getChildren().add(new ImageView(BabyDragon.getDeckImage()));
      isCardAdded[Cards.BABY_DRAGON.ordinal()] = true;
      unableToPlaceCardLabel.setVisible(false);
    } else {
      unableToPlaceCardLabel.setVisible(true);
    }
  }

  @FXML
  public void addBarbarians(MouseEvent event) {
    if (!isCardAdded[Cards.BARBARIANS.ordinal()] && deck.size() < Settings.DECK_SIZE) {
      deck.add(new Barbarians(personLevel));
      deckView.getChildren().add(new ImageView(Barbarians.getDeckImage()));
      isCardAdded[Cards.BARBARIANS.ordinal()] = true;
      unableToPlaceCardLabel.setVisible(false);
    } else {
      unableToPlaceCardLabel.setVisible(true);
    }
  }

  @FXML
  public void addCannon(MouseEvent event) {
    if (!isCardAdded[Cards.CANNON.ordinal()] && deck.size() < Settings.DECK_SIZE) {
      deck.add(new Cannon(personLevel));
      deckView.getChildren().add(new ImageView(Cannon.getDeckImage()));
      isCardAdded[Cards.CANNON.ordinal()] = true;
      unableToPlaceCardLabel.setVisible(false);
    } else {
      unableToPlaceCardLabel.setVisible(true);
    }
  }

  @FXML
  public void addFireball(MouseEvent event) {
    if (!isCardAdded[Cards.FIREBALL.ordinal()] && deck.size() < Settings.DECK_SIZE) {
      deck.add(new Fireball(personLevel));
      deckView.getChildren().add(new ImageView(Fireball.getDeckImage()));
      isCardAdded[Cards.FIREBALL.ordinal()] = true;
      unableToPlaceCardLabel.setVisible(false);
    } else {
      unableToPlaceCardLabel.setVisible(true);
    }
  }

  @FXML
  public void addGiant(MouseEvent event) {
    if (!isCardAdded[Cards.GIANT.ordinal()] && deck.size() < Settings.DECK_SIZE) {
      deck.add(new Giant(personLevel));
      deckView.getChildren().add(new ImageView(Giant.getDeckImage()));
      isCardAdded[Cards.GIANT.ordinal()] = true;
      unableToPlaceCardLabel.setVisible(false);
    } else {
      unableToPlaceCardLabel.setVisible(true);
    }
  }

  @FXML
  public void addInfernoTower(MouseEvent event) {
    if (!isCardAdded[Cards.INFERNO_TOWER.ordinal()] && deck.size() < Settings.DECK_SIZE) {
      deck.add(new InfernoTower(personLevel));
      deckView.getChildren().add(new ImageView(InfernoTower.getDeckImage()));
      isCardAdded[Cards.INFERNO_TOWER.ordinal()] = true;
      unableToPlaceCardLabel.setVisible(false);
    } else {
      unableToPlaceCardLabel.setVisible(true);
    }
  }

  @FXML
  public void addMiniPekka(MouseEvent event) {
    if (!isCardAdded[Cards.MINI_PEKKA.ordinal()] && deck.size() < Settings.DECK_SIZE) {
      deck.add(new MiniPekka(personLevel));
      deckView.getChildren().add(new ImageView(MiniPekka.getDeckImage()));
      isCardAdded[Cards.MINI_PEKKA.ordinal()] = true;
      unableToPlaceCardLabel.setVisible(false);
    } else {
      unableToPlaceCardLabel.setVisible(true);
    }
  }

  @FXML
  public void addRage(MouseEvent event) {
    if (!isCardAdded[Cards.RAGE.ordinal()] && deck.size() < Settings.DECK_SIZE) {
      deck.add(new Rage(personLevel));
      deckView.getChildren().add(new ImageView(Rage.getDeckImage()));
      isCardAdded[Cards.RAGE.ordinal()] = true;
      unableToPlaceCardLabel.setVisible(false);
    } else {
      unableToPlaceCardLabel.setVisible(true);
    }
  }

  @FXML
  public void addValkyrie(MouseEvent event) {
    if (!isCardAdded[Cards.VALKYRIE.ordinal()] && deck.size() < Settings.DECK_SIZE) {
      deck.add(new Valkyrie(personLevel));
      deckView.getChildren().add(new ImageView(Valkyrie.getDeckImage()));
      isCardAdded[Cards.VALKYRIE.ordinal()] = true;
      unableToPlaceCardLabel.setVisible(false);
    } else {
      unableToPlaceCardLabel.setVisible(true);
    }
  }

  @FXML
  public void addWizard(MouseEvent event) {
    if (!isCardAdded[Cards.WIZARD.ordinal()] && deck.size() < Settings.DECK_SIZE) {
      deck.add(new Wizard(personLevel));
      deckView.getChildren().add(new ImageView(Wizard.getDeckImage()));
      isCardAdded[Cards.WIZARD.ordinal()] = true;
      unableToPlaceCardLabel.setVisible(false);
    } else {
      unableToPlaceCardLabel.setVisible(true);
    }
  }
}
