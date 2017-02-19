/*
 * PieceV2 is a Piece that has an Icon that is attached to it. It also has boolean variable that determines
 * if it is in a Board or not, a boolean that determines whether or not it can be rotated and an orientation variable.
 * 
 * written by Jeffrey Shao and Mark Guan
 */

import java.awt.*;
import javax.swing.*;

public class PieceV2 extends JLabel{
	private Piece piece;
	private boolean inBoard = false;
	private boolean rotatable = false;
	
	//gets piece
	public Piece getPiece() {
		return piece;
	}
	
	//takes in a piece and sets it
	public void setPiece(Piece piece) {
		this.piece = piece;
	}

	//checks if piece isRotatable
	public boolean isRotatable() {
		return rotatable;
	}
	
	//sets whether or not piece is rotatable
	public void setRotatable(boolean rotatable) {
		this.rotatable = rotatable;
	}
	
	//returns if piece isInBoard
	public boolean isInBoard() {
		return inBoard;
	}
	
	//sets whether or not piece is inBoard
	public void setInBoard(boolean inBoard) {
		this.inBoard = inBoard;
	}
	
	/*
	 * Ctor for PieceV2 that takes in a Piece and an image, and calls the JLabel constructor with
	 * the image as the parameters, and sets piece to the given Piece.
	 */
	public PieceV2(Icon image, Piece piece)
	{
		super(image);
		setBackground(Color.WHITE);
		setBounds(0, 0, 100, 100);
		setOpaque(false);
		this.piece = piece;
		snapHome();
	}
	
	//Returns piece back to its location in the JPanel
	public void snapHome()
	{
		inBoard = false;
		int homeX = 0;
		int homeY = 0;
		
		if (piece == Pieces.PIECE_1)
		{
			homeX = 41;
			homeY = 5;
		}
		if (piece == Pieces.PIECE_2)
		{
			homeX = 164;
			homeY = 5;
		}
		if (piece == Pieces.PIECE_3)
		{
			homeX = 287;
			homeY = 5;
		}
		
		if (piece == Pieces.PIECE_4)
		{
			homeX = 410;
			homeY = 5;
		}
		if (piece == Pieces.PIECE_5)
		{
			homeX = 533;
			homeY = 5;
		}
		if (piece == Pieces.PIECE_6)
		{
			homeX = 102;
			homeY = 128;
		}
		if (piece == Pieces.PIECE_7)
		{
			homeX = 225;
			homeY = 128;
		}
		if (piece == Pieces.PIECE_8)
		{
			homeX = 348;
			homeY = 128;
		}
		if (piece == Pieces.PIECE_9)
		{
			homeX = 471;
			homeY = 128;
		}
		setLocation(homeX, homeY);
	}
	
	//rotates the PieceV2
	public void rotate(){
		piece.rotateClockwise();
	}
	
	//Paints the PieceV2 icon on the JPanel given its orientation
	public void paintComponent(Graphics g)
	{
		Graphics2D g2 = (Graphics2D)g.create();
		g2.translate( + 59,  + 59); 
		g2.rotate( Math.toRadians(piece.getOrientation() * 90 ) );
		this.getIcon().paintIcon(this, g2, -59, -59);
    	}
	

}
