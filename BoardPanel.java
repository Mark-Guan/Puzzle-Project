// Mark Guan
// The graphical interface for the puzzle board.

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Toolkit;

import javax.swing.JPanel;


public class BoardPanel extends JPanel
{
	@Override
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

}


