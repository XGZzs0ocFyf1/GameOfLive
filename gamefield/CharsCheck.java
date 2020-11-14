package gamefield;


/**
 * This class prints table of chars
 * to select one of them you should
 * use its address like linenumber and
 * its number at line in table
 *
 * for example current char 4960 is 60 character
 * in 49 line of drawing table
 *
 * insert this char (if you want another)
 * to gamefield.GameField::drawBoard
 */
public class CharsCheck {



    //just simple static printer
    public static void main(String[] args) {
        System.out.println((char)4960);
        System.out.println();
        System.out.println();

        for (int i = 0; i < 5000; i++) {

            System.out.print((char) i);
            if (i % 100 == 0) {

                System.out.println();
                System.out.print(i / 100 + " ");
            }

        }
    }
}
