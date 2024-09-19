/**
 * Project 4 - Maze
 *
 * Maze class that allows us to 
 * instantiate a new maze to be
 * solved
 * 
 * @author Brady Williams, L23
 *
 * @version April 5, 2024
 *
 */
public class Maze {
    private final int[] end;
    private final char[][] grid;
    private final String name;
    private int[][] path;
    private final int[] start;

    public Maze(String name, char[][] grid, int[] start, int[] end) {
        this.name = name;
        this.grid = grid;
        this.start = start;
        this.end = end;
        this.path = null;
    }

    public int[] getEnd() {
        return end;
    }

    public char[][] getGrid() {
        return grid;
    }

    public String getName() {
        return name;
    }

    public int[][] getPath() {
        return path;
    }

    public int[] getStart() {
        return start;
    }

    public String pathString() {

        String pathOut = String.format("%s\nMoves: %d\nStart\n", name, path.length);

        for (int i = 0; i < path.length; i++) {
            pathOut = pathOut + String.format("%d-%d\n", path[i][0], path[i][1]);
        }

        pathOut = pathOut + "End";

        return pathOut;
    }

    public void setPath(int[][] path) {
        this.path = path;
    }

    public String toString() {
        String mazeOut = String.format("%s\nStart: %d-%d\nEnd: %d-%d\n", name, start[0], start[1], end[0], end[1]);

        for (int i = 0; i < path.length; i++) {
            mazeOut = mazeOut + path[i][0];
            for (int k = 1; k < path[i].length; k++) {
                mazeOut = mazeOut + ",";
                mazeOut = mazeOut + path[i][k];
            }
        }

        return mazeOut;
    }
}

