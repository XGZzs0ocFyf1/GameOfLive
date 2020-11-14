package gamefield;

public class GameField {


    //game field params
    private static final int WIDTH = 70;
    private static final int HEIGHT = 20;

    //number of circles in the game
    private static final int NUMBER_OF_STEPS = 1000;

    //main game panel
    private static int[][] board = new int[WIDTH][HEIGHT];



    public static void main(String[] args) throws InterruptedException {
        setStartPos();


        int i = 0;

        //number of steps
        while (i < NUMBER_OF_STEPS) {
            i++;
            drawBoard();
            board = makeOneStep();
            Thread.sleep(400); //custom wait for slowling console spikes

        }
    }


    /**
     * @param x - horizontal coord in matrix (increase direction from left to right)
     * @param y - vertical coord in matric (increase direction from to to bottom)
     */
    public static void setXY(int x, int y){
        board[x][y] = 1;
    }

    //sets glider formation (its move bottom right)
    //more bottom than right
    private static void setGlider(){
        setXY(2,0);
        setXY(2,1);
        setXY(2,2);
        setXY(0,1);
        setXY(1,2);
    }

    private static void setStartPos() {
        setGlider();
    }


    /***
     * Method get x and y coords of  board[x][y], "move" coords, that are cross
     * the virtual border of our view to the another edge of our board.
     * In common sense we have not board, but torus, because board top edge
     * connected with bottom edge, and left edge connect to right edge.
     *
     * @param x horizontal coord (direction from left to right)
     * @param y vertical coord  (direction from top to botton)
     * @return value of board[x][y]
     */
    private static int getBoardValue(int x, int y) {

        x = x < 0 ? WIDTH - 1 : x; //avoid array out of border by X axis left; teleport x to the end of row;
        x = x > WIDTH - 1 ? 0 : x; //avoid array out of border by X axis right; teleport x to the start of row;
        y = y < 0 ? HEIGHT - 1 : y; // avoid array out of border by Y axis left; teleport y to the end of column;
        y = y > HEIGHT - 1 ? 0 : y; //avoid array out of border by Y axis right; teleport y to the start of column;

        // System.out.println("[" + x + "," + y + "] = " + board[x][y] + ";"); //todo: debug
        return board[x][y];
    }


    //method count all neighbors of non dead ( board[x][y] != 0) cell
    public static int countAliveNeighbours(int x, int y) {
        int count = 0;


        if (getBoardValue(x - 1, y - 1) != 0) count++;
        if (getBoardValue(x, y - 1) != 0) count++; //+
        if (getBoardValue(x + 1, y - 1) != 0) count++; //+


        if (getBoardValue(x - 1, y) != 0) count++;
        //do not count getBoardValue(x,y)
        if (getBoardValue(x + 1, y) != 0) count++; //+

        if (getBoardValue(x - 1, y + 1) != 0) count++;
        if (getBoardValue(x, y + 1) != 0) count++;
        if (getBoardValue(x + 1, y + 1) != 0) count++;

        return count;

    }

    /**
     * method kills and revive cells in case of Game of life rules
     * current rules is:
     * if alive, then kill if have one neighbor;
     * if alive, and have 2 or 3 neighbor - then alive;
     * if alive, and have more than 3 neighbor - then dead;
     * if dead (empty cell) and have 3 neighbor - then alive;
     *
     * recount board[x][y]
     * @return recounted version on board[x][y]
     */
    public static int[][] makeOneStep() {
        int[][] output = new int[WIDTH][HEIGHT];

        for (int y = 0; y < HEIGHT; y++) {
            for (int x = 0; x < WIDTH; x++) {

                //look around
                int aliveCellsAround = countAliveNeighbours(x, y);
                int currentCellValue = board[x][y];

                //debug
              /*  String cellType = currentCellValue == 0 ? "мертвая " : "живая";
                System.out.print("Я " + cellType + " клетка на " + "[" + x + "][" + y + "] ;");
                System.out.print("оглядываюсь:");
                System.out.format("вижу %s соседей; поэтому ", aliveCellsAround);
                */

                //create next iteration of board
                //if cellValue == 0 its dead, if cellValue == 0 alive
                int newCellValue = 0;

                //if cell was dead in last circle
                if (currentCellValue == 0) {


                    //check reproduction case (rule 4)
                    if (aliveCellsAround == 3) {

                        newCellValue = 1;
                     //   System.out.println("клетка была репродуцирована");
                    }
                } else { //if cell was alive in last circle

                    //overpopulation case (rule 3)
                    if (aliveCellsAround > 3) {
                        newCellValue = 0;
                       // System.out.println("клетка была уничтожена перенаселением");
                    }


                    //widh 2 or 3 neighbours case (rule 2)
                    if (aliveCellsAround >= 2 & aliveCellsAround <= 3) {
                        newCellValue = 1;
                      //  System.out.println("клетка выжила по правилу 2 <= x <= 3");
                    }

                    //underpopulation case (rule 1)
                    if (aliveCellsAround < 2) {
                        newCellValue = 0;
                       // System.out.println("клетка была уничтожена при уменьшении населения");
                    }

                }
                //todo: handle game stop cases
                /**
                 * These rules, which compare the behavior of the automaton to real life, can be condensed into the following:
                 *
                 * Any live cell with two or three live neighbours survives.
                 * Any dead cell with three live neighbours becomes a live cell.
                 * All other live cells die in the next generation. Similarly, all other dead cells stay dead.
                 */

                output[x][y] = newCellValue;
              //  System.out.println();
            }
        }

        return output;
    }

    /**
     * draws game board. alive cells (board[x][y] value = 1 ) drawing by special character ፠
     */
    public static void drawBoard() {
        for (int y = 0; y < HEIGHT; y++) {
            for (int x = 0; x < WIDTH; x++) {
                String output = board[x][y] == 0 ? " " :""+ (char)4960;
                System.out.print(output);


            }
            System.out.println();

        }
    }


}
