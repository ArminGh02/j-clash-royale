package view;

import com.sun.tools.hat.internal.model.Root;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import util.Config;

import java.io.IOException;

/**
 * ViewManager class, manages views. Like changing/loading a view
 * @author Adibov & Armin Gh
 * @version 1.0
 */
public class ViewManager extends Application {
    private static Stage currentStage;
    private static FXMLLoader currentFXMLLoader;

    /**
     * class main method to make it runnable
     * @param args program args
     */
    public static void main(String[] args) {
        launch(args);
    }

    /**
     * override start method
     * @param stage primary stage
     */
    @Override
    public void start(Stage stage) {
        currentStage = stage;
        currentStage.setResizable(false);
        loadLoginView();
    }

    /**
     * load login view
     */
    public static void loadLoginView() {
        currentFXMLLoader = new FXMLLoader(ViewManager.class.getResource(Config.retrieveProperty("LOGIN_VIEW")));
        updateScene();
    }

    /**
     * load main menu view
     */
    public static void loadMainMenuView() {
        currentFXMLLoader = new FXMLLoader(ViewManager.class.getResource(Config.retrieveProperty("MAIN_MENU_VIEW")));
        updateScene();
    }

    /**
     * update the current scene
     */
    private static void updateScene() {
        try {
            Parent root = currentFXMLLoader.load();
            currentStage.setScene(new Scene(root));
            currentStage.show();
        }
        catch (IOException exception) {
            exception.printStackTrace();
        }
    }
}
