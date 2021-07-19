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
import view.DialogShower;
import view.ViewManager;

/**
 * MainMenuController class, handles interactions in main menu
 *
 * @author Adibov & Armin Gh
 * @version 1.0
 */
public class MainMenuController {
  private List<Card> deck = new ArrayList<>(Settings.DECK_SIZE);
  private boolean[] isCardAdded = new boolean[12];
  @FXML private HBox deckView;
  @FXML private Label unableToPlaceCardLabel;

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
      gameController.setRobotPlayer(vsEasyBot ? new BeginnerRobot() : new AdvancedRobot());
      gameController.getPersonPlayer().setDeck(deck);
      ViewManager.loadMapView();
      // save deck to database
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
      deck.add(new Archer());
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
      deck.add(new Arrows());
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
      deck.add(new BabyDragon());
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
      deck.add(new Barbarians());
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
      deck.add(new Cannon());
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
      deck.add(new Fireball());
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
      deck.add(new Giant());
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
      deck.add(new InfernoTower());
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
      deck.add(new MiniPekka());
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
      deck.add(new Rage());
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
      deck.add(new Valkyrie());
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
      deck.add(new Wizard());
      deckView.getChildren().add(new ImageView(Wizard.getDeckImage()));
      isCardAdded[Cards.WIZARD.ordinal()] = true;
      unableToPlaceCardLabel.setVisible(false);
    } else {
      unableToPlaceCardLabel.setVisible(true);
    }
  }
}
