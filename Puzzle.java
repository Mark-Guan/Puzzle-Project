import java.awt.Point;
import java.util.ArrayList;
/**
 * Puzzle class: a puzzle is an entity that has a board and pieces. Methods include puzzle constructors,
 * solve method to find a solution to the puzzle, isSolved to check if the puzzle is solved, canFit to check
 * if a piece can fit into a spot, removePiece to remove a piece from the board, clear to clear the board
 * of all pieces, getSpot to get the location on the board, and addPiece to add the piece onto the board.
 * 
 * @author Austin Wolf and Wendy Yu
 * Period 6
 * 4/20/2014
 */
public class Puzzle {

	/**
	 * list of pieces that will be used in the puzzle
	 */
	private ArrayList<Piece> pieceBank = new ArrayList<Piece>();

	private Board board;
	
	/**
	 * Puzzle ctor: takes in a board and a list of pieces. The dimension of the puzzle is the dimension
	 * of the board
	 * @param board
	 * @param pieces
	 */
	public Puzzle(Board board, Piece[] pieces) {
		for(int i = 0; i < pieces.length; i++){
			pieceBank.add(pieces[i]);
		}
		this.board = board;
	}
	
	/**
	 * Puzzle ctor: takes in a number of rows, a number of columns, and a list of pieces. The dimension
	 * of the puzzle is (number of rows) * (number of columns)
	 * @param numRows
	 * @param numCols
	 * @param pieces
	 */
	public Puzzle(int numRows, int numCols, Piece [] pieces){
		board = new Board(numRows, numCols);
		for(int i = 0; i < pieces.length; i++){
			pieceBank.add(pieces[i]);
		}
	}
	
	/**
	 * nextLoc: private method used in solve to get the next location of the puzzle
	 * @param row
	 * @param col
	 * @return
	 */
	private Point nextLoc(int row, int col) {
		int newRow = 0, newCol = 0;
		if (col == board.getNumCols() - 1) {
			newRow = row + 1;
			newCol = 0;
		} else {
			newCol = col + 1;
			newRow = row;
		}

		return new Point(newRow, newCol);
	}
	
	/**
	 * clearFrom: private method used in solve to clear from one spot to another spot
	 * @param row
	 * @param col
	 */
	private void clearFrom(int row, int col) {
		for (int i = row; i < board.getNumRows(); i++)
			for (int j = col; j < board.getNumCols(); j++)
				board.setSpot(null, i, j);
	}
	
	/**
	 * solve: solves puzzle
	 */
	public void solve(){
		solve(0, 0, pieceBank);
	}
	
	/**
	 * solve: helper method to solve the puzzle
	 * @param row
	 * @param col
	 * @param remainder
	 * @return board with the pieces all put in the correct places
	 */
	private Board solve(int row, int col, ArrayList<Piece> remainder){
		 if (isSolved()){
		        return board;
		 }
		    for (Piece piece : remainder) {
		        for(int orientation = 0; orientation < 4; orientation++) {
		        	clearFrom(row, col);
		        	piece.rotateClockwise();
		            if (addPiece(piece, row, col)) {		            	
		            	ArrayList<Piece> remainder2 = (ArrayList<Piece>) remainder.clone();	           
		                remainder2.remove(piece);		                
		                Point next = nextLoc(row, col);		                
		                Board solution = solve(next.x, next.y, remainder2);		                
		                if(solution != null){
		                	return solution;
		                }
		            }
		        }
		    }
		    return null;
	}

	/**
	 * isSolved: checks if the puzzle is solved
	 * @return true if it is solved, false if it is not yet solved
	 */
	public boolean isSolved(){
	    for (int i = 0; i < board.getNumRows(); i++) {
	        for (int j= 0; j < board.getNumCols(); j++) {
	            if (!board.isOccupied(i, j)) {
	                return false;
	            }
	        }
	    }
	    return true;
	}

	/**
	 * canFit: checks if the piece can fit into a given location
	 * @param piece
	 * @param row
	 * @param col
	 * @return true if it can fit, false if it cannot fit
	 */
	public boolean canFit(Piece piece, int row, int col){
		int actual = 0;
		int max = 0;
		if(board.isOccupied(row, col)) return false;
		if (board.isValid(row - 1, col) && getSpot(row - 1, col) != null) {
			max++;
			if (piece.getSide(Piece.NORTH)
					+ getSpot(row - 1, col).getSide(Piece.SOUTH) == 0)
				actual++;
		}
		if (board.isValid(row + 1, col) && getSpot(row + 1, col) != null) {
			max++;
			if (piece.getSide(Piece.SOUTH)
					+ getSpot(row + 1, col).getSide(Piece.NORTH) == 0)
				actual++;
		}
		if (board.isValid(row, col - 1) && getSpot(row, col - 1) != null) {
			max++;
			if (piece.getSide(Piece.WEST)
					+ getSpot(row, col - 1).getSide(Piece.EAST) == 0)
				actual++;
		}
		if (board.isValid(row, col + 1) && getSpot(row, col + 1) != null) {
			max++;
			if (piece.getSide(Piece.EAST)
					+ getSpot(row, col + 1).getSide(Piece.WEST) == 0)
				actual++;
		}
		if (actual == max) {
			return true;
		}
		return false;
	}
	
	/**
	 * removePiece: removes piece from a given spot
	 * @param row
	 * @param col
	 * @return the piece that was removed
	 */
	public Piece removePiece(int row, int col){
		return board.remove(row, col);
	}
	
	/**
	 * gets number of columns
	 * @return number of columns
	 */
	public int getNumCols(){
		return board.getNumCols();
	}
	
	/**
	 * gets number of rows
	 * @return number of rows
	 */
	public int getNumRows(){
		return board.getNumRows();
	}
	
	/**
	 * clear: clears the puzzle of all pieces
	 */
	public void clear(){
		for (int i = 0; i < board.getNumRows(); i++)
			for (int j = 0; j < board.getNumCols(); j++)
				board.setSpot(null, i, j);
	}

	/**
	 * getSpot: gets the location
	 * @param row
	 * @param col
	 * @return piece that is at the location, or null if there is nothing there
	 */
	public Piece getSpot(int row, int col){
		if (board.isValid(row, col))
			return board.getSpot(row, col);
		return null;
	}
	
	/**
	 * addPiece: adds piece to the puzzle
	 * @param piece
	 * @param row
	 * @param col
	 * @return true if a piece can be added, false if it cannot be added
	 */
	public boolean addPiece(Piece piece, int row, int col){
		if(canFit(piece,row,col)){
			board.setSpot(piece, row, col);
			return true;
		}
		return false;
	}
}