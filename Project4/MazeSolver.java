import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Array;
import java.util.ArrayList;


//Javadoc
/**
 * Project 4 - MazeSolver
 *
 * Reads the maze given the file
 * and also computes the quickest
 * route through the specified maze
 * 
 * @author Brady Williams, L23
 *
 * @version April 5, 2024
 *
 */
public class MazeSolver {
    private Maze maze;

    public MazeSolver() {

    }

    public void readMaze(String filename) throws InvalidMazeException, IOException {
        String line;
        String name;

        String startStr;
        String[] startArr;
        String[] startLoc;
        int[] start = new int[2];

        String endStr;
        String[] endArr;
        String[] endLoc;
        int[] end = new int[2];

        ArrayList<String[]> gridList = new ArrayList<String[]>();
        char[][] grid;
        int rows;
        int columns;
        
        BufferedReader br;

        try {
            br = new BufferedReader(new FileReader(new File(filename)));
        } catch (Exception e) {
            throw new IOException("File does not exist.");
        }

        name = br.readLine();

        if (name.contains("Start: ") || name.contains("End:") || name.equals("")) {
            name = null;
            br.close();
            throw new InvalidMazeException("No Maze Name.");
        }

        startStr = br.readLine();

        if (startStr.contains("Start: ") && startStr.contains("-")) {
            startArr = startStr.split(": ");
            startLoc = startArr[1].split("-");
            try {
                start[0] = Integer.parseInt(startLoc[0]);
                start[1] = Integer.parseInt(startLoc[1]);
            } catch (Exception e) {
                br.close();
                throw new InvalidMazeException("Start location invalid.");
            }
            
        } else {
            br.close();
            throw new InvalidMazeException("No Maze Start.");
        }

        endStr = br.readLine();

        if (endStr.contains("End: ") && endStr.contains("-")) {
            endArr = endStr.split(": ");
            endLoc = endArr[1].split("-");
            try {
                end[0] = Integer.parseInt(endLoc[0]);
                end[1] = Integer.parseInt(endLoc[1]);
            } catch (Exception e) {
                br.close();
                throw new InvalidMazeException("End location invalid.");
            }
            
        } else {
            br.close();
            throw new InvalidMazeException("No Maze End.");
        }

        try {
            while ((line = br.readLine()) != null) {
                gridList.add(line.split(","));
            }
        } catch (Exception e) {
            br.close();
            throw new InvalidMazeException("Invalid Grid Provided");
        }
            
        br.close();

        rows = gridList.size();
        columns = gridList.get(0).length;

        grid = new char[rows][columns];

        for (int i = 0; i < gridList.size(); i++) {
            if (gridList.get(i).length == columns) {
                for (int j = 0; j < gridList.get(i).length; j++) {
                    grid[i][j] = gridList.get(i)[j].charAt(0);
                }
            } else {
                throw new InvalidMazeException("Maze is not a rectangle.");
            }
        }

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] != 'P' && grid[i][j] != 'W') {
                    throw new InvalidMazeException("Maze contains invalid characters.");
                }
            }
        }
    
        if (grid[start[0]][start[1]] != 'P') {
            throw new InvalidMazeException("Start location is not a path.");
        }

        if (grid[end[0]][end[1]] != 'P') {
            throw new InvalidMazeException("End location is not a path.");
        }

        if (start[0] > grid.length || start[1] > grid[0].length || start[0] < 0 || start[1] < 0) {
            throw new InvalidMazeException("Start location is not in maze.");
        }

        if (end[0] > grid.length || end[1] > grid[0].length || end[0] < 0 || end[1] < 0) {
            throw new InvalidMazeException("End location is not in maze.");
        }

        maze = new Maze(name, grid, start, end);
    }


    public void solveMaze() {
        //For this solution, I implemented BFS to ensure that it took
        //the quickest possible path
        int[][] cardinalDirections = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}}; 
        // North, South, West, East in order
        //this array will allow me to easily find the 
        //four directions from the current point to explore
        char[][] grid = maze.getGrid();
        int[] start = maze.getStart();
        int[] end = maze.getEnd();
        int[][] solution;
    
        ArrayList<ArrayList<int[]>> paths = new ArrayList<>();
        ArrayList<int[]> initialPath = new ArrayList<>();
        initialPath.add(start);
        paths.add(initialPath);
    
        boolean solved = false;
        
        while (!paths.isEmpty() && !solved) {
            ArrayList<ArrayList<int[]>> newPaths = new ArrayList<>();
    
            for (ArrayList<int[]> path : paths) {
                int[] lastPosition = path.get(path.size() - 1);
    
                if (lastPosition[0] == end[0] && lastPosition[1] == end[1]) {

                    solution = new int[path.size()][2];
                    for (int a = 0; a < path.size(); a++) {
                        solution[a] = path.get(a);
                    }
                    maze.setPath(solution);
                    solved = true;
                    break;
                }
    
                for (int[] cardinalDirection : cardinalDirections) {
                    int newRow = lastPosition[0] + cardinalDirection[0];
                    int newColumn = lastPosition[1] + cardinalDirection[1];
    
                    if (newRow >= 0 && newColumn >= 0 && newRow < grid.length && 
                        newColumn < grid[0].length && grid[newRow][newColumn] == 'P') {
                        boolean alreadyVisited = false;
                        for (int[] step : path) {
                            if (step[0] == newRow && step[1] == newColumn) {
                                alreadyVisited = true;
                                break;
                            }
                        }
                        if (!alreadyVisited) {
                            ArrayList<int[]> newPath = new ArrayList<>(path);
                            newPath.add(new int[]{newRow, newColumn});
                            newPaths.add(newPath);
                        }
                    }
                }
            }
    
            paths = new ArrayList<>(newPaths);
        }
    }

    public void writeSolution(String filename) {
        try {
            BufferedWriter pw = new BufferedWriter(new PrintWriter(new File(filename)));
            pw.write(maze.pathString());
            pw.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}