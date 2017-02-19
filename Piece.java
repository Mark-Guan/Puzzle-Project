// Mark Guan
// A puzzle piece with with four sides. Each side is a possible suit and each suit can be inward or outward.

import java.util.ArrayList;

public class Piece {

	public int getOrientation() {
		return orientation;
	}

	public void setOrientation(int orientation) {
		this.orientation = orientation;
	}

	private int orientation = 0;
	
	//sides
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
	
	//constructor. Uses ArrayList to create a piece
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
	
	//returns the value of the side given
	public int getSide(int direction){
		return piece.get(direction);
	}
	
	//rotates the piece clockwise;
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
	
	//rotates the piece counterclockwise
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
	
	public String toString(){
		int a = this.getSide(NORTH);
		int b = this.getSide(EAST);
		int c = this.getSide(SOUTH);
		int d = this.getSide(WEST);
		return "{" + a + "," + b + "," + c + "," + d + "}";
	}
	
}
