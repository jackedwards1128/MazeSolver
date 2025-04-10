/**
 * Solves the given maze using DFS or BFS
 * @author Jack Edwards
 * Distribution code written by Ms. Namasivayam
 * @version 03/10/2023
 */

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class MazeSolver {
    private Maze maze;
    private Stack<MazeCell> cellsToVisitStack = new Stack<MazeCell>();
    private Queue<MazeCell> cellsToVisitQueue = new LinkedList<MazeCell>();

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
        ArrayList<MazeCell> solution = new ArrayList<MazeCell>();

        MazeCell cell = maze.getEndCell();
        while (cell != maze.getStartCell()) {
            solution.add(cell);
            cell = cell.getParent();
        }
        return solution;
    }



    /**
     * Performs a Depth-First Search to solve the Maze
     * @return An ArrayList of MazeCells in order from the start to end cell
     */
    public ArrayList<MazeCell> solveMazeDFS() {
        // Explore the cells in the order: NORTH, EAST, SOUTH, WEST

        // Run Depth First Search to create a chain of parents leading from the end cell to the start cell
        DFSHelper(maze.getStartCell());

        // Return solution using getSolution()
        return getSolution();
    }

    public MazeCell DFSHelper(MazeCell inputCell) {

        int row = inputCell.getRow();
        int col = inputCell.getCol();

        // Base case: if we have arrived at the end cell, return the end cell itself
        if (maze.getEndCell() == inputCell) {
            return inputCell;
        }

        // First add the neighboring tiles that are North, then East, then South
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                // Check that the neighbor being checked is not out of bounds
                if (row + i >= maze.getNumRows() || row + i < 0 || col + j >= maze.getNumCols() || col + j < 0) {
                    continue;
                }
                // If the absolute value of the sum of the difference in row/col is 1, the tile is directly
                // adjacent to the parent cell. Also, check that it's unexplored
                if (Math.abs(i + j) == 1 && !maze.getCell(row + i, col+ j).isExplored()) {
                    // Make sure the program does not preemptively add the west tile
                    if (!(i == 0 && j == -1)) {
                        // Check if the tile is a wall
                        if (maze.isValidCell(row + i, col + j)) {
                            // Set the cell's parent to the current cell, set the cell to explored, and add it to the Stack
                            maze.getCell(row + i, col + j).setParent(inputCell);
                            maze.getCell(row + i, col + j).setExplored(true);
                            cellsToVisitStack.push(maze.getCell(row + i, col + j));
                        }
                    }
                }
            }
        }

        // Add the west tile
        // Check if it's in bounds
        if (!(row >= maze.getNumRows() || row < 0 || col - 1 >= maze.getNumCols() || col - 1 < 0)) {
            // Check if it's valid and unexplored
            if (maze.isValidCell(row, col - 1) && !maze.getCell(row, col - 1).isExplored()) {
                // Set the cell's parent to the current cell, set the cell to explored, and add it to the Stack
                maze.getCell(row, col - 1).setParent(inputCell);
                maze.getCell(row, col - 1).setExplored(true);
                cellsToVisitStack.push(maze.getCell(row, col - 1));
            }
        }

        // Use recursion to move to the next cell-to-visit in the stack
        return DFSHelper(cellsToVisitStack.pop());
    }

    /**
     * Performs a Breadth-First Search to solve the Maze
     * @return An ArrayList of MazeCells in order from the start to end cell
     */
    public ArrayList<MazeCell> solveMazeBFS() {
        // Explore the cells in the order: NORTH, EAST, SOUTH, WEST

        // Run Depth First Search to create a chain of parents leading from the end cell to the start cell
        BFSHelper(maze.getStartCell());

        // Return solution using getSolution()
        return getSolution();
    }

    public MazeCell BFSHelper(MazeCell inputCell) {
        int row = inputCell.getRow();
        int col = inputCell.getCol();

        // Base case: if we have arrived at the end cell, return the end cell itself
        if (maze.getEndCell() == inputCell) {
            return inputCell;
        }

        // First add the neighboring tiles that are North, then East, then South
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                // Check that the neighbor being checked is not out of bounds
                if (row + i >= maze.getNumRows() || row + i < 0 || col + j >= maze.getNumCols() || col + j < 0) {
                    continue;
                }
                // If the absolute value of the sum of the difference in row/col is 1, the tile is directly
                // adjacent to the parent cell. Also, check that it's unexplored
                if (Math.abs(i + j) == 1 && !maze.getCell(row + i, col+ j).isExplored()) {
                    // Make sure the program does not preemptively add the west tile
                    if (!(i == 0 && j == -1)) {
                        // Check if the tile is a wall
                        if (maze.isValidCell(row + i, col + j)) {
                            // Set the cell's parent to the current cell, set the cell to explored, and add it to the Queue
                            maze.getCell(row + i, col + j).setParent(inputCell);
                            maze.getCell(row + i, col + j).setExplored(true);
                            cellsToVisitQueue.add(maze.getCell(row + i, col + j));
                        }
                    }
                }
            }
        }

        // Add the west tile
        // Check if it's in bounds
        if (!(row >= maze.getNumRows() || row < 0 || col - 1 >= maze.getNumCols() || col - 1 < 0)) {
            // Check if it's valid and unexplored
            if (maze.isValidCell(row, col - 1) && !maze.getCell(row, col - 1).isExplored()) {
                // Set the cell's parent to the current cell, set the cell to explored, and add it to the Queue
                maze.getCell(row, col - 1).setParent(inputCell);
                maze.getCell(row, col - 1).setExplored(true);
                cellsToVisitQueue.add(maze.getCell(row, col - 1));
            }
        }

        // Use recursion to move to the next cell-to-visit in the queue
        return BFSHelper(cellsToVisitQueue.remove());
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

        System.out.println();

        // Reset the maze
        maze.reset();

        // Solve the maze using BFS and print the solution
        sol = ms.solveMazeBFS();
        maze.printSolution(sol);
    }
}
