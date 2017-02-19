/*
 * Display class that  creates the JFrame with a BoardPanel that can hold 9 Pieces and has a solve and clear button
 * 
 * written by Jeffrey Shao and Mark Guan
 */

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.MalformedURLException;
import javax.swing.*;

public class Display {
	private static Puzzle puzzle;
	private static JTextArea text = new JTextArea("Welcome to the Puzzle Game\n"
			+ "Instructions\n"
			+ "-----------------------------------------------\n"
			+ "-Left-Click and Drag a Puzzle Piece to Place It Inside the Board\n"
			+ "-Hover on a Puzzle Piece Outside of the Board and press 'r' to Rotate It");
	
	//Creates a new puzzle, with a PieceBank of 9 pieces and a 3 by 3 board
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
	
	//Returns the puzzle
	public static Puzzle getPuzzle()
	{
		return puzzle;
	}
	
	public static void main(String[] args) throws MalformedURLException, IOException {
		//Generates a puzzle
		generatePuzzle();
		//Creates a JFrame and sets the size and title
		final JFrame frame = new JFrame();	
		frame.setSize(700, 700);
		frame.setPreferredSize(new Dimension(700, 700));
		frame.setTitle("Puzzle Game");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//Creates 2 new JPanels,panel and board, and sets their backgrounds
		JPanel panel = new JPanel();
		JPanel board = new BoardPanel();
		panel.setBackground(new Color(172, 209, 233));
		board.setBackground(new Color(172, 209, 233));
		//Sets the JFrame's layout and adds the JPanels to it
		frame.setLayout(new BorderLayout());
		frame.add(panel, BorderLayout.SOUTH);
		frame.add(board);
		
		//Creates 2 new JButtons, Clear and Solve and adds them to panel
		JButton clearButton = new JButton("Clear");
		JButton solveButton = new JButton("Solve");
		
		//Adds the JTextArea and buttons to panel
		text.setBackground(new Color(245, 250, 250));
		text.setEditable(false);
		panel.add(text);
		panel.add(clearButton);
		panel.add(solveButton);
		
		//adds the panel with the buttons and text
		frame.add(panel, BorderLayout.SOUTH);
		frame.pack();
		
		//Helper Class that implements an ActionListener that will clear the puzzle
		class clearListener implements ActionListener
		{
			@Override
			public void actionPerformed(ActionEvent arg0) 
			{
				frame.setSize(0,0);
				frame.setSize(700,700);
				puzzle.clear();
				BoardPanel.piece1Component.setInBoard(false);
				BoardPanel.piece2Component.setInBoard(false);
				BoardPanel.piece3Component.setInBoard(false);
				BoardPanel.piece4Component.setInBoard(false);
				BoardPanel.piece5Component.setInBoard(false);
				BoardPanel.piece6Component.setInBoard(false);
				BoardPanel.piece7Component.setInBoard(false);
				BoardPanel.piece8Component.setInBoard(false);
				BoardPanel.piece9Component.setInBoard(false);
			}
		}
		//Creates a new solveListener and adds the actionsListener to the solve JButton
		clearListener cL = new clearListener();
		clearButton.addActionListener(cL);
		
		//Helper Class that implements an ActionListener that will solve the puzzle
		class solveListener implements ActionListener
		{
			public void actionPerformed(ActionEvent arg)
			{
				puzzle.solve();
				for (int r = 0; r < 3; r++)
					for (int c = 0; c < 3; c++)
					{
						Piece p = puzzle.getSpot(r,c);
						if (p == Pieces.PIECE_1){
							BoardPanel.piece1Component.setLocation(219 + 70 * c, 255 + 70 * r);
							BoardPanel.piece1Component.setInBoard(true);
						}
						if (p == Pieces.PIECE_2){
							BoardPanel.piece2Component.setLocation(219 + 70 * c, 255 + 70 * r);
							BoardPanel.piece2Component.setInBoard(true);
						}
						if (p == Pieces.PIECE_3){
							BoardPanel.piece3Component.setLocation(219 + 70 * c, 255 + 70 * r);
							BoardPanel.piece3Component.setInBoard(true);
						}
						if (p == Pieces.PIECE_4){
							BoardPanel.piece4Component.setLocation(219 + 70 * c, 255 + 70 * r);
							BoardPanel.piece4Component.setInBoard(true);
						}
						if (p == Pieces.PIECE_5){
							BoardPanel.piece5Component.setLocation(219 + 70 * c, 255 + 70 * r);
							BoardPanel.piece5Component.setInBoard(true);
						}
						if (p == Pieces.PIECE_6){
							BoardPanel.piece6Component.setLocation(219 + 70 * c, 255 + 70 * r);
							BoardPanel.piece6Component.setInBoard(true);
						}
						if (p == Pieces.PIECE_7){
							BoardPanel.piece7Component.setLocation(219 + 70 * c, 255 + 70 * r);
							BoardPanel.piece7Component.setInBoard(true);
						}
						if (p == Pieces.PIECE_8){
							BoardPanel.piece8Component.setLocation(219 + 70 * c, 255 + 70 * r);
							BoardPanel.piece8Component.setInBoard(true);
						}
						if (p == Pieces.PIECE_9){
							BoardPanel.piece9Component.setLocation(219 + 70 * c, 255 + 70 * r);
							BoardPanel.piece9Component.setInBoard(true);
						}
					}
			}
		}
		//Creates a new solveListener and adds the actionsListener to the solve JButton
		solveListener sL = new solveListener();
		solveButton.addActionListener(sL);
		//Sets it so the JFrame cannot resize and makes it visible
		frame.setResizable(false);
		frame.setVisible(true);
		
	}
	

}
