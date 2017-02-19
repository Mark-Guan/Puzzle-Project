// Mark Guan
// The graphical version of a puzzle piece. Contains a puzzle piece.

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;                        

public class PieceV2 extends JLabel
{
	private Piece piece;
	private boolean inBoard = false;

	public PieceV2(Icon image, Piece piece)
	{
		super(image);
		setBackground(Color.WHITE);
		setBounds(0, 0, 100, 100);
		setOpaque(false);
		PieceMouseListener pML = new PieceMouseListener();
		addMouseListener(pML);
		addMouseMotionListener(pML);
		this.piece = piece;
	}
	
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
	
	private class PieceMouseListener implements MouseListener, MouseMotionListener 
	{
		private int screenX = 0;
		private int screenY = 0;
		private int locX = 0;
		private int locY = 0;
		
		
		@Override
		public void mouseClicked(MouseEvent e){}

		@Override
		public void mouseEntered(MouseEvent e){}

		@Override
		public void mouseExited(MouseEvent e){} 

		@Override
		public void mousePressed(MouseEvent e) 
		{
				screenX = e.getXOnScreen();
				screenY = e.getYOnScreen();
				locX = getX();
				locY = getY();
			if (e.getButton() == MouseEvent.BUTTON3)
			{
				if(!inBoard){
					piece.rotateClockwise();
					repaint();
				}
			}	
		}

		@Override
		public void mouseReleased(MouseEvent e){
			int deltaX = e.getXOnScreen() - screenX;
			int deltaY = e.getYOnScreen() - screenY;
			int newX = locX + deltaX;
			int newY = locY + deltaY;
			boolean shouldSnap = false;
			if (newX > 199 && newX < 239)
			{
				if (newY > 235 && newY < 275) 
				{
					if (Display.getPuzzle().canFit(piece,0,0)) {
						setLocation(219,255);
						Display.getPuzzle().addPiece(piece,0,0);
						inBoard = true;
					}
					else shouldSnap = true;
				}
				else if (newY > 305 && newY < 345)
				{
					if (Display.getPuzzle().canFit(piece,1,0)) {
						setLocation(219,325);
						Display.getPuzzle().addPiece(piece,1,0);
						inBoard = true;
					}
					else shouldSnap = true;
				}
				else if (newY > 375 && newY < 415)
				{
					if (Display.getPuzzle().canFit(piece,2,0)) {
						setLocation(219,395);
						Display.getPuzzle().addPiece(piece,2,0);
						inBoard = true;
					}
					else shouldSnap = true;
				}
				else shouldSnap = true;
			}
			else if (newX > 269 && newX < 309)
			{
				if (newY > 235 && newY < 275) 
				{
					if (Display.getPuzzle().canFit(piece,0,1)) {
						setLocation(289,255);
						Display.getPuzzle().addPiece(piece,0,1);
						inBoard = true;
					}
					else shouldSnap = true;
				}
				
				else if (newY > 305 && newY < 345)
				{
					if (Display.getPuzzle().canFit(piece,1,1)) {
						setLocation(289,325);
						Display.getPuzzle().addPiece(piece,1,1);
						inBoard = true;
					}
					else shouldSnap = true;
				}
				else if (newY > 375 && newY < 415)
				{
					if (Display.getPuzzle().canFit(piece,2,1)) {
						setLocation(289,395);
						Display.getPuzzle().addPiece(piece,2,1);
						inBoard = true;
					}
					else shouldSnap = true;
				}	
				else shouldSnap = true;
			}
			else if (newX > 329 && newX < 379)
			{
				if (newY > 235 && newY < 275) 
				{
					if (Display.getPuzzle().canFit(piece,0,2)) {
						setLocation(359,255);
						Display.getPuzzle().addPiece(piece,0,2);
						inBoard = true;
					}
					else shouldSnap = true;
				}	
				else if (newY > 305 && newY < 345)
				{
					if (Display.getPuzzle().canFit(piece,1,2)) {
						setLocation(359,325);
						Display.getPuzzle().addPiece(piece,1,2);
						inBoard = true;
					}
					else shouldSnap = true;
				}
				
				else if (newY > 375 && newY < 415)
				{
					if (Display.getPuzzle().canFit(piece,2,2)) {
						setLocation(359,395);
						Display.getPuzzle().addPiece(piece,2,2);
						inBoard = true;
					}
					else shouldSnap = true;
				}		
				else shouldSnap = true;
			}
			else shouldSnap = true;
			if (shouldSnap == true)
			{
				snapHome();
				int i;
				int j;
				try{
					for(i = 0; i<Display.getPuzzle().getNumRows();i++){
						for(j = 0; j<Display.getPuzzle().getNumCols();j++){
							if(piece.equals(Display.getPuzzle().getSpot(i, j))){
								Display.getPuzzle().removePiece(i,j);
							}
						}
					}
				}
				catch (NullPointerException exception) {}
			}
			

			
		}
	
		@Override
		public void mouseDragged(MouseEvent e) 
		{
			int deltaX = e.getXOnScreen() - screenX;
			int deltaY = e.getYOnScreen() - screenY;
			int newX = locX + deltaX;
			int newY = locY + deltaY;
			setLocation(newX, newY);
		}

		@Override
		public void mouseMoved(MouseEvent e){}
	}
	@Override
	public void paintComponent(Graphics g)
	{
		Graphics2D g2 = (Graphics2D)g.create();
		g2.translate( + 59,  + 59);
		g2.rotate( Math.toRadians(piece.getOrientation() * 90 ) );
		this.getIcon().paintIcon(this, g2, -59, -59);
    	}

}
