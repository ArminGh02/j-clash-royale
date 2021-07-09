package controller;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import util.Config;

/**
 * LoginController class, handles interactions and events in login view
 * @version 1.0
 * @author Adibov & Armin Gh
 */
public class LoginController {
    @FXML
    private TextField usernameTextField;

    @FXML
    private PasswordField passwordField;

    private static String username;
    private static String password;

    @FXML
    void loginButtonOnAction(ActionEvent event) {
        String username = usernameTextField.getText();
        String password = passwordField.getText();
        if (true) { // TODO: replace "true" with DataBaseHandler.userExists(username, password)
            Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            switchToMainMenuScene(currentStage);
        } else { // user is not created before
            usernameTextField.setText("Username or password is incorrect");
            usernameTextField.selectAll();
            usernameTextField.requestFocus();
            passwordField.clear();
        }
    }

    @FXML
    void signUpButtonOnAction(ActionEvent event) {
        String username = usernameTextField.getText();
        String password = passwordField.getText();
        if (true) { // TODO: replace "true" with !DataBaseHandler.userExists(username, password)
            // TODO: add user to existing users' list in this line
            Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            switchToMainMenuScene(currentStage);
        } else { // user already exists
            usernameTextField.setText("User already exists");
            usernameTextField.selectAll();
            usernameTextField.requestFocus();
            passwordField.clear();
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
}
