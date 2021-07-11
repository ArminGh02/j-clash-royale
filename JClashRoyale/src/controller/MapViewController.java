package controller;

import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import util.Config;

public class MapViewController {
    public final int mapRowCount = 32;
    public final int mapColumnCount = 18;

    @FXML private GridPane mapGrid;

    @FXML
    public void initialize() {
        for (int i = 0; i < mapRowCount; i++)
            for (int j = 0; j < mapColumnCount; j++) {
                String rebuiltImage = "";
                if ((i + j) % 2 == 1)
                    rebuiltImage = "2";
                mapGrid.add(new ImageView(Config.retrieveProperty("FULL_GRASS_IMAGE" + rebuiltImage)), j, i);
                if (i == 0)
                    mapGrid.add(new ImageView(Config.retrieveProperty("TOP_GRASS_IMAGE" + rebuiltImage)), j, i);
                else if (j == 0)
                    mapGrid.add(new ImageView(Config.retrieveProperty("LEFT_GRASS_IMAGE" + rebuiltImage)), j, i);
                else if (j == mapColumnCount - 1)
                    mapGrid.add(new ImageView(Config.retrieveProperty("RIGHT_GRASS_IMAGE" + rebuiltImage)), j, i);
                else if (i == mapRowCount - 1)
                    mapGrid.add(new ImageView(Config.retrieveProperty("BOTTOM_GRASS_IMAGE" + rebuiltImage)), j, i);
            }
        mapGrid.add(new ImageView(Config.retrieveProperty("TOP_LEFT_GRASS_IMAGE")), 0, 0);
        String rebuiltImage = "";
        if (mapColumnCount % 2 == 0)
            rebuiltImage = "2";
        mapGrid.add(new ImageView(Config.retrieveProperty("TOP_RIGHT_GRASS_IMAGE" + rebuiltImage)), mapColumnCount - 1, 0);
        rebuiltImage = "";
        if (mapRowCount % 2 == 0)
            rebuiltImage = "2";
        mapGrid.add(new ImageView(Config.retrieveProperty("BOTTOM_LEFT_GRASS_IMAGE" + rebuiltImage)), 0, mapRowCount - 1);
        rebuiltImage = "";
        if ((mapRowCount + mapColumnCount) % 2 == 1)
            rebuiltImage = "2";
        mapGrid.add(new ImageView(Config.retrieveProperty("BOTTOM_RIGHT_GRASS_IMAGE" + rebuiltImage)), mapColumnCount - 1, mapRowCount - 1);

        int waterRow = (mapRowCount - 2) / 2;
        for (int j = 0; j < mapColumnCount; j++) {
            mapGrid.add(new ImageView(Config.retrieveProperty("TOP_WATER_IMAGE")), j, waterRow);
            mapGrid.add(new ImageView(Config.retrieveProperty("BOTTOM_WATER_IMAGE")), j, waterRow + 1);
        }

        mapGrid.add(new ImageView(Config.retrieveProperty("TOP_BRIDGE_IMAGE")), 3, waterRow);
        mapGrid.add(new ImageView(Config.retrieveProperty("BOTTOM_BRIDGE_IMAGE")), 3, waterRow + 1);
        mapGrid.add(new ImageView(Config.retrieveProperty("TOP_BRIDGE_IMAGE")), mapColumnCount - 4, waterRow);
        mapGrid.add(new ImageView(Config.retrieveProperty("BOTTOM_BRIDGE_IMAGE")), mapColumnCount - 4, waterRow + 1);
    }
}
