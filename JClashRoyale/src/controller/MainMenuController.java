package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import view.ViewManager;

/**
 * MainMenuController class, handles interactions in main menu
 * @author Adibov & Armin Gh
 * @version 1.0
 */
public class MainMenuController {
    /**
     * logout from the current account and return to log in view
     * @param event occurred event
     */
    @FXML
    void logoutPressed(ActionEvent event) {
        ViewManager.loadLoginView();
    }
}
