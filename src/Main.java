import java.util.Scanner;
import java.util.Random;

// NOTE
// Matrix (user 7x7;)Ships(1x3; 1x2 2; 1x1 4;)
// 
// 
public class Main 
{
    public static void main(String[] args) throws Exception 
    {

        Scanner sc = new Scanner(System.in);
        int s = 9;
        String[][] hidenField = fieldConstructor();

        // output field 7x7
        for (int i = 1; i < s - 1; i++) {
            for (int j = 1; j < s - 1; j++) {
                System.out.print(hidenField[i][j] + " ");
            }
            System.out.println();
        }
    }
    // 
    // Creating matrix with ships
    // 
    public static String[][] fieldConstructor() 
    {
        Random rand = new Random();
        int s = 9;
        String[][] field = new String[s][s];
        for (int i = 0; i < s; i++) {
            for (int j = 0; j < s; j++) {
                field[i][j] = "0";
            }
        }
        // 1x3 ship
        int row = 0;
        int col = 0;
        int randDir = 0;
        randDir = rand.nextInt(1, 3);
        if (randDir == 1)// Y
        {
            row = rand.nextInt(1, 6);
            col = rand.nextInt(1, 6);
            for (int i = 0; i < 3; i++) {
                field[row + i][col] = "X";
                field[row + i][col - 1] = "^";
                field[row + i][col + 1] = "^";
            }
            field[row - 1][col] = "^";
            field[row - 1][col + 1] = "^";
            field[row - 1][col - 1] = "^";
            field[row + 3][col] = "^";
            field[row + 3][col - 1] = "^";
            field[row + 3][col + 1] = "^";
        } else if (randDir == 2) {// X
            row = rand.nextInt(1, 6);
            col = rand.nextInt(1, 6);
            for (int i = 0; i < 3; i++) {
                field[row][col + i] = "X";
                //
                field[row - 1][col + i] = "^";
                field[row + 1][col + i] = "^";
            }
            field[row][col - 1] = "^";
            field[row - 1][col - 1] = "^";
            field[row + 1][col - 1] = "^";
            field[row][col + 3] = "^";
            field[row - 1][col + 3] = "^";
            field[row + 1][col + 3] = "^";
        }
        // 1x2 ships
        row = 0;
        col = 0;
        int ships = 0;
        while (ships != 2) {
            randDir = rand.nextInt(1, 3);
            row = rand.nextInt(1, 7);
            col = rand.nextInt(1, 7);
            if (randDir == 1 && field[row][col].equals("0") && field[row + 1][col].equals("0")) // Y
            {
                for (int i = 0; i < 2; i++) {
                    field[row + i][col] = "X";
                    field[row + i][col - 1] = "^";
                    field[row + i][col + 1] = "^";
                }
                field[row - 1][col] = "^";
                field[row - 1][col + 1] = "^";
                field[row - 1][col - 1] = "^";
                field[row + 2][col] = "^";
                field[row + 2][col - 1] = "^";
                field[row + 2][col + 1] = "^";
                ships += 1;
            } else if (randDir == 2 && field[row][col].equals("0") && field[row][col + 1].equals("0")) {
                for (int i = 0; i < 2; i++) {
                    field[row][col + i] = "X";
                    //
                    field[row - 1][col + i] = "^";
                    field[row + 1][col + i] = "^";
                }
                field[row][col - 1] = "^";
                field[row - 1][col - 1] = "^";
                field[row + 1][col - 1] = "^";
                field[row][col + 2] = "^";
                field[row - 1][col + 2] = "^";
                field[row + 1][col + 2] = "^";
                ships += 1;
            } else {
                continue;
            }
        }
        // 1x1 ships
        ships = 0;
        while (ships != 4) {
            row = rand.nextInt(1, 8);
            col = rand.nextInt(1, 8);
            if (field[row][col].equals("0") == true) {
                field[row][col] = "X";
                field[row][col - 1] = "^";
                field[row][col + 1] = "^";
                field[row - 1][col] = "^";
                field[row + 1][col] = "^";
                field[row - 1][col + 1] = "^";
                field[row - 1][col - 1] = "^";
                field[row + 1][col - 1] = "^";
                field[row + 1][col + 1] = "^";
                ships++;
            }
        }
        return field;
    }
}
