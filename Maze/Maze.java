package Maze;

import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Stack;

//Siddhanth Patel
//I pledge my honor that I have abided by the Stevens Honor System.
/**
 * Class that solves maze problems with backtracking.
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
    	System.out.println("All paths: " + findAllMazePaths(0,0));
    	System.out.println("Min paths: " + findMazePathMin(0,0));
        return findMazePath(0, 0); // (0, 0) is the start point.
    }

    /**
     * Attempts to find a path through point (x, y).
     * @pre Possible path cells are in BACKGROUND color;
     *      barrier cells are in ABNORMAL color.
     * @post If a path is found, all cells on it are set to the
     *       PATH color; all cells that were visited but are
     *       not on the path are in the TEMPORARY color.
     * @param x The x-coordinate of current point
     * @param y The y-coordinate of current point
     * @return If a path through (x, y) is found, true;
     *         otherwise, false
     */
    public boolean findMazePath(int x, int y) {
    	if(!(x >= 0 && x < maze.getNCols() && y >= 0 && y < maze.getNRows())) {
    		return false;
    	}
    	else if(maze.getColor(x,y) != NON_BACKGROUND) {
    		return false;
    	}
    	else if (x == maze.getNCols() - 1 && y == maze.getNRows() - 1) {
			maze.recolor(x,y, PATH);
			return true;
		}
		else {
			maze.recolor(x,y, PATH);
			if (findMazePath(x-1,y) || findMazePath(x+1,y) || findMazePath(x,y-1) || findMazePath(x,y+1)) {
				return true;
			}
			else {
				maze.recolor(x,y, TEMPORARY);
				return false;
			}
		}
	}

    /**
     * Helps find all paths using Stacks for findAllMazePath method
     * @param x the starting x coordinate
     * @param y the starting y coordinate
     * @param result the arraylist of arraylist of lists of path coordinates
     * @param trace the stack that holds coordinates
     */
	public void findMazePathStackBased(int x, int y, ArrayList<ArrayList<PairInt>> result, Stack<PairInt> trace) {
    	if (x > maze.getNCols() -1 || y > maze.getNRows() -1){
    		return;}
    	if (x < 0 || y < 0) {
    		return;}
    	if (maze.getColor(x, y) == BACKGROUND || maze.getColor(x, y) == TEMPORARY){
    		return;}
    	else if (x == (maze.getNCols()-1) && y == (maze.getNRows()-1)){
    		PairInt coord = new PairInt(x,y);
			trace.push(coord);
    		ArrayList<PairInt> result1 = new ArrayList<PairInt>();
    		result1.addAll(trace);
    		result.add(result1);
    		trace.pop();
    		maze.recolor(x,y, NON_BACKGROUND);
    		return;
		}else {
			maze.recolor(x, y,TEMPORARY);
    		PairInt coord = new PairInt(x,y);
			trace.push(coord);
    		this.findMazePathStackBased(x + 1, y, result, trace);
    		this.findMazePathStackBased(x - 1, y, result, trace);
    		this.findMazePathStackBased(x, y + 1, result, trace);
    		this.findMazePathStackBased(x, y - 1, result, trace);
    		maze.recolor(x, y, NON_BACKGROUND);
    		trace.pop();
    		return;
    		}
    	}
	 /**
     * Finds all possible paths and returns the arraylist of arraylist of lists of all possible path coordinates
     * @param x the starting x coordinate 
     * @param y the starting y coordinate
     * @return the arraylist of arraylist of lists of all possible path coordinates
     */
    public ArrayList<ArrayList<PairInt>> findAllMazePaths(int x, int y) {
        ArrayList<ArrayList<PairInt>> result = new ArrayList<>();
        Stack<PairInt> trace = new Stack<>();
        findMazePathStackBased(0, 0, result, trace);
        return result;
    }
    
    
    /**
     * Finds the shortest length of possible paths and returns a list of coordinates of that path.
     * @param the x coordinate 
     * @param the y coordinate
     * @return an list of coordinates for the shortest path and an empty list if there are no paths.
     */
    public ArrayList<PairInt> findMazePathMin(int x, int y) {
    	var paths = findAllMazePaths(x, y);
    	ArrayList <ArrayList <PairInt>> result = paths;
    	if(result.size()==0) {
    		return new ArrayList<PairInt>();
    	}
    	else {
    		int PathLength = result.get(0).size();
        	int resultindex = 0;
	    	for(int i = 1; i<result.size(); i++) {
	    		if(PathLength > result.get(i).size()) {
	    			PathLength = result.get(i).size();
	    			resultindex = i;
    		}
    	}
    	return result.get(resultindex);
    }
    }

    /*<exercise chapter="5" section="6" type="programming" number="2">*/
    public void resetTemp() {
        maze.recolor(TEMPORARY, BACKGROUND);
    }
    /*</exercise>*/

    /*<exercise chapter="5" section="6" type="programming" number="3">*/
    public void restore() {
        resetTemp();
        maze.recolor(PATH, BACKGROUND);
        maze.recolor(NON_BACKGROUND, BACKGROUND);
    }
    /*</exercise>*/
}
/*</listing>*/
