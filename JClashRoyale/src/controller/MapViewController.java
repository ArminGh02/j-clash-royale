package controller;

import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import util.Config;

public class MapViewController {
    public final int mapRowCount = 13;
    public final int mapColumnCount = 7;

    @FXML private GridPane mapGrid;
    @FXML private AnchorPane basePane;
    private ImageView friendlyKingTower;
    private ImageView friendlyPrinceTowerL, friendlyPrinceTowerR;
    private ImageView enemyKingTower;
    private ImageView enemyPrinceTowerL, enemyPrinceTowerR;

    @FXML
    public void initialize() {
        makeMapBaseField();
        addTowersToMap();
    }

    /**
     * add map base images to the grid pane
     */
    private void makeMapBaseField() {
        for (int i = 0; i < mapRowCount; i++)
            for (int j = 0; j < mapColumnCount; j++) {
                if (i == 0 && j == 0)
                    mapGrid.add(new ImageView(Config.retrieveProperty("TOP_LEFT_GRASS_IMAGE")), j, i);
                else if (i == 0 && j == mapColumnCount - 1)
                    mapGrid.add(new ImageView(Config.retrieveProperty("TOP_RIGHT_GRASS_IMAGE")), j, i);
                else if (i == mapRowCount - 1 && j == 0)
                    mapGrid.add(new ImageView(Config.retrieveProperty("BOTTOM_LEFT_GRASS_IMAGE")), j, i);
                else if (i == mapRowCount - 1 && j == mapColumnCount - 1)
                    mapGrid.add(new ImageView(Config.retrieveProperty("BOTTOM_RIGHT_GRASS_IMAGE")), j, i);
                else if (i == 0)
                    mapGrid.add(new ImageView(Config.retrieveProperty("TOP_GRASS_IMAGE")), j, i);
                else if (j == 0)
                    mapGrid.add(new ImageView(Config.retrieveProperty("LEFT_GRASS_IMAGE")), j, i);
                else if (j == mapColumnCount - 1)
                    mapGrid.add(new ImageView(Config.retrieveProperty("RIGHT_GRASS_IMAGE")), j, i);
                else if (i == mapRowCount - 1)
                    mapGrid.add(new ImageView(Config.retrieveProperty("BOTTOM_GRASS_IMAGE")), j, i);
                else
                    mapGrid.add(new ImageView(Config.retrieveProperty("FULL_GRASS_IMAGE")), j, i);
            }

        int waterRow = mapRowCount / 2;
        mapGrid.add(new ImageView(Config.retrieveProperty("LEFT_WATER_IMAGE")), 0, waterRow);
        mapGrid.add(new ImageView(Config.retrieveProperty("RIGHT_WATER_IMAGE")), mapColumnCount - 1, waterRow);
        for (int j = 1; j < mapColumnCount - 1; j++)
            mapGrid.add(new ImageView(Config.retrieveProperty("FULL_WATER_IMAGE")), j, waterRow);

        mapGrid.add(new ImageView(Config.retrieveProperty("BRIDGE_IMAGE")), 1, waterRow);
        mapGrid.add(new ImageView(Config.retrieveProperty("BRIDGE_IMAGE")), mapColumnCount - 2, waterRow);
    }

    /**
     * add tower images to the grid pane
     */
    private void addTowersToMap() {
        friendlyKingTower = new ImageView(Config.retrieveProperty("FRIENDLY_KING_TOWER_IMAGE"));
        friendlyPrinceTowerL = new ImageView(Config.retrieveProperty("FRIENDLY_PRINCE_TOWER_IMAGE"));
        friendlyPrinceTowerR = new ImageView(Config.retrieveProperty("FRIENDLY_PRINCE_TOWER_IMAGE"));
        enemyKingTower = new ImageView(Config.retrieveProperty("ENEMY_KING_TOWER_IMAGE"));
        enemyPrinceTowerL = new ImageView(Config.retrieveProperty("ENEMY_PRINCE_TOWER_IMAGE"));
        enemyPrinceTowerR = new ImageView(Config.retrieveProperty("ENEMY_PRINCE_TOWER_IMAGE"));

        int middleColumn = mapColumnCount / 2;
        mapGrid.add(friendlyKingTower, middleColumn, mapRowCount - 1);
        mapGrid.add(friendlyPrinceTowerL, 1, mapRowCount - 3);
        mapGrid.add(friendlyPrinceTowerR, mapColumnCount - 2, mapRowCount - 3);
        mapGrid.add(enemyKingTower, middleColumn, 0);
        mapGrid.add(enemyPrinceTowerL, 1, 1);
        mapGrid.add(enemyPrinceTowerR, mapColumnCount - 2, 1);
    }

    /**
     * player clicked on map and wants to deploy card
     * @param event happened event
     */
    @FXML
    void gridPaneMouseClicked(MouseEvent event) {
        double x = event.getSceneX(), y = event.getSceneY();
        System.out.println("x: " + x + "\ny: " + y);
        ImageView imageView = new ImageView(Config.retrieveProperty("BARBARIAN_UP"));
        imageView.setX(x - 32);
        imageView.setY(y - 32);
        basePane.getChildren().add(imageView);
    }
}
