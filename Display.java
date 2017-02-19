// Mark Guan
// Creates the graphical interface for the puzzle game.

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import javax.imageio.ImageIO;
import javax.swing.*;


public class Display
{


	private static Puzzle puzzle;
	private static JTextArea text = new JTextArea("Welcome to the Puzzle Game\n"
			+ "Instructions\n"
			+ "-----------------------------------------------\n"
			+ "-Click and Drag a Puzzle Piece to Place It Inside the Board\n"
			+ "-Right-Click on a Puzzle Piece Outside of the Board to Rotate It");
	private static JTextField textField = new JTextField("Status:Puzzle Unsolved");
	
	public static void generatePuzzle(){
		Piece[] test = new Piece[9];
		test[0] =  Pieces.PIECE_1;
		test[1] =  Pieces.PIECE_2;
		test[2] =  Pieces.PIECE_3;
		test[3] =  Pieces.PIECE_4;
		test[4] =  Pieces.PIECE_5;
		test[5] =  Pieces.PIECE_6;
		test[6] =  Pieces.PIECE_7;
		test[7] =  Pieces.PIECE_8;
		test[8] =  Pieces.PIECE_9;
		puzzle = new Puzzle(new Board(3,3), test);
	}
	public static Puzzle getPuzzle()
	{
		return puzzle;
	}
	
