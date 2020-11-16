package game.classed;

import javax.swing.*;
import java.awt.*;

public class GameFrame extends JFrame {



    //configuration
    private static Configuration config = new Configuration();

    private static int width = 0;
    private  static int height = 0;

    /**
     * method get window params from preset in property file
     *
     */
    private void initConfiguration(){
        config.loadConfiguration();
        width = config.getWidth();
        height = config.getHeight();

    }

    public GameFrame()  {
        initConfiguration(); //Call config, config -> loads from props
    }

    public static void main(String[] args) {

        GameFrame gameFrame = new GameFrame(); // here we create out game frame
        GamePanel gameContext = new GamePanel(config); //init panel and pass config variables to it


        gameFrame.add(gameContext);
        gameFrame.setSize(width, height);
        gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gameFrame.setVisible(true);
        gameFrame.setTitle("Game of Live");
        gameFrame.setBackground(Color.BLACK);
        gameFrame.setLocation(100, 30);

    }
}
