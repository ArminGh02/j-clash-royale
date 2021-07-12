package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import model.Person;
import util.DBHandler;
import view.ViewManager;

/**
 * LoginController class, handles interactions and events in login view
 * @version 1.0
 * @author Adibov & Armin Gh
 */
public class LoginController {
    @FXML private TextField usernameTextField;
    @FXML private PasswordField passwordField;
    @FXML private Label warningLabel;

    @FXML
    void loginButtonOnAction(ActionEvent event) {
        warningLabel.setVisible(false);
        String username = usernameTextField.getText();
        String password = passwordField.getText();
        if (DBHandler.doesPersonExists(username, password)) {
            SoloGameController.getInstance().setPersonPlayer(DBHandler.getPerson(username));
            ViewManager.loadMainMenuView();
        }
        else { // user is not created before
            warningLabel.setText("Username or password is incorrect");
            warningLabel.setVisible(true);
        }
    }

    /**
     * implement signup button on action
     * @param event new event
     */
    @FXML
    void signUpButtonOnAction(ActionEvent event) {
        warningLabel.setVisible(false);
        String username = usernameTextField.getText();
        String password = passwordField.getText();
        if (!DBHandler.doesPersonExists(username)) {
            DBHandler.addPerson(new Person(username, password));
            ViewManager.loadMainMenuView();
        }
        else { // user already exists
            warningLabel.setText("User already exists");
            warningLabel.setVisible(true);
        }
    }
}
