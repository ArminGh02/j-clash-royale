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
import model.BeginnerRobot;
import model.Deck;
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
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import view.ViewManager;

/**
 * MainMenuController class, handles interactions in main menu
 * @author Adibov & Armin Gh
 * @version 1.0
 */
public class MainMenuController {
    private List<Card> deck = new ArrayList<>(Deck.SIZE);

    private boolean[] isCardAdded = new boolean[12];
    private final int ARCHER_INDEX = 0;
    private final int ARROWS_INDEX = 1;
    private final int BABY_DRAGON_INDEX = 2;
    private final int BARBARIANS_INDEX = 3;
    private final int CANNON_INDEX = 4;
    private final int FIREBALL_INDEX = 5;
    private final int GIANT_INDEX = 6;
    private final int INFERNO_TOWER_INDEX = 7;
    private final int MINI_PEKKA_INDEX = 8;
    private final int RAGE_INDEX = 9;
    private final int VALKYRIE_INDEX = 10;
    private final int WIZARD_INDEX = 11;

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
      if (!isCardAdded[ARCHER_INDEX] && deck.size() < Deck.SIZE) {
        deck.add(new Archer());
        deckView.getChildren().add(new ImageView(Archer.getDeckImage()));
        isCardAdded[ARCHER_INDEX] = true;
        unableToPlaceCardLabel.setVisible(false);
      } else {
        unableToPlaceCardLabel.setVisible(true);
      }
    }

    @FXML
    public void addArrows(MouseEvent event) {
      if (!isCardAdded[ARROWS_INDEX] && deck.size() < Deck.SIZE) {
        deck.add(new Arrows());
        deckView.getChildren().add(new ImageView(Arrows.getDeckImage()));
        isCardAdded[ARROWS_INDEX] = true;
        unableToPlaceCardLabel.setVisible(false);
      } else {
        unableToPlaceCardLabel.setVisible(true);
      }
    }

    @FXML
    public void addBabyDragon(MouseEvent event) {
      if (!isCardAdded[BABY_DRAGON_INDEX] && deck.size() < Deck.SIZE) {
        deck.add(new BabyDragon());
        deckView.getChildren().add(new ImageView(BabyDragon.getDeckImage()));
        isCardAdded[BABY_DRAGON_INDEX] = true;
        unableToPlaceCardLabel.setVisible(false);
      } else {
        unableToPlaceCardLabel.setVisible(true);
      }
    }

    @FXML
    public void addBarbarians(MouseEvent event) {
      if (!isCardAdded[BARBARIANS_INDEX] && deck.size() < Deck.SIZE) {
        deck.add(new Barbarians());
        deckView.getChildren().add(new ImageView(Barbarians.getDeckImage()));
        isCardAdded[BARBARIANS_INDEX] = true;
        unableToPlaceCardLabel.setVisible(false);
      } else {
        unableToPlaceCardLabel.setVisible(true);
      }
    }

    @FXML
    public void addCannon(MouseEvent event) {
      if (!isCardAdded[CANNON_INDEX] && deck.size() < Deck.SIZE) {
        deck.add(new Cannon());
        deckView.getChildren().add(new ImageView(Cannon.getDeckImage()));
        isCardAdded[CANNON_INDEX] = true;
        unableToPlaceCardLabel.setVisible(false);
      } else {
        unableToPlaceCardLabel.setVisible(true);
      }
    }

    @FXML
    public void addFireball(MouseEvent event) {
      if (!isCardAdded[FIREBALL_INDEX] && deck.size() < Deck.SIZE) {
        deck.add(new Fireball());
        deckView.getChildren().add(new ImageView(Fireball.getDeckImage()));
        isCardAdded[FIREBALL_INDEX] = true;
        unableToPlaceCardLabel.setVisible(false);
      } else {
        unableToPlaceCardLabel.setVisible(true);
      }
    }

    @FXML
    public void addGiant(MouseEvent event) {
      if (!isCardAdded[GIANT_INDEX] && deck.size() < Deck.SIZE) {
        deck.add(new Giant());
        deckView.getChildren().add(new ImageView(Giant.getDeckImage()));
        isCardAdded[GIANT_INDEX] = true;
        unableToPlaceCardLabel.setVisible(false);
      } else {
        unableToPlaceCardLabel.setVisible(true);
      }
    }

    @FXML
    public void addInfernoTower(MouseEvent event) {
      if (!isCardAdded[INFERNO_TOWER_INDEX] && deck.size() < Deck.SIZE) {
        deck.add(new InfernoTower());
        deckView.getChildren().add(new ImageView(InfernoTower.getDeckImage()));
        isCardAdded[INFERNO_TOWER_INDEX] = true;
        unableToPlaceCardLabel.setVisible(false);
      } else {
        unableToPlaceCardLabel.setVisible(true);
      }
    }

    @FXML
    public void addMiniPekka(MouseEvent event) {
      if (!isCardAdded[MINI_PEKKA_INDEX] && deck.size() < Deck.SIZE) {
        deck.add(new MiniPekka());
        deckView.getChildren().add(new ImageView(MiniPekka.getDeckImage()));
        isCardAdded[MINI_PEKKA_INDEX] = true;
        unableToPlaceCardLabel.setVisible(false);
      } else {
        unableToPlaceCardLabel.setVisible(true);
      }
    }

    @FXML
    public void addRage(MouseEvent event) {
      if (!isCardAdded[RAGE_INDEX] && deck.size() < Deck.SIZE) {
        deck.add(new Rage());
        deckView.getChildren().add(new ImageView(Rage.getDeckImage()));
        isCardAdded[RAGE_INDEX] = true;
        unableToPlaceCardLabel.setVisible(false);
      } else {
        unableToPlaceCardLabel.setVisible(true);
      }
    }

    @FXML
    public void addValkyrie(MouseEvent event) {
      if (!isCardAdded[VALKYRIE_INDEX] && deck.size() < Deck.SIZE) {
        deck.add(new Valkyrie());
        deckView.getChildren().add(new ImageView(Valkyrie.getDeckImage()));
        isCardAdded[VALKYRIE_INDEX] = true;
        unableToPlaceCardLabel.setVisible(false);
      } else {
        unableToPlaceCardLabel.setVisible(true);
      }
    }

    @FXML
    public void addWizard(MouseEvent event) {
      if (!isCardAdded[WIZARD_INDEX] && deck.size() < Deck.SIZE) {
        deck.add(new Wizard());
        deckView.getChildren().add(new ImageView(Wizard.getDeckImage()));
        isCardAdded[WIZARD_INDEX] = true;
        unableToPlaceCardLabel.setVisible(false);
      } else {
        unableToPlaceCardLabel.setVisible(true);
      }
    }
}
