import java.util.Scanner;
import java.util.Random;
import java.util.ArrayList;
// NOTE
// Matrix (user 7x7;)Ships(1x3; 1x2 2; 1x1 4;)
// 0 - free X-ship ^-close to ship
// X - hit, # - miss, %-sunk  
public class Main {
    public static void main(String[] args) throws Exception {

        Scanner sc = new Scanner(System.in);
        int s = 9;
        // String[][] hidenField = fieldConstructor();
        // show where the ships
        // for (int i = 1; i < s - 1; i++) {
        //     for (int j = 1; j < s - 1; j++) {
        //         System.out.print(hidenField[i][j] + " ");
        //     }
        //     System.out.println();
        // }
        //
        //filling playField
        // String[][] playField = new String[s][s];
        // for (int i = 0; i < s; i++) {
        //     for (int j = 0; j < s; j++) {
        //         playField[i][j] = "0";
        //     }
        // }
        // playin
        int x = 0;
        while (x == 0)
        {
            String[][] hidenField = fieldConstructor();
            for (int i = 1; i < s - 1; i++) {
                for (int j = 1; j < s - 1; j++) {
                    System.out.print(hidenField[i][j] + " ");
                }
                System.out.println();
            }
            String[][] playField = new String[s][s];
            for (int i = 0; i < s; i++) {
                for (int j = 0; j < s; j++) {
                    playField[i][j] = "0";
                }
            }
            System.out.println("Enter username ...");
            String username = sc.next();
            clear();
            int turn = 0;
            int hit = 0;
            int shotCol = 0;
            int shotRow = 0;
            ArrayList<Integer> turns = new ArrayList<Integer>();
            ArrayList<String> players = new ArrayList<String>();
            String battleLog = "Let's get started!";
            while (hit != 11) {

                showField(battleLog, playField);
                System.out.println("Choose coordinate to shoot...");
                shotCol = sc.nextInt();
                shotRow = sc.nextInt();
                clear();
                if (shotCol > 7 || shotCol < 1 || shotRow > 7 || shotRow < 1) {
                    System.out.println("You can only shoot on coordinates from 1 to 7");
                    System.out.println("Enter anything to continue...");
                    String pause = sc.next();
                    clear();
                    continue;
                }
                if (!playField[s - shotRow - 1][shotCol].equals("0")
                        || playField[s - shotRow][shotCol].equals("%")
                        || playField[s - shotRow - 2][shotCol].equals("%")
                        || playField[s - shotRow - 1][shotCol - 1].equals("%")
                        || playField[s - shotRow - 1][shotCol + 1].equals("%")
                        || playField[s - shotRow][shotCol + 1].equals("%")
                        || playField[s - shotRow][shotCol - 1].equals("%")
                        || playField[s - shotRow - 2][shotCol - 1].equals("%")
                        || playField[s - shotRow - 2][shotCol + 1].equals("%")) {
                    System.out.println("Why shooting there my boi?");
                    System.out.println("Enter anything to continue...");
                    String pause = sc.next();
                    clear();
                    continue;
                }
                if (hidenField[s - shotRow - 1][shotCol].equals("X")) {
                    playField[s - shotRow - 1][shotCol] = "X";
                    hidenField[s - shotRow - 1][shotCol] = "%";
                    battleLog = "Hit!";
                    turn++;
                    //
                    if (!hidenField[s - shotRow - 1][shotCol].equals("X")
                            && !hidenField[s - shotRow - 2][shotCol].equals("X")
                            && !hidenField[s - shotRow - 1][shotCol - 1].equals("X")
                            && !hidenField[s - shotRow - 1][shotCol + 1].equals("X")) {
                        battleLog = "Sunk!";
                        for (int i = 1; i < s - 1; i++) {
                            for (int j = 1; j < s - 1; j++) {
                                if (hidenField[i][j].equals("%"))
                                    playField[i][j] = "%";
                            }
                        }
                        // 
                        // playField[s - shotRow - 1][shotCol] = "%";
                        // if (hidenField[s - shotRow + 1][shotCol].equals("%"))
                        //     playField[s - shotRow + 1][shotCol] = "%";
                        // if (hidenField[s - shotRow][shotCol].equals("%"))
                        //     playField[s - shotRow][shotCol] = "%";
                        // // if (hidenField[s - shotRow - 2][shotCol].equals("%"))
                        // //     playField[s - shotRow - 2][shotCol] = "%";
                        // // if (hidenField[s - shotRow - 3][shotCol].equals("%"))
                        // //     playField[s - shotRow - 3][shotCol] = "%";
                        // // if (hidenField[s - shotRow][shotCol - 2].equals("%"))
                        // //     playField[s - shotRow][shotCol - 2] = "%";
                        // if (hidenField[s - shotRow][shotCol - 1].equals("%"))
                        //     playField[s - shotRow][shotCol - 1] = "%";
                        // if (hidenField[s - shotRow][shotCol + 1].equals("%"))
                        //     playField[s - shotRow][shotCol + 1] = "%";
                        // if (hidenField[s - shotRow][shotCol + 2].equals("%"))
                        //     playField[s - shotRow][shotCol + 2] = "%";
                        
                    }
                    hit++;
                } else if (!hidenField[s - shotRow - 1][shotCol].equals("X")) {
                    playField[s - shotRow - 1][shotCol] = ".";
                    battleLog = "Miss!";
                    turn++;
                }
            }
            // output field 7x7
            for (int i = 1; i < s - 1; i++) {
                for (int j = 1; j < s - 1; j++) {
                    System.out.print(playField[i][j] + " ");
                }
                System.out.println();
            }
            System.out.println("Congratulations! " + username + ", you won with just " + turn + " turns");
            turns.add(turn);
            players.add(username);
            System.out.println(turn + " hg: " + username );
            System.out.println("Do you want to continue plaing|0| or want to exit|1|");
            x = sc.nextInt();
            if (x == 1) {
                for (int i = 0; i < turns.size(); i++) {
                    System.out.println(players.get(i) + "|   " + turns.get(i));
                }
            }
            System.out.println();
            System.out.println("Print anything...");
            String pause = sc.next();

        }
        sc.close();
    }

    //
    public static void showField(String log, String[][] field) {
        System.out.println(log);
        for (int i = 1; i < 9 - 1; i++) {
            for (int j = 1; j < 9 - 1; j++) {
                System.out.print(field[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static void clear() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    //
    // Creating matrix with ships
    //
    public static String[][] fieldConstructor() {
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
        row = rand.nextInt(1, 6);
        col = rand.nextInt(1, 6);
        if (randDir == 1)// Y
        {
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
