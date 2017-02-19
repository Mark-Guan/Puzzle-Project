// Mark Guan
// Class Puzzle keeps track of a board and a pieceBank and allows for interactions between the two

import java.awt.Point;
import java.util.ArrayList;

public class Puzzle {

	private ArrayList<Piece> pieceBank = new ArrayList<Piece>();

	private Board board;
	
	public Puzzle(Board board, Piece[] pieces) {
		for(int i = 0; i < pieces.length; i++){
			pieceBank.add(pieces[i]);
		}
		this.board = board;
	}
	
	public Puzzle(int numRows, int numCols, Piece [] pieces){
		board = new Board(numRows, numCols);
		for(int i = 0; i < pieces.length; i++){
			pieceBank.add(pieces[i]);
		}
	}
	
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
	
	private void clearFrom(int row, int col) {
		for (int i = row; i < board.getNumRows(); i++)
			for (int j = col; j < board.getNumCols(); j++)
				board.setSpot(null, i, j);
	}
	
	public void solve(){
		solve(0, 0, pieceBank);
	}
	
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

	public boolean canFit(Piece piece, int row, int col){
		int actual = 0;
		int max = 0;
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
	
	public Piece removePiece(int row, int col){
		return board.remove(row, col);
	}
	
	public int getNumCols(){
		return board.getNumCols();
	}
	
	public int getNumRows(){
		return board.getNumRows();
	}
	
	public void clear(){
		for (int i = 0; i < board.getNumRows(); i++)
			for (int j = 0; j < board.getNumCols(); j++)
				board.setSpot(null, i, j);
	}

	public Piece getSpot(int row, int col){
		if (board.isValid(row, col))
			return board.getSpot(row, col);
		return null;
	}
	
	public boolean addPiece(Piece piece, int row, int col){
		if(canFit(piece,row,col)){
			board.setSpot(piece, row, col);
			return true;
		}
		return false;
	}
	
	public void printPuzzle() {
		for (int i = 0; i < board.getNumRows(); i++){
			for (int j = 0; j < board.getNumCols(); j++){
				System.out.print(board.getSpot(i, j).toString());	
			}
		}
	}
}
