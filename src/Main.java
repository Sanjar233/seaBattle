
import java.io.PrintStream;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        String freeSpot = "\u25cb";
        String shotSpot = "\u25ce";
        String sunk = "\u25cf";
        HashMap<String, Integer> coordinatesX = new HashMap<>();
        HashMap<String, Integer> coordinatesY = new HashMap<>();
        coordinatesX.put("a", 1);
        coordinatesX.put("b", 2);
        coordinatesX.put("c", 3);
        coordinatesX.put("d", 4);
        coordinatesX.put("e", 5);
        coordinatesX.put("f", 6);
        coordinatesX.put("g", 7);
        coordinatesX.put("A", 1);
        coordinatesX.put("B", 2);
        coordinatesX.put("C", 3);
        coordinatesX.put("D", 4);
        coordinatesX.put("E", 5);
        coordinatesX.put("F", 6);
        coordinatesX.put("G", 7);
        coordinatesY.put("1", 1);
        coordinatesY.put("2", 2);
        coordinatesY.put("3", 3);
        coordinatesY.put("4", 4);
        coordinatesY.put("5", 5);
        coordinatesY.put("6", 6);
        coordinatesY.put("7", 7);
        Scanner sc = new Scanner(System.in);
        int s = 9;
        // playin
        int x = 0;
        int turn = 0;
        String username = "";
        ArrayList<Integer> turns = new ArrayList<Integer>();
        HashMap<Integer, String> players = new HashMap<>();
        while (x == 0) {
            String[][] hidenField = fieldConstructor();
            ArrayList<Integer> tripleShipCords = new ArrayList<Integer>();
            for (int i = 1; i < s - 1; i++) {
                for (int j = 1; j < s - 1; j++) {
                    if (hidenField[i][j].equals("X3")) {
                        tripleShipCords.add(i);
                        tripleShipCords.add(j);
                    }
                }
            }
            String[][] playField = new String[s][s];
            for (int i = 0; i < s; i++) {
                for (int j = 0; j < s; j++) {
                    playField[i][j] = "0";
                }
            }
            System.out.println("Enter username ...");
            username = sc.next();
            clear();
            turn = 0;
            int hit = 0;
            String keyCol = "";
            String keyRow = "";
            int shotCol = 0;
            int shotRow = 0;
            String battleLog = "";
            System.out.println("Let's get started!");
            while (hit != 11) {
                showField(battleLog, playField);
                System.out.println("Choose X|Y coordinate to shoot...");
                keyCol = sc.next();
                keyRow = sc.next();
                while (coordinatesX.containsKey(keyCol) == false || coordinatesY.containsKey(keyRow) == false) {
                    battleLog = "(> - <)";
                    clear();
                    showField(battleLog, playField);
                    System.out.println();
                    System.out.println("Incorrect coordinates, please try again.");
                    System.out.println("Please use A-G for X, and 1-7 for Y");
                    System.out.println("Choose X|Y coordinate to shoot...");
                    keyCol = sc.next();
                    keyRow = sc.next();
                    clear();
                    showField(battleLog, playField);

                }
                shotCol = coordinatesX.get(keyCol);
                shotRow = coordinatesY.get(keyRow);
                clear();
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
                if (hidenField[s - shotRow - 1][shotCol].contains("X")) {
                    playField[s - shotRow - 1][shotCol] = "X";
                    hidenField[s - shotRow - 1][shotCol] += "%";
                    battleLog = "Hit!";
                    turn++;
                    hit++;
                    // SUNK
                    // for 1x1
                    if (hidenField[s - shotRow - 1][shotCol].equals("X1%")) {
                        playField[s - shotRow - 1][shotCol] = "%";
                        battleLog = "Sunk!";
                    }
                    // for 1x3
                    if (hidenField[tripleShipCords.get(0)][tripleShipCords.get(1)].contains("X3%")
                            && hidenField[tripleShipCords.get(2)][tripleShipCords.get(3)].contains("X3%")
                            && hidenField[tripleShipCords.get(4)][tripleShipCords.get(5)].contains("X3%")) {
                        playField[tripleShipCords.get(0)][tripleShipCords.get(1)] = "%";
                        playField[tripleShipCords.get(2)][tripleShipCords.get(3)] = "%";
                        playField[tripleShipCords.get(4)][tripleShipCords.get(5)] = "%";
                        battleLog = "Sunk!";
                    }
                    // for 1x2
                    if (hidenField[s - shotRow - 1][shotCol - 1].equals("X2%")) {
                        playField[s - shotRow - 1][shotCol] = "%";
                        playField[s - shotRow - 1][shotCol - 1] = "%";
                    }
                    if (hidenField[s - shotRow - 1][shotCol + 1].equals("X2%")) {
                        playField[s - shotRow - 1][shotCol + 1] = "%";
                        playField[s - shotRow - 1][shotCol] = "%";
                    }
                    if (hidenField[s - shotRow][shotCol].equals("X2%")) {
                        playField[s - shotRow][shotCol] = "%";
                        playField[s - shotRow - 1][shotCol] = "%";
                    }
                    if (hidenField[s - shotRow - 2][shotCol].equals("X2%")) {
                        playField[s - shotRow - 2][shotCol] = "%";
                        playField[s - shotRow - 1][shotCol] = "%";
                    }
                    //
                } else if (!hidenField[s - shotRow - 1][shotCol].contains("X")) {
                    playField[s - shotRow - 1][shotCol] = ".";
                    battleLog = "Miss!";
                    turn++;
                }
            }
            // output field 7x7
            battleLog = "Victory!";
            showField(battleLog, playField);
            System.out.println("Congratulations! " + username + ", you won with just " + turn + " turns");
            turns.add(turn);
            players.put(turn, username);
            System.out.println("Do you want to continue plaing|0| or want to exit|1|");
            x = sc.nextInt();
            clear();
            if (x == 1) {
                Collections.sort(turns);
                System.out.println("PLAYERS|TURNS");
                for (int i = 0; i < turns.size(); i++) {
                    System.out.println(players.get(turns.get(i)) + "|" + turns.get(i));
                }
            }
            System.out.println();
            System.out.println("Print anything...");
            String pause = sc.next();
            clear();
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
                field[row + i][col] = "X3";
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
                field[row][col + i] = "X3";
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
                    field[row + i][col] = "X2";
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
                    field[row][col + i] = "X2";
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
                field[row][col] = "X1";
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
