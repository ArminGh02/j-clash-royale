package controller;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Person;
import util.Config;
import util.DBHandler;

/**
 * LoginController class, handles interactions and events in login view
 * @version 1.0
 * @author Adibov & Armin Gh
 */
public class LoginController {
    @FXML private TextField usernameTextField;
    @FXML private PasswordField passwordField;
    @FXML private Label userExistsLabel;
    @FXML private Label wrongPasswordLabel;

    @FXML
    void loginButtonOnAction(ActionEvent event) {
        resetVisibility();
        String username = usernameTextField.getText();
        String password = passwordField.getText();
        if (DBHandler.doesPersonExists(username, password)) {
            Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            switchToMainMenuScene(currentStage);
        }
        else { // user is not created before
            wrongPasswordLabel.setVisible(true);
        }
    }

    @FXML
    void signUpButtonOnAction(ActionEvent event) {
        resetVisibility();
        String username = usernameTextField.getText();
        String password = passwordField.getText();
        if (!DBHandler.doesPersonExists(username)) {
            DBHandler.addPerson(new Person(username, password));
            Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            switchToMainMenuScene(currentStage);
        }
        else { // user already exists
            userExistsLabel.setVisible(true);
        }
    }

    private void switchToMainMenuScene(Stage currentStage) {
        try {
            Parent root =
                FXMLLoader.load(getClass().getResource(Config.retrieveProperty("MAIN_MENU")));
            Scene scene = new Scene(root);
            currentStage.setScene(scene);
            currentStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * reset labels visibility
     */
    private void resetVisibility() {
        userExistsLabel.setVisible(false);
        wrongPasswordLabel.setVisible(false);
    }
}