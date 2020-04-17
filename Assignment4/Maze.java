package edu.stevens.cs570.assignments;


import java.util.ArrayList;
import java.util.Stack;

/**
 * Class that solves maze problems with backtracking.
 *
 * @author Koffman and Wolfgang
 **/
public class Maze implements GridColors {

    /** The maze */
    private TwoDimGrid maze;

    public Maze(TwoDimGrid m) {
        maze = m;
    }



    /** Wrapper method. */
    public boolean findMazePath() {
        return findMazePath(0, 0); // (0, 0) is the start point.
    }

    /**
     * PROBLEM 1
     * Attempts to find a path through point (x, y).
     *
     * @pre Possible path cells are in BACKGROUND color; barrier cells are in
     *      ABNORMAL color.
     * @post If a path is found, all cells on it are set to the PATH color; all
     *       cells that were visited but are not on the path are in the TEMPORARY
     *       color.
     * @param x
     *            The x-coordinate of current point
     * @param y
     *            The y-coordinate of current point
     * @return If a path through (x, y) is found, true; otherwise, false
     */
    public boolean findMazePath(int x, int y) {
         boolean found = false;
        if(x < 0 || y < 0 || x >= maze.getNCols()||y >= maze.getNRows() || !maze.getColor(x,y).equals(NON_BACKGROUND)){
            return found;
        }
        else if(x == maze.getNCols() - 1 && y == maze.getNRows() - 1){
            maze.recolor(x,y,PATH);
            return !found;
        }
        else{
            maze.recolor(x,y,PATH);
            if(findMazePath(x-1,y) || findMazePath(x+1,y) || findMazePath(x,y-1) || findMazePath(x,y+1)){
                return !found;
            }
            else{
                maze.recolor(x,y,TEMPORARY);
                return found;
            }
        }

    }




    // PROBLEM 2
    public ArrayList<ArrayList<PairInt>> findAllMazePaths(int x, int y) {
        ArrayList<ArrayList<PairInt>> result = new ArrayList<>();
        Stack<PairInt> trace = new Stack<>();
        findMazePathStackBased(0, 0, result, trace);
        return result;
    }

    /**
     * Helper method for PROBLEM 2
     * @pre Possible cells are in ABNORMAL color. Barriers are in BACKGROUND color.
     * @post If a path is found, then the ArrayList result will be modified to
     *       include all possible paths to complete the maze successfully.
     * @param x
     *            x-coordinate of the current point
     * @param y
     *            y-coordinate of the current point
     * @param result
     *            The 2-D ArrayList that contains ArrayLists of PairInts
     *            corresponding to successful paths to complete the maze.
     * @param trace
     *            A stack to keep track of the current path being explored to
     *            determine a successful path.
     *
     */



    private void findMazePathStackBased(int x, int y, ArrayList<ArrayList<PairInt>> result, Stack<PairInt> trace) {
        if(x < 0 || y < 0 || x > maze.getNCols() - 1 || y > maze.getNRows() - 1 ||
                (!maze.getColor(x, y).equals(NON_BACKGROUND))){
            return;
        }
        else if (x == maze.getNCols() - 1 && y == maze.getNRows() - 1) {
            trace.push(new PairInt(x, y));
            ArrayList<PairInt> cur = new ArrayList<>(trace);
            result.add(cur);
            trace.pop();
            maze.recolor(x, y, NON_BACKGROUND);
            return;
        } else {
            trace.push(new PairInt(x, y));
            maze.recolor(x, y, PATH);
            findMazePathStackBased(x, y + 1, result, trace);
            findMazePathStackBased(x, y - 1, result, trace);
            findMazePathStackBased(x + 1, y, result, trace);
            findMazePathStackBased(x - 1, y, result, trace);
            maze.recolor(x, y, NON_BACKGROUND);
            trace.pop();
            return;
        }
    }



    
    // PROBLEM 3
    public ArrayList<PairInt> findMazePathMin(int x, int y) {
        int index = 0;
        int[] count;
        int min;

        // arrayList minPath is the result from find all possible paths
        ArrayList<ArrayList<PairInt>> allPaths;
        allPaths = findAllMazePaths(x, y);

        // create an arrayList which size equals the size of result
        count = new int[allPaths.size()];

        // each element is the size of previous sublist of the result list
        for (int i = 0; i < allPaths.size(); i++) {
            count[i] = allPaths.get(i).size();
        }

        // initial min = 0
        min = count[0];

        // loop to find the smallest count and its index
        for (int i = 1; i < count.length; i++) {
            if (count[i] < min) {
                min = count[i];
                index = i;
            }
        }

        // return the path which has the smallest count number
        return allPaths.get(index);
    }

    /**
     * Helper method for PROBLEM 3
     * All possible paths are explored, and the one with the shortest length is
     * added to the ArrayList.
     *
     * @param x
     *            current x-coordinate
     * @param y
     *            current y-coordinate
     * @return An ArrayList of PairInts that correspond to the shortest possible
     *         path through the maze.
     */
    private ArrayList<ArrayList<PairInt>> findMazePathMinHelper(int x, int y) {
        ArrayList<ArrayList<PairInt>> result = new ArrayList<>();
        Stack<PairInt> trace = new Stack<>();
        findMazePathMin(0, 0);
        return result;
    }

    public void resetTemp() {
        maze.recolor(TEMPORARY, BACKGROUND);
    }

    public void restore() {
        resetTemp();
        maze.recolor(PATH, BACKGROUND);
        maze.recolor(NON_BACKGROUND, BACKGROUND);
    }
}
