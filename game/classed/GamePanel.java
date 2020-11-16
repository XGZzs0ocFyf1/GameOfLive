package game.classed;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.ThreadLocalRandom;

public class GamePanel extends JPanel implements ActionListener {

    private int width = 0;
    private int height = 0;
    private int cellSize = 0;
    private int numberOfXXXCells = 0; //multiple X in word instread of NumberOfXCells
    private int numberOfYYYCells = 0;// due to fast visual distinguish X and Y variables
    private byte[][] cellBoard;
    private boolean hasGrid = false;
    private boolean isGameEnds = false;
    private int repaintSpeed = 50; // time to refresh frame in ms
    private Engine engine;
    private Configuration configuration;


    public GamePanel(Configuration config) {
        this.configuration = config;
        width = config.getWidth();
        height = config.getHeight();
        cellSize = config.getCellSize();
        numberOfXXXCells = config.getNumberOfXXXCells();
        numberOfYYYCells = config.getNumberOfYYYCells();
        repaintSpeed = config.getRefreshDelay();
        cellBoard = new byte[numberOfXXXCells][numberOfYYYCells];
        engine = new Engine(numberOfXXXCells, numberOfYYYCells);
        setSize(width, height);
        setLayout(null);
        setBackground(Color.BLACK);

        //set the start position
        setStartPos();
        //shedulled execution every repaintSpeed ms
        new Timer(repaintSpeed, this).start();

    }


    //here we draw all our stuff
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        //grid
        if (configuration.isHasGrid() == true) {
            if (cellSize >= 5) { //disablse greed for saving CPU time
                drawGrid(g);
            }
        }
        //cells
        drawBoard(g);
    }


    //create random cells
    private void spawn() {
        if (!isGameEnds) {
            for (int x = 0; x < numberOfXXXCells; x++) {
                for (int y = 0; y < numberOfYYYCells; y++) {

                    byte randomNumber = (byte) ThreadLocalRandom.current().nextInt(2);
                    cellBoard[x][y] = randomNumber == 0 ? (byte) 0 : (byte) 1;
                }
            }
            isGameEnds = false;
        }
    }


    //draw each cell if it alive with color in board
    private void drawBoard(Graphics g) {
        g.setColor(new Color(199, 233, 119));
        for (int x = 0; x < numberOfXXXCells; x++) {
            for (int y = 0; y < numberOfYYYCells; y++) {
                if (cellBoard[x][y] != 0)
                    g.fillRect(x * cellSize, y * cellSize, cellSize, cellSize);
            }
        }

    }


    //redraw by timer (see at constructor)
    @Override
    public void actionPerformed(ActionEvent e) {

        //generate world map in the next time moment
        cellBoard = engine.makeOneStep(cellBoard);

        //refresh JPanel
        repaint();

    }

    private void setStartPos() {
        if (configuration.getGameType().equals("spawn"))
            spawn(); //generate random
        else
            setGlider();// generate one glider
    }

    private void setGlider() {
        setXY(2, 0);
        setXY(2, 1);
        setXY(2, 2);
        setXY(0, 1);
        setXY(1, 2);
    }

    public void setXY(int x, int y) {
        cellBoard[x][y] = 1;
    }


    //draws cell grid on the panel. spend a lot of CPU time
    private void drawGrid(Graphics g) {
        g.setColor(new Color(37, 61, 50, 165)); //
        for (int i = 0; i < cellBoard.length; i++) {
            g.drawLine(0, i * cellSize, width, i * cellSize); //draw horizontal line
            g.drawLine(i * cellSize, 0, i * cellSize, height); //draw vertical line

        }
    }

}
