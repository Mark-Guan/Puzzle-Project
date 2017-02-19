/**
 * Piece class: constructs a puzzle piece with four sides, each side having a different shape.
 * The shapes include a heart, a spade, a club, or a diamond, each either indented or raised.
 * These shapes are assigned to the puzzle through a given direction.
 * The puzzle piece can also rotate counterclockwise or clockwise.
 * 
 * @author Mark Guan
 * 4/20/2014
 */

import java.util.ArrayList;

public class Piece {

	//0 = north; 1 = east; 2 = south; 3 = west
	private int orientation = 0;
	
	/**
	 * gets orientation
	 * @return orientation
	 */
	public int getOrientation() {
		return orientation;
	}

	/**
	 * sets orientation
	 * @param orientation
	 */
	public void setOrientation(int orientation) {
		this.orientation = orientation;
	}

	//shape on the sides
	public static final int IN_HEART = -1;
	public static final int OUT_HEART = 1;
	
	public static final int IN_SPADE = -2;
	public static final int OUT_SPADE = 2;
	
	public static final int IN_CLUB = -3;
	public static final int OUT_CLUB = 3;
	
	public static final int IN_DIAMOND = -4;
	public static final int OUT_DIAMOND = 4;
	
	//directions
	public static final int NORTH = 0;
	public static final int EAST = 1;
	public static final int SOUTH = 2;
	public static final int WEST = 3;
	
	private final ArrayList<Integer> piece = new ArrayList<Integer>(4);
	private int northSpot;
	private int eastSpot;
	private int southSpot;
	private int westSpot;
	
	/**
	 * Piece ctor: constructs a piece given four shapes, assigned to each side in the order of 
	 * north, east, south, and west
	 * @param northSuit
	 * @param eastSuit
	 * @param southSuit
	 * @param westSuit
	 */
	public Piece(int northSuit, int eastSuit, int southSuit, int westSuit){
		piece.add(NORTH, northSuit);
		piece.add(EAST, eastSuit);
		piece.add(SOUTH, southSuit);
		piece.add(WEST, westSuit);
		northSpot = northSuit;
		eastSpot = eastSuit;
		southSpot = southSuit;
		westSpot = westSuit;
	}
	
	/**
	 * getSide: gets the shape that is on a side of the piece
	 * @param direction
	 * @return the shape that is on the side 
	 */
	public int getSide(int direction){
		return piece.get(direction);
	}
	
	/**
	 * rotateClockwise: rotates the piece clockwise
	 */
	public void rotateClockwise(){
		piece.set(NORTH, westSpot);
		piece.set(EAST, northSpot);
		piece.set(SOUTH, eastSpot);
		piece.set(WEST, southSpot);
		int temp = northSpot;
		northSpot = westSpot;
		westSpot = southSpot;
		southSpot = eastSpot;
		eastSpot = temp;
		
		orientation++;
		if(orientation == 4)
			orientation = 0;
	}
	
	/**
	 * rotateCounterClockwise: rotates the piece counterclockwise
	 */
	public void rotateCounterClockwise(){
		piece.set(NORTH, eastSpot);
		piece.set(EAST, southSpot);
		piece.set(SOUTH, westSpot);
		piece.set(WEST, northSpot);
		int temp = northSpot;
		northSpot = eastSpot;
		eastSpot = southSpot;
		southSpot = westSpot;
		westSpot = temp;
		
		orientation++;
		if(orientation == 4)
			orientation = 0;
	}
	
}
