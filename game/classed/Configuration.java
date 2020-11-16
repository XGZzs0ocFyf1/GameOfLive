package game.classed;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;


/**
 * Configuration file used in training purposes
 *
 */
public class Configuration {
    private  final String FILENAME = "config/game.properties";
    private  int width = 600;
    private  int height = 600;
    private  int cellSize = 7;
    private int numberOfSteps = 0;
    private String gameType = "";
    private boolean hasGrid = false;
    private int refreshDelay = 100; //delay between screen repaint


    //Upload configuration from FILENAME file
    public  void loadConfiguration() {
        try (InputStream inputStream = new FileInputStream(FILENAME)) {

            Properties properties = new Properties();
            properties.load(inputStream);
            height = Integer.parseInt(properties.getProperty("game.field.height"));
            width = Integer.parseInt(properties.getProperty("game.field.width"));
            cellSize = Integer.parseInt(properties.getProperty("game.field.cell.size"));
            gameType = properties.getProperty("game.type");
            hasGrid = Boolean.parseBoolean(properties.getProperty("game.field.hasgrid"));
            refreshDelay = Integer.parseInt(properties.getProperty("game.field.refresh.delay"));

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public  int getWidth() {
        return width;
    }

    public  int getHeight() {
        return height;
    }

    public  int getCellSize() {
        return cellSize;
    }

    public int getNumberOfSteps() {
        return numberOfSteps;
    }

    //number of horizontal(x axis) cells
    public int getNumberOfXXXCells() {
        return cellSize == 0 ? 0 :  width / cellSize;
    }

    //number of vertical(y axis) cells
    public int getNumberOfYYYCells() {
        return cellSize == 0 ? 0 :  height / cellSize;
    }

    public String getGameType() {
        return gameType;
    }

    public boolean isHasGrid() {
        return hasGrid;
    }

    public int getRefreshDelay() {
        return refreshDelay;
    }
}
