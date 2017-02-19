import java.util.ArrayList;

public class Board {
	
	private Piece[][] board;
	
	private int numRows;
	private int numCols;
	
	public Board(Piece[][] board){
		this.board = board;
		this.numRows = board.length;
		this.numCols = board[0].length;
	}
	
	public Board(int numRows, int numCols){
		this(new Piece[numRows][numCols]);
	}
	
	public Piece[][] getBoard() {
		return board;
	}

	public void setBoard(Piece[][] board) {
		this.board = board;
	}

	public int getNumRows() {
		return numRows;
	}

	public void setNumRows(int numRows) {
		this.numRows = numRows;
	}

	public int getNumCols() {
		return numCols;
	}

	public void setNumCols(int numCols) {
		this.numCols = numCols;
	}

	public Board(){
		this(3,3);
	}
	
	//works
	public boolean isValid(int row, int col){
		if(row >= numRows || row < 0 || col >= numCols || col < 0){
			return false;
		}
		return true;
	}
	
	//works
	public boolean isOccupied(int row, int col){
		if(isValid(row, col)){
			if(board[row][col] != null){
				return true;
			}
		}
		return false;
	}
	
	//works?
	public Piece getSpot(int row, int col){
		if(isOccupied(row, col)){
			return board[row][col];
		}
		return null;
	}
	
	//works?
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
	
	//works
	public Piece remove(int row, int col){
		Piece pastPiece = getSpot(row, col);
		board[row][col] = null;
		return pastPiece;
	}
	
	//works
	public void clearBoard(){
		for (int i = 0; i < numRows; i++){
			for (int j = 0; j < numCols; j++){
				board[i][j] = null;
			}
		}
	}
	
	//works
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
