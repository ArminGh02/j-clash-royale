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
import model.player.BeginnerRobot;
import model.Settings;
import model.card.Cards;
import model.card.Archer;
import model.card.Arrows;
import model.card.BabyDragon;
import model.card.Barbarians;
import model.card.Cannon;
import model.card.Card;
import model.card.Fireball;
import model.card.Giant;
import model.card.InfernoTower;
import model.card.MiniPekka;
import model.card.Rage;
import model.card.Valkyrie;
import model.card.Wizard;
import view.ViewManager;

/**
 * MainMenuController class, handles interactions in main menu
 * @author Adibov & Armin Gh
 * @version 1.0
 */
public class MainMenuController {
    private Deck deck = SoloGameController.getInstance().getPersonPlayer().getDeck();
    private boolean[] isCardAdded = new boolean[12];
    @FXML private HBox deckView;
    @FXML private Label unableToPlaceCardLabel;

    @FXML
    void startGameVsEasyBot(ActionEvent event) {
        SoloGameController.getInstance().setRobotPlayer(new BeginnerRobot());
      ViewManager.loadMapView();
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
     * @param event occurred event
     */
    @FXML
    void logoutPressed(ActionEvent event) {
      ViewManager.loadLoginView();
    }

    @FXML
    public void addArcher(MouseEvent event) {
      if (!isCardAdded[Cards.ARCHER.ordinal()] && deck.size() < Settings.DECK_SIZE) {
        deck.addCard(new Archer());
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
        deck.addCard(new Arrows());
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
        deck.addCard(new BabyDragon());
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
        deck.addCard(new Barbarians());
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
        deck.addCard(new Cannon());
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
        deck.addCard(new Fireball());
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
        deck.addCard(new Giant());
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
        deck.addCard(new InfernoTower());
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
        deck.addCard(new MiniPekka());
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
        deck.addCard(new Rage());
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
        deck.addCard(new Valkyrie());
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
        deck.addCard(new Wizard());
        deckView.getChildren().add(new ImageView(Wizard.getDeckImage()));
        isCardAdded[Cards.WIZARD.ordinal()] = true;
        unableToPlaceCardLabel.setVisible(false);
      } else {
        unableToPlaceCardLabel.setVisible(true);
      }
    }
}
