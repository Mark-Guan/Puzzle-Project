import java.util.ArrayList;
/**
 * Board class: a board is an entity that contains pieces. Methods include board constructors, isValid to check
 * if the spot is on the board, getSpot to get the location, setSpot to set a piece to the location, remove to
 * remove a piece from the location, clearBoard to clear the board entirely of pieces, isOccupied to check if 
 * the spot is occupied by a piece already, and getNeighbors to get the neighbors that are adjacent to the given piece.
 * 
 * @author Mark Guan
 * 4/20/2014
 */
public class Board {
	
	private Piece[][] board;
	
	private int numRows;
	private int numCols;
	
	/**
	 * Board ctor: constructs a board given an array of pieces
	 * @param board
	 */
	public Board(Piece[][] board){
		this.board = board;
		this.numRows = board.length;
		this.numCols = board[0].length;
	}
	
	/**
	 * Board ctor: constructs a board given a designated number of rows and columns. The dimension is
	 * (number of rows) * (number of columns)
	 * @param numRows
	 * @param numCols
	 */
	public Board(int numRows, int numCols){
		this(new Piece[numRows][numCols]);
	}
	
	/**
	 * gets board
	 * @return the board
	 */
	public Piece[][] getBoard() {
		return board;
	}

	/**
	 * sets board
	 * @param board
	 */
	public void setBoard(Piece[][] board) {
		this.board = board;
	}

	/**
	 * gets number of rows
	 * @return number of rows
	 */
	public int getNumRows() {
		return numRows;
	}

	/**
	 * sets number of rows
	 * @param numRows
	 */
	public void setNumRows(int numRows) {
		this.numRows = numRows;
	}

	/**
	 * gets number of columns
	 * @return number of columns
	 */
	public int getNumCols() {
		return numCols;
	}

	/**
	 * sets number of columns
	 * @param numCols
	 */
	public void setNumCols(int numCols) {
		this.numCols = numCols;
	}

	/**
	 * Board ctor: default board size is 3x3
	 */
	public Board(){
		this(3,3);
	}
	
	/**
	 * isValid: checks to see if the given location exists on the board
	 * @param row
	 * @param col
	 * @return true if spot is on the board, false if it is not on the board
	 */
	public boolean isValid(int row, int col){
		if(row >= numRows || row < 0 || col >= numCols || col < 0){
			return false;
		}
		return true;
	}
	
	/**
	 * isOccupied: checks if the given spot is already occupied by a piece
	 * @param row
	 * @param col
	 * @return true if is occupied by a piece already, false if it is empty
	 */
	public boolean isOccupied(int row, int col){
		if(isValid(row, col)){
			if(board[row][col] != null){
				return true;
			}
		}
		return false;
	}
	
	/**
	 * getSpot: gets the spot
	 * @param row
	 * @param col
	 * @return piece at the spot if it is occupied, null if it is empty
	 */
	public Piece getSpot(int row, int col){
		if(isOccupied(row, col)){
			return board[row][col];
		}
		return null;
	}
	
	/**
	 * setSpot: sets a piece to the spot
	 * @param piece
	 * @param row
	 * @param col
	 * @return previous piece at the spot if it was occupied, null if it was empty
	 */
	public Piece setSpot(Piece piece, int row, int col){
		if(isValid(row, col)){
			if(isOccupied(row, col)){
				Piece pastPiece = getSpot(row, col);
				board[row][col] = piece;
				return pastPiece;
			}
			board[row][col] = piece;
			return null;
		}
		return null;
	}
	
	/**
	 * remove: removes the piece from the spot
	 * @param row
	 * @param col
	 * @return the piece that was removed
	 */
	public Piece remove(int row, int col){
		Piece pastPiece = getSpot(row, col);
		board[row][col] = null;
		return pastPiece;
	}
	
	/**
	 * clearBoard: clears the board of all pieces
	 * @return a list of all the pieces that were removed
	 */
	public void clearBoard(){
		for (int i = 0; i < numRows; i++){
			for (int j = 0; j < numCols; j++){
				board[i][j] = null;
			}
		}
	}
	
	/**
	 * getNeighbors: gets the neighbors adjacent to the piece at a given spot
	 * @param row
	 * @param col
	 * @return a list of the neighbors
	 */
	public ArrayList<Piece> getNeighbors(int row, int col){
		ArrayList<Piece> neighbors = new ArrayList<Piece>();
		
			Piece neighbor1 = getSpot(row - 1, col);
			Piece neighbor2 = getSpot(row, col + 1);
			Piece neighbor3 = getSpot(row + 1, col);
			Piece neighbor4 = getSpot(row, col - 1);
			
			neighbors.add(neighbor1);
			neighbors.add(neighbor2);
			neighbors.add(neighbor3);
			neighbors.add(neighbor4);	
	
		return neighbors;
	}
	
}
