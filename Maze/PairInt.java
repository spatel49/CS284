/**
 * A class that represents a coordinate on a grid
 * I pledge my honor that I have abided by the Stevens Honor System
 * @author Siddhanth Patel
 */
package Maze;

public class PairInt {
	//Data Fields
	private int x;
	private int y;
	
	//Constructors
	/**
	 * Constructs a pair of coordinates
	 * @param x The x coordinate
	 * @param y The y coordinate
	 */
	public PairInt(int x,int y) {
		this.x = x;
		this.y = y;
	}
	//Methods
	
	/**
	 * Gets the x coordinate
	 * @return The x coordinate
	 */
	public int getX() {
		return x;
	}
	
	/**
	 * Gets the y coordinate
	 * @return The y coordinate
	 */
	public int getY() {
		return y;
	}
	
	/**
	 * Sets the x coordinate
	 * @param x the new integer for x coordinate
	 */
	public void setX(int x){
		this.x = x;
	}
	
	/**
	 * Sets the y coordinate
	 * @param y the new integer for x coordinate
	 */
	public void setY(int y) {
		this.y = y;
	}
	
	/**
	 * True if coordinates are equal to p otherwise false.
	 * @param p object that is being compared to coordinates
	 */
	public boolean equals(Object p) {
		if(!(p instanceof PairInt)){
			throw new IllegalArgumentException("Argument is not a PairInt");
		}
		else{
			PairInt P = (PairInt)p;
			return P.x == this.x && P.y ==this.y;
		}
	}

	/**
	 * Returns PairInt in string type.
	 */
	public String toString() {
		return "(" + x + "," + y + ")";
	}
	
	/**
	 * Creates a new copy of a PairInt
	 * @return A copy of the current coordinates
	 */
	public PairInt copy() {
		return new PairInt(x,y);
	}
}