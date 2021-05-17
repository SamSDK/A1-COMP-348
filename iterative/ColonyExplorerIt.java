package iterative;

import java.io.PrintWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.awt.*;

public class ColonyExplorerIt {
    // attributes
    /**
     * 2d array of member objects
     */
    private Member[][] grid;
    /**
     * counts the quantity of colonies found in the given grid
     */
    static int colonyCount;
    /**
     * tracks the size of every individual colony
     */
    static int size;
    /**
     * represents grid's dimensions
     */
    private static int maxRow, maxCol;
    /**
     * keeps track of the total quantity of colonies and their names
     */
    private static ArrayList<String> colonies = new ArrayList<String>();
    /**
     * printwriter object to output information to a text file
     */
    private PrintWriter os = null;

    // constructor
    /**
     * default constructor sets colonycount, size to zero and initializes grid size to random number. fills the grid with random 1's and zero's
     */
    public ColonyExplorerIt() {
        colonyCount = 0;
        size = 0;
        grid = new Member[randomNum()][randomNum()];
        maxRow = grid.length;
        maxCol = grid[1].length;
        fillGrid(grid);
        // output text file
        try {
            os = new PrintWriter(new FileWriter("iterativeResults.txt", true));
        } catch (FileNotFoundException e) {
            System.out.println(e.toString());
        } catch (IOException e) {
            System.out.println(e.toString());
        }
    }

    // helper functions
    /**
     * generates random number between 5 and 20
     * 
     * @return int random number between 5 and 20
     */
    public static int randomNum() {
        Random random = new Random();
        int randomNum = random.nextInt(16) + 5; // generate random number between 5-20
        return randomNum;
    }

    /**
     * fills the grid with 1"s and 0"s in random order
     * 
     * @param grid String[][] 2d array
     */
    public static void fillGrid(Member[][] grid) {
        for (int row = 0; row < maxRow; row++) {
            for (int col = 0; col < maxCol; col++) {
                grid[row][col] = new Member(Integer.toString((randomNum() % 2)));
            }
        }
    }

    
    /** 
     * @param quantityOfColonies int quantity of current colonies
     * @return String represents the label that is to be used next
     */
    public static String generateLabel(int quantityOfColonies) {
        int count = quantityOfColonies % 53;
        String[] setOfChar = { "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R",
                "S", "T", "U", "V", "W", "X", "Y", "Z", "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m",
                "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z" };
        return setOfChar[count];
    }

    
    /** 
     * @param grid grid object being examined
     * @param x int x coordinate
     * @param y int y coordinate
     * @return int size of colony
     */
    public int exploreAndLabelColony(Member[][] grid, int x, int y) {
        int size = 0;
        String currentColony = generateLabel(colonyCount - 1);
        ArrayList<Point> candidates = new ArrayList<>();
        grid[x][y].setValue(currentColony);
        candidates.add(new Point(x, y));
        size++;
        while (!candidates.isEmpty()) {
            Point p = candidates.remove(candidates.size() - 1);
            int row = (int) p.getX();
            int col = (int) p.getY();
            // check north
            if (col > 0) {
                if (grid[row][col - 1].getValue().equals("1")) {
                    grid[row][col - 1].setValue(currentColony);
                    candidates.add(new Point(row, col - 1));
                    size++;
                }
                // check north-east
                if (row + 1 < grid.length) {
                    if (grid[row + 1][col - 1].getValue().equals("1")) {
                        grid[row + 1][col - 1].setValue(currentColony);
                        candidates.add(new Point(row + 1, col - 1));
                        size++;
                    }
                }
                // check north-west
                if (row > 0) {
                    if (grid[row - 1][col - 1].getValue().equals("1")) {
                        grid[row - 1][col - 1].setValue(currentColony);
                        candidates.add(new Point(row - 1, col - 1));
                        size++;
                    }
                }
            }
            // check east
            if (row + 1 < grid.length) {
                if (grid[row + 1][col].getValue().equals("1")) {
                    grid[row + 1][col].setValue(currentColony);
                    candidates.add(new Point(row + 1, col));
                    size++;
                }
            }

            // check west
            if (row > 0) {
                if (grid[row - 1][col].getValue().equals("1")) {
                    grid[row - 1][col].setValue(currentColony);
                    candidates.add(new Point(row - 1, col));
                    size++;
                }
            }

            // check south points
            if (col + 1 < grid[row].length) {
                // check south
                if (grid[row][col + 1].getValue().equals("1")) {
                    grid[row][col + 1].setValue(currentColony);
                    candidates.add(new Point(row, col + 1));
                    size++;
                }
                // check south-east
                if (row + 1 < grid.length) {
                    if (grid[row + 1][col + 1].getValue().equals("1")) {
                        grid[row + 1][col + 1].setValue(currentColony);
                        candidates.add(new Point(row + 1, col + 1));
                        size++;
                    }
                }

                // check south-west
                if (row > 0) {
                    if (grid[row - 1][col + 1].getValue().equals("1")) {
                        grid[row - 1][col + 1].setValue(currentColony);
                        candidates.add(new Point(row - 1, col + 1));
                        size++;
                    }
                }
            }
        }
        return size;
    }
    /**
     * explores the 2d grid and calls exploreAndLabelColony function
     */
    public void explore() {
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j].getValue().equals("1")) {
                    colonyCount++;
                    size = 1;
                    int colonySize = exploreAndLabelColony(this.grid, i, j);
                    colonies.add(generateLabel(colonyCount - 1) + ": " + colonySize);
                } else if (grid[i][j].getValue().equals("0")) {
                    grid[i][j].setValue("-");
                } else {
                    continue;
                }
            }
        }
    }

    
    /** 
     * @param args[] main method
     */
    // test main method
    public static void main(String args[]) {

        ColonyExplorerIt obj = new ColonyExplorerIt();
        obj.os.write("-------------------------------------------------------------------\n");
        obj.os.write("Output for grid of size " + obj.grid.length + " x " + obj.grid[1].length + ": \n\n");

        for (int i = 0; i < obj.grid.length; i++) {
            for (int j = 0; j < obj.grid[i].length; j++) {
                obj.os.write(obj.grid[i][j].toString());
                // System.out.print(obj.grid[i][j].toString());
            }
            obj.os.write("\n");
            // System.out.println();
        }

        obj.os.write("\n"); // extra space in text file
        long startTime = System.currentTimeMillis();
        obj.explore();
        long endTime = System.currentTimeMillis();
        long elapsed = endTime - startTime;
        for (int i = 0; i < obj.grid.length; i++) {
            for (int j = 0; j < obj.grid[i].length; j++) {
                obj.os.write(obj.grid[i][j].toString());
                // System.out.print(obj.grid[i][j].toString());
            }
            obj.os.write("\n");
            // System.out.println();
        }
        for (String var : colonies) {
            obj.os.write(var + "\n");
            // System.out.println(var);
        }
        obj.os.write("\nTime taken: " + elapsed + "ms\n");
        obj.os.write("-------------------------------------------------------------------\n");
        obj.os.close();
    }
}