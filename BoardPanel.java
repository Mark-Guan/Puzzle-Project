
/*
 * Class BoardPanel which creates a board that is divided into 9 boxes and and also contains the 9 puzzle pieces
 * This class's main purpose is to allow interaction between the Graphics Pieces and the board
 * 
 * written by Jeffrey Shao and Mark Guan
 */

import java.awt.*;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import javax.imageio.ImageIO;
import javax.swing.*;

public class BoardPanel extends JPanel
{
	static PieceV2 piece1Component;
	static PieceV2 piece2Component;
	static PieceV2 piece3Component;
	static PieceV2 piece4Component;
	static PieceV2 piece5Component;
	static PieceV2 piece6Component;
	static PieceV2 piece7Component;
	static PieceV2 piece8Component;
	static PieceV2 piece9Component;
	
	/* Constructor
	 * Creates 9 BufferedImages and creates PieceV2 variables using those images
	 * Adds MouseListeners and Key Binding
	 */
	public BoardPanel() throws MalformedURLException, IOException{
		BufferedImage myPicture = ImageIO.read(new URL("http://i.imgur.com/mFXYOXW.png"));
		piece1Component = new PieceV2(new ImageIcon(myPicture), Pieces.PIECE_1);
		add(piece1Component);
		BufferedImage myPicture2 = ImageIO.read(new URL("http://i.imgur.com/GvsmfSn.png"));
		piece2Component = new PieceV2(new ImageIcon(myPicture2), Pieces.PIECE_2);
		add(piece2Component);
		BufferedImage myPicture3 = ImageIO.read(new URL("http://i.imgur.com/2OSo16d.png"));
		 piece3Component = new PieceV2(new ImageIcon(myPicture3), Pieces.PIECE_3);
		add(piece3Component);
		BufferedImage myPicture4 = ImageIO.read(new URL("http://i.imgur.com/XTdumQf.png"));
		 piece4Component = new PieceV2(new ImageIcon(myPicture4), Pieces.PIECE_4);
		add(piece4Component);
		BufferedImage myPicture5 = ImageIO.read(new URL("http://i.imgur.com/NO8OWdr.png"));
		 piece5Component = new PieceV2(new ImageIcon(myPicture5), Pieces.PIECE_5);
		add(piece5Component);
		BufferedImage myPicture6 = ImageIO.read(new URL("http://i.imgur.com/8O6OMgq.png"));
		 piece6Component = new PieceV2(new ImageIcon(myPicture6), Pieces.PIECE_6);
		add(piece6Component);
		BufferedImage myPicture7 = ImageIO.read(new URL("http://i.imgur.com/DsCnmfP.png"));
		 piece7Component = new PieceV2(new ImageIcon(myPicture7), Pieces.PIECE_7);
		add(piece7Component);
		BufferedImage myPicture8 = ImageIO.read(new URL("http://i.imgur.com/o5i04FN.png"));
		 piece8Component = new PieceV2(new ImageIcon(myPicture8), Pieces.PIECE_8);
		add(piece8Component);
		BufferedImage myPicture9 = ImageIO.read(new URL("http://i.imgur.com/I9xIkwk.png"));
		 piece9Component = new PieceV2(new ImageIcon(myPicture9), Pieces.PIECE_9);
		add(piece9Component);
		
		BoardMouseListener bML = new BoardMouseListener();
		addMouseListener(bML);
		addMouseMotionListener(bML);
		
		//rotates a piece when user hits 'r' on the keyboard
		 getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_R, 0), "forward");
	     getActionMap().put("forward", new AbstractAction() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	                rotate();
	            }
	     });
	}
	
	//takes in a piece at a certain spot on the board and checks which PieceV2 has that piece
	private PieceV2 getMatchingPieceComponent(Piece spot) {
		if(spot == piece1Component.getPiece())
			return piece1Component;
		if(spot == piece2Component.getPiece())
			return piece2Component;
		if(spot == piece3Component.getPiece())
			return piece3Component;
		if(spot == piece4Component.getPiece())
			return piece4Component;
		if(spot == piece5Component.getPiece())
			return piece5Component;
		if(spot == piece6Component.getPiece())
			return piece6Component;
		if(spot == piece7Component.getPiece())
			return piece7Component;
		if(spot == piece8Component.getPiece())
			return piece8Component;
		if(spot == piece9Component.getPiece())
			return piece9Component;
		return null;
	}
	
	//Method that paints 9 filled-in boxes and sets the JLabel background to white
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		g2.setColor(Color.WHITE);
		Rectangle box = new Rectangle(68,68);
		for (int i = 4; i < 7; i++)
		{
			for (double j = 3.472; j < 6.471; j++)
			{
				box.setLocation((int) (j * 70), i * 70);
				g2.draw(box);
				g2.fill(box);
			}
		}
	}
	
	/*
	 * MouseListener that determines if the mouse has been pressed, released, dragged, or moved. It also determines if the mouse was
	 * released in the board or not.
	 */
	private class BoardMouseListener implements MouseListener, MouseMotionListener 
	{
		private int screenX = 0;
		private int screenY = 0;
		private int locX = 0;
		private int locY = 0;
		private PieceV2 piece;
		
		
		@Override
		public void mouseClicked(MouseEvent e){}

		@Override
		public void mouseEntered(MouseEvent e){}

		@Override
		public void mouseExited(MouseEvent e){} 

		@Override
		//when the mouse is pressed - it will select the PieceV2 that is there
		// if no PieceV2 is there, the selected PieceV2 will remain as null
		public void mousePressed(MouseEvent e) 
		{
			if(e.getButton() != MouseEvent.BUTTON1){
				return;
			}
			screenX = e.getXOnScreen();
			screenY = e.getYOnScreen();			

				if(screenY > 54 && screenY < 132 ){
					if(screenX > 63 && screenX < 140) piece = piece1Component;
					else if(screenX > 184 && screenX < 261) piece = piece2Component;
					else if(screenX > 312 && screenX < 386) piece = piece3Component;
					else if(screenX > 438 && screenX < 514) piece = piece4Component;
					else if(screenX > 561 && screenX < 636) piece = piece5Component;
					try{
					locX = piece.getX();
					locY = piece.getY();
					}
					catch (NullPointerException exception) {}
				}
				else if(screenY > 179 && screenY < 258 ){
					if(screenX > 121 && screenX < 198) piece = piece6Component;
					if(screenX > 248 && screenX < 323) piece = piece7Component;
					if(screenX > 374 && screenX < 448) piece = piece8Component;
					if(screenX > 499 && screenX < 575) piece = piece9Component;
					
					try{
						locX = piece.getX();
						locY = piece.getY();
						}
						catch (NullPointerException exception) {}
				}
				else if(screenY > 302 && screenY < 372 ){
					if(screenX > 246 && screenX < 316)
						piece = getMatchingPieceComponent(Display.getPuzzle().getSpot(0, 0));
					if(screenX > 316 && screenX < 386)
						piece = getMatchingPieceComponent(Display.getPuzzle().getSpot(0, 1));
					if(screenX > 386 && screenX < 456) 
						piece = getMatchingPieceComponent(Display.getPuzzle().getSpot(0, 2));
				
					try{
						locX = piece.getX();
						locY = piece.getY();
						}
						catch (NullPointerException exception) {}
				}
				else if(screenY > 372 && screenY < 442 ){
					if(screenX > 246 && screenX < 316)
						piece = getMatchingPieceComponent(Display.getPuzzle().getSpot(1, 0));
					if(screenX > 316 && screenX < 386)
						piece = getMatchingPieceComponent(Display.getPuzzle().getSpot(1, 1));
					if(screenX > 386 && screenX < 456) 
						piece = getMatchingPieceComponent(Display.getPuzzle().getSpot(1, 2));
				
					try{
						locX = piece.getX();
						locY = piece.getY();
						}
						catch (NullPointerException exception) {}
				}
				else if(screenY > 442 && screenY < 512 ){
					if(screenX > 246 && screenX < 316)
						piece = getMatchingPieceComponent(Display.getPuzzle().getSpot(2, 0));
					if(screenX > 316 && screenX < 386)
						piece = getMatchingPieceComponent(Display.getPuzzle().getSpot(2, 1));
					if(screenX > 386 && screenX < 456) 
						piece = getMatchingPieceComponent(Display.getPuzzle().getSpot(2, 2));
				
					try{
						locX = piece.getX();
						locY = piece.getY();
						}
						catch (NullPointerException exception) {}
				}
				else{
					piece = null;
				}
			
			
			
		}


		@Override
		/*
		 * Determines what to do if the mouse is released. If the mouse is released in the board and the piece can fit then the PieceV2
		 * will snap into place
		 * Otherwise, the piece will snapHome
		 */
		public void mouseReleased(MouseEvent e){
			if(piece == null)
				return;
			if (e.getButton() != MouseEvent.BUTTON1)
			{			
				return;
			}	
			int deltaX = e.getXOnScreen() - screenX;
			int deltaY = e.getYOnScreen() - screenY;
			int newX = locX + deltaX;
			int newY = locY + deltaY;
			boolean shouldSnap = false;
			/*
			 * If the mouse is released within certain locations, checks to see if the piece can fit and snaps it 
			 * into place if it can. Repeats with different locations.
			 */
			if (newX > 199 && newX < 239)
			{
				if (newY > 235 && newY < 275) 
				{
					if (Display.getPuzzle().canFit(piece.getPiece(),0,0)) {
						piece.setLocation(219,255);
						Display.getPuzzle().addPiece(piece.getPiece(),0,0);
						piece.setInBoard(true);
					}
					else shouldSnap = true;
				}
				else if (newY > 305 && newY < 345)
				{
					if (Display.getPuzzle().canFit(piece.getPiece(),1,0)) {
						piece.setLocation(219,325);
						Display.getPuzzle().addPiece(piece.getPiece(),1,0);
						piece.setInBoard(true);
					}
					else shouldSnap = true;
				}
				else if (newY > 375 && newY < 415)
				{
					if (Display.getPuzzle().canFit(piece.getPiece(),2,0)) {
						piece.setLocation(219,395);
						Display.getPuzzle().addPiece(piece.getPiece(),2,0);
						piece.setInBoard(true);
					}
					else shouldSnap = true;
				}
				else shouldSnap = true;
			}
			else if (newX > 269 && newX < 309)
			{
				if (newY > 235 && newY < 275) 
				{
					if (Display.getPuzzle().canFit(piece.getPiece(),0,1)) {
						piece.setLocation(289,255);
						Display.getPuzzle().addPiece(piece.getPiece(),0,1);
						piece.setInBoard(true);
					}
					else shouldSnap = true;
				}
				
				else if (newY > 305 && newY < 345)
				{
					if (Display.getPuzzle().canFit(piece.getPiece(),1,1)) {
						piece.setLocation(289,325);
						Display.getPuzzle().addPiece(piece.getPiece(),1,1);
						piece.setInBoard(true);;
					}
					else shouldSnap = true;
				}
				else if (newY > 375 && newY < 415)
				{
					if (Display.getPuzzle().canFit(piece.getPiece(),2,1)) {
						piece.setLocation(289,395);
						Display.getPuzzle().addPiece(piece.getPiece(),2,1);
						piece.setInBoard(true);;
					}
					else shouldSnap = true;
				}	
				else shouldSnap = true;
			}
			else if (newX > 329 && newX < 379)
			{
				if (newY > 235 && newY < 275) 
				{
					if (Display.getPuzzle().canFit(piece.getPiece(),0,2)) {
						piece.setLocation(359,255);
						Display.getPuzzle().addPiece(piece.getPiece(),0,2);
						piece.setInBoard(true);
					}
					else shouldSnap = true;
				}	
				else if (newY > 305 && newY < 345)
				{
					if (Display.getPuzzle().canFit(piece.getPiece(),1,2)) {
						piece.setLocation(359,325);
						Display.getPuzzle().addPiece(piece.getPiece(),1,2);
						piece.setInBoard(true);
					}
					else shouldSnap = true;
				}
				
				else if (newY > 375 && newY < 415)
				{
					if (Display.getPuzzle().canFit(piece.getPiece(),2,2)) {
						piece.setLocation(359,395);
						Display.getPuzzle().addPiece(piece.getPiece(),2,2);
						piece.setInBoard(true);
					}
					else shouldSnap = true;
				}		
				else shouldSnap = true;
			}
			else shouldSnap = true;
			if (shouldSnap == true)
			{
				piece.snapHome();
				int i;
				int j;
				try{
					for(i = 0; i<Display.getPuzzle().getNumRows();i++){
						for(j = 0; j<Display.getPuzzle().getNumCols();j++){
							if(piece.getPiece().equals(Display.getPuzzle().getSpot(i, j))){
								Display.getPuzzle().removePiece(i,j);
							}
						}
					}
				}
				catch (NullPointerException exception) {}
				piece = null;
			}
			

			
		}
	
		@Override
		//Moves the selected PieceV2 if the mouse is dragged
		public void mouseDragged(MouseEvent e) 
		{
			if(piece == null)
				return;
			int b1 = MouseEvent.BUTTON1_DOWN_MASK;
			int b2 = MouseEvent.BUTTON2_DOWN_MASK;
			if ((e.getModifiersEx() & (b1 | b2)) == b1) {
				if(piece.isInBoard()){
					for(int i = 0; i < Display.getPuzzle().getNumRows(); i++){
						for(int j = 0; j < Display.getPuzzle().getNumCols(); j++){
							if(piece.getPiece().equals(Display.getPuzzle().getSpot(i, j))){
								Display.getPuzzle().removePiece(i,j);
							}
						}
					}
				}
				int deltaX = e.getXOnScreen() - screenX;
				int deltaY = e.getYOnScreen() - screenY;
				int newX = locX + deltaX;
				int newY = locY + deltaY;
				piece.setLocation(newX, newY);
			}
		}

		//checks if the Mouse is hovering over a PieceV2 or not
		// if it is than the PieceV2 will be declared as rotatable
		public void mouseMoved(MouseEvent e){
			screenX = e.getX();
			screenY = e.getY();

			piece1Component.setRotatable(false);
			piece2Component.setRotatable(false);
			piece3Component.setRotatable(false);
			piece4Component.setRotatable(false);
			piece5Component.setRotatable(false);
			piece6Component.setRotatable(false);
			piece7Component.setRotatable(false);
			piece8Component.setRotatable(false);
			piece9Component.setRotatable(false);
			
			if(screenY > 27 && screenY < 100 ){
				if(screenX > 63 && screenX < 140 && !piece1Component.isInBoard()){
					piece1Component.setRotatable(true);
				}	
				else if(screenX > 184 && screenX < 261 && !piece2Component.isInBoard()){
					piece2Component.setRotatable(true);
				}
				else if(screenX > 312 && screenX < 386 && !piece3Component.isInBoard()) piece3Component.setRotatable(true);
				else if(screenX > 438 && screenX < 514 && !piece4Component.isInBoard()) piece4Component.setRotatable(true);
				else if(screenX > 561 && screenX < 636 && !piece5Component.isInBoard()) piece5Component.setRotatable(true);
			}
			else if(screenY > 150 && screenY < 222 ){
				if(screenX > 121 && screenX < 198 && !piece6Component.isInBoard()) piece6Component.setRotatable(true);
				if(screenX > 248 && screenX < 323 && !piece7Component.isInBoard()) piece7Component.setRotatable(true);
				if(screenX > 374 && screenX < 448 && !piece8Component.isInBoard()) piece8Component.setRotatable(true);
				if(screenX > 499 && screenX < 575 && !piece9Component.isInBoard()) piece9Component.setRotatable(true);
			}
		}
		
	}
	
	//if a PieceV2 is rotatable, than it will be rotated
	public void rotate() {
			if(piece1Component.isRotatable())
				piece1Component.rotate();
			if(piece2Component.isRotatable())
				piece2Component.rotate();
			if(piece3Component.isRotatable())
				piece3Component.rotate();
			if(piece4Component.isRotatable())
				piece4Component.rotate();				
			if(piece5Component.isRotatable())
				piece5Component.rotate();
			if(piece6Component.isRotatable())
				piece6Component.rotate();
			if(piece7Component.isRotatable())
				piece7Component.rotate();
			if(piece8Component.isRotatable())
				piece8Component.rotate();
			if(piece9Component.isRotatable())
				piece9Component.rotate();
			repaint();					
	}
}