	public static void main(String[] args) throws IOException 
	{
		generatePuzzle();
		final JFrame frame = new JFrame();	
		frame.setSize(700, 700);
		frame.setPreferredSize(new Dimension(700, 700));

		frame.setTitle("Puzzle Game");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JPanel panel = new JPanel();
		JPanel board = new BoardPanel();
		panel.setBackground(new Color(172, 209, 233));
		board.setBackground(new Color(172, 209, 233));
		
		JButton clearButton = new JButton("Clear");
		JButton solveButton = new JButton("Solve");
		

		
		panel.add(clearButton);
		panel.add(solveButton);
		
		BufferedImage myPicture = ImageIO.read(new URL("http://i.imgur.com/mFXYOXW.png"));
		final PieceV2 piece1Component = new PieceV2(new ImageIcon(myPicture), Pieces.PIECE_1);
		board.add(piece1Component);
		BufferedImage myPicture2 = ImageIO.read(new URL("http://i.imgur.com/GvsmfSn.png"));
		final PieceV2 piece2Component = new PieceV2(new ImageIcon(myPicture2), Pieces.PIECE_2);
		board.add(piece2Component);
		BufferedImage myPicture3 = ImageIO.read(new URL("http://i.imgur.com/2OSo16d.png"));
		final PieceV2 piece3Component = new PieceV2(new ImageIcon(myPicture3), Pieces.PIECE_3);
		board.add(piece3Component);
		BufferedImage myPicture4 = ImageIO.read(new URL("http://i.imgur.com/XTdumQf.png"));
		final PieceV2 piece4Component = new PieceV2(new ImageIcon(myPicture4), Pieces.PIECE_4);
		board.add(piece4Component);
		BufferedImage myPicture5 = ImageIO.read(new URL("http://i.imgur.com/NO8OWdr.png"));
		final PieceV2 piece5Component = new PieceV2(new ImageIcon(myPicture5), Pieces.PIECE_5);
		board.add(piece5Component);
		BufferedImage myPicture6 = ImageIO.read(new URL("http://i.imgur.com/8O6OMgq.png"));
		final PieceV2 piece6Component = new PieceV2(new ImageIcon(myPicture6), Pieces.PIECE_6);
		board.add(piece6Component);
		BufferedImage myPicture7 = ImageIO.read(new URL("http://i.imgur.com/DsCnmfP.png"));
		final PieceV2 piece7Component = new PieceV2(new ImageIcon(myPicture7), Pieces.PIECE_7);
		board.add(piece7Component);
		BufferedImage myPicture8 = ImageIO.read(new URL("http://i.imgur.com/o5i04FN.png"));
		final PieceV2 piece8Component = new PieceV2(new ImageIcon(myPicture8), Pieces.PIECE_8);
		board.add(piece8Component);
		BufferedImage myPicture9 = ImageIO.read(new URL("http://i.imgur.com/I9xIkwk.png"));
		final PieceV2 piece9Component = new PieceV2(new ImageIcon(myPicture9), Pieces.PIECE_9);
		board.add(piece9Component);
		
		class clearListener implements ActionListener
		{
			@Override
			public void actionPerformed(ActionEvent arg0) 
			{
				frame.setSize(0,0);
				frame.setSize(700,700);
				puzzle.clear();
			}
		}
		
		clearListener cL = new clearListener();
		clearButton.addActionListener(cL);
		
		class solveListener implements ActionListener
		{
			public void actionPerformed(ActionEvent arg)
			{
				puzzle.solve();
				for (int r = 0; r < 3; r++)
					for (int c = 0; c < 3; c++)
					{
						Piece p = puzzle.getSpot(r,c);
						if (p == Pieces.PIECE_1) piece1Component.setLocation(219 + 70 * c, 255 + 70 * r);
						if (p == Pieces.PIECE_2) piece2Component.setLocation(219 + 70 * c, 255 + 70 * r);
						if (p == Pieces.PIECE_3) piece3Component.setLocation(219 + 70 * c, 255 + 70 * r);
						if (p == Pieces.PIECE_4) piece4Component.setLocation(219 + 70 * c, 255 + 70 * r);
						if (p == Pieces.PIECE_5) piece5Component.setLocation(219 + 70 * c, 255 + 70 * r);
						
						if (p == Pieces.PIECE_6) piece6Component.setLocation(219 + 70 * c, 255 + 70 * r);
						if (p == Pieces.PIECE_7) piece7Component.setLocation(219 + 70 * c, 255 + 70 * r);
						if (p == Pieces.PIECE_8) piece8Component.setLocation(219 + 70 * c, 255 + 70 * r);
						if (p == Pieces.PIECE_9) piece9Component.setLocation(219 + 70 * c, 255 + 70 * r);
					}
			}
		}
		
		solveListener sL = new solveListener();
		solveButton.addActionListener(sL);
		

		frame.setLayout(new BorderLayout());
		frame.add(panel, BorderLayout.SOUTH);
		frame.add(board);
		text.setBackground(new Color(245, 250, 250));
		text.setEditable(false);
		text.setLayout(null);
		frame.add(text, BorderLayout.NORTH);
		frame.pack();
		textField.setBounds(frame.getWidth()/2, 0, 350, 80);
		textField.setFont( new Font("Verdana", Font.BOLD, 14));
		textField.setLayout(new BorderLayout());
		text.add(textField);
		final BufferedImage mytroll = ImageIO.read(new URL("http://img1.wikia.nocookie.net/__cb20130819075351/random-laughter/images/7/73/Trollface.png"));
		final JLabel troll= new JLabel();
		textField.add(troll, BorderLayout.EAST);
		
		frame.setResizable(false);
		frame.setVisible(true);
		

		ActionListener listener = new ActionListener(){
			  public void actionPerformed(ActionEvent event){
					boolean solved = true;
					for(int i = 0; i<Display.getPuzzle().getNumRows();i++){
						for(int j = 0; j<Display.getPuzzle().getNumCols();j++){
							if(Display.getPuzzle().getSpot(i, j) == null){
								solved = false;
							}
						}
					}
					if(solved){
						textField.setText("YOU SOLVED THE PUZZLE!!!");
						troll.setIcon(new ImageIcon(mytroll));
					}	
					else{
						textField.setText("Status: Puzzle Unsolved");
						troll.setIcon(null);
						
					}
			  }
		};
			Timer displayTimer = new Timer(1, listener);
			displayTimer.start();
	}
}



