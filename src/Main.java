import java.util.Scanner;
import java.util.Set;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;

// NOTE
// Matrix (user 7x7;)Ships(1x3; 1x2 2; 1x1 4;)
// 
// 
public class Main {
    public static void main(String[] args) throws Exception {
        Random rand = new Random();
        Scanner sc = new Scanner(System.in);
        // filling field
        int s = 9;
        String[][] field = new String[s][s];
        for (int i = 0; i < s; i++) {
            for (int j = 0; j < s; j++) {
                field[i][j] = "0";
            }
        }
        // x3 ship
        int r1 = 0;
        int c1 = 0;
        int randDir = rand.nextInt(1, 3);
        System.out.println(randDir); // log
        if (randDir == 1)// Y
        {
            r1 = rand.nextInt(1, 6);
            c1 = rand.nextInt(1, 6);
            for (int i = 0; i < 3; i++) {
                field[r1 + i][c1] = "X";
                field[r1 + i][c1 - 1] = "^";
                field[r1 + i][c1 + 1] = "^";
            }
            field[r1 - 1][c1] = "^";
            field[r1 - 1][c1 + 1] = "^";
            field[r1 - 1][c1 - 1] = "^";
            field[r1 + 3][c1] = "^";
            field[r1 + 3][c1 - 1] = "^";
            field[r1 + 3][c1 + 1] = "^";
        } 
        else if (randDir == 2) {// X
            r1 = rand.nextInt(1,6);
            c1 = rand.nextInt(1,6);
            for (int i = 0; i < 3; i++) {
                field[r1][c1 + i] = "X";
                //
                field[r1 - 1][c1 + i] = "^";
                field[r1 + 1][c1 + i] = "^";
            }
            field[r1][c1 - 1] = "^";
            field[r1 - 1][c1 - 1] = "^";
            field[r1 + 1][c1 - 1] = "^";
            field[r1][c1 + 3] = "^";
            field[r1 - 1][c1 + 3] = "^";
            field[r1 + 1][c1 + 3] = "^";
        }
        // output field 9x9
        // for (int i = 0; i < s; i++) {
        //     for (int j = 0; j < s; j++) {
        //         System.out.print(field[i][j] + " ");
        //     }
        //     System.out.println();
        // }
        // output field 7x7
        for (int i = 1; i < s - 1; i++) {
            for (int j = 1; j < s -1; j++) {
                System.out.print(field[i][j] + " ");
            }
            System.out.println();
        }

    }
}
