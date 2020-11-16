package game.classed;


public class Engine {

    byte[][] previosWorldStapshot;
    byte[][] currentWorldSnapshot;
    int numberOfXXXCells;
    int numberOfYYYCells;

    public Engine(int numberOfXXXCells, int numberOfYYYCells) {
        this.numberOfXXXCells = numberOfXXXCells;
        this.numberOfYYYCells = numberOfYYYCells;
    }

    public byte[][] makeOneStep(byte[][] cellBoard) {

        currentWorldSnapshot = cellBoard;


        byte[][] output = new byte[numberOfXXXCells][numberOfYYYCells];

        for (int y = 0; y < numberOfYYYCells; y++) {
            for (int x = 0; x < numberOfXXXCells; x++) {

                //look around
                int aliveCellsAround = countAliveNeighbours(x, y);
                int currentCellValue = currentWorldSnapshot[x][y];


                //create next iteration of board
                //if cellValue == 0 its dead, if cellValue == 0 alive
                byte newCellValue = 0;

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
            }
        }
        previosWorldStapshot = cellBoard;
        //isGameStopped();
        return output;
    }


    public int countAliveNeighbours(int x, int y) {
        int count = 0;

        if (getBoardValue(x - 1, y - 1) != 0) count++;
        if (getBoardValue(x, y - 1) != 0) count++;
        if (getBoardValue(x + 1, y - 1) != 0) count++;

        if (getBoardValue(x - 1, y) != 0) count++;
        //do not count getBoardValue(x,y)
        if (getBoardValue(x + 1, y) != 0) count++;

        if (getBoardValue(x - 1, y + 1) != 0) count++;
        if (getBoardValue(x, y + 1) != 0) count++;
        if (getBoardValue(x + 1, y + 1) != 0) count++;

        return count;

    }

    private int getBoardValue(int x, int y) {

        x = x < 0 ? numberOfXXXCells - 1 : x; //avoid array out of border by X axis left; teleport x to the end of row;
        x = x > numberOfXXXCells - 1 ? 0 : x; //avoid array out of border by X axis right; teleport x to the start of row;
        y = y < 0 ? numberOfYYYCells - 1 : y; // avoid array out of border by Y axis left; teleport y to the end of column;
        y = y > numberOfYYYCells - 1 ? 0 : y; //avoid array out of border by Y axis right; teleport y to the start of column;

        return currentWorldSnapshot[x][y];
    }

   /* public boolean isGameStopped() {

        boolean isStopped = false;

        if (Arrays.equals(previosWorldStapshot, currentWorldSnapshot))
            isStopped =  true;

        System.out.println(isStopped);
        return isStopped;
    }*/
}
