package controller;

import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import util.Config;

public class MapViewController {
    public final int mapRowCount = 13;
    public final int mapColumnCount = 7;

    @FXML private GridPane mapGrid;
    private ImageView friendlyKingTower;

    @FXML
    public void initialize() {
        makeMapBaseField();
//        addTowersToMap();
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
        mapGrid.add(friendlyKingTower, 3, 11);
    }
}
