/**
 * Solves the given maze using DFS or BFS
 * @author Ms. Namasivayam
 * @version 03/10/2023
 */

import java.util.ArrayList;
import java.util.Stack;

public class MazeSolver {
    private Maze maze;
    private Stack<MazeCell> cellsToVisit = new Stack<MazeCell>();

    public MazeSolver() {
        this.maze = null;
    }

    public MazeSolver(Maze maze) {
        this.maze = maze;
    }

    public void setMaze(Maze maze) {
        this.maze = maze;
    }

    /**
     * Starting from the end cell, backtracks through
     * the parents to determine the solution
     * @return An arraylist of MazeCells to visit in order
     */
    public ArrayList<MazeCell> getSolution() {
        // TODO: Get the solution from the maze
        MazeCell currentCell = maze.getStartCell();
        // Should be from start to end cells
        return null;
    }



    /**
     * Performs a Depth-First Search to solve the Maze
     * @return An ArrayList of MazeCells in order from the start to end cell
     */
    public ArrayList<MazeCell> solveMazeDFS() {
        // TODO: Use DFS to solve the maze
        // Explore the cells in the order: NORTH, EAST, SOUTH, WEST
        return null;
    }

    public MazeCell DFSHelper(MazeCell inputCell) {
        ArrayList<MazeCell> cellsToAdd = new ArrayList<MazeCell>();

        // abs of dif of row + abs o dif of col = 1
        int row = inputCell.getRow();
        int col = inputCell.getCol();

        for (int i = -1; i <= 1; i++) {
            for (int j = 1; j >= -1; j--) {
                if (row + i >= maze.getNumRows() || row + i < 0 || col + j >= maze.getNumCols() || col + j < 0) {
                    continue;
                }
                if (Math.abs(i + j) == 1 && !maze.getCell(row + i, col+ j).isExplored()) {
                    if (maze.isValidCell(row + i, col + j)) {
                        cellsToAdd.add(maze.getCell(row + i, col + j));
                    }
                }
            }
        }



    }

    /**
     * Performs a Breadth-First Search to solve the Maze
     * @return An ArrayList of MazeCells in order from the start to end cell
     */
    public ArrayList<MazeCell> solveMazeBFS() {
        // TODO: Use BFS to solve the maze
        // Explore the cells in the order: NORTH, EAST, SOUTH, WEST
        return null;
    }

    public static void main(String[] args) {
        // Create the Maze to be solved
        Maze maze = new Maze("Resources/maze3.txt");

        // Create the MazeSolver object and give it the maze
        MazeSolver ms = new MazeSolver();
        ms.setMaze(maze);

        // Solve the maze using DFS and print the solution
        ArrayList<MazeCell> sol = ms.solveMazeDFS();
        maze.printSolution(sol);

        // Reset the maze
        maze.reset();

        // Solve the maze using BFS and print the solution
        sol = ms.solveMazeBFS();
        maze.printSolution(sol);
    }
}
