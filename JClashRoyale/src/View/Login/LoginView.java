package View.Login;

import Controller.Config;
import Controller.FileUtils;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * LoginView, handles showing login page to the client
 * @version 1.0
 * @author Adibov & Armin Gh
 */
public class LoginView extends Application {
    private static Stage currentStage;
    private static FXMLLoader currentFXML;

    /**
     * main method, to make this class runnable
     * @param args program args
     */
    public static void main(String[] args) {
        launch(args);
    }

    /**
     * override start method from Application class
     * @param stage given stage
     */
    @Override
    public void start(Stage stage) {
        LoginView.currentStage = stage;
        currentStage.setResizable(false);
        LoginView.showLoginPage();
    }

    /**
     * bring up login view to the current scene
     */
    public static void showLoginPage() {
        LoginView.currentFXML = new FXMLLoader(LoginView.class.getResource(Config.retrieveProperty("LOGIN_VIEW")));
        LoginView.updateScene();
    }

    /**
     * update scene and load the new page
     */
    private static void updateScene() {
        try {
            Pane root = LoginView.currentFXML.load();
            currentStage.setScene(new Scene(root));
            currentStage.show();
        }
        catch (IOException exception) {
            exception.printStackTrace();
        }
    }
}
