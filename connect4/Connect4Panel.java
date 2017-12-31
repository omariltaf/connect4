package connect4;
import connect4.codeprovided.Connect4GameState;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;


/** Connect4Panel.java
*
* @author Omar Iltaf
* Sets up the panel for the Frame.
*
*/
public class Connect4Panel extends JPanel {

	private final Connect4GameState gameState;
	//These values correspond with column/row positions provided by gameState.
	private int[] xArr = {100, 200, 300, 400, 500, 600, 700};
	private int[] yArr = {600, 500, 400, 300, 200, 100};
	//This array contains details about each slot position on the board.
	//It holds the corresponding -x and -y coordinates compared to the position in gameState.
	//It also holds the colour of the counter in a slot.
	private int[][] slot = new int[3][42];
	private int winner;

	//Constructor
	public Connect4Panel(Connect4GameState gameState) {
		setBackground(Color.white);
		this.gameState = gameState;
		//Initialises array
		for (int i = 0; i < slot.length; i++) {
			for (int j = 0; j < slot[0].length; j++) {
				slot[i][j] = 0;
			}
		}
	}

	//Draws the Connect 4 table
	private void drawGrid(Graphics2D g2) {
		for (int x = 100; x < 900; x += 100) {
			g2.drawLine(x, 100, x, 700);
		}
		for (int y = 200; y < 800; y += 100) {
			g2.drawLine(100, y, 800, y);
		}
	}

	//Displays everything drawn in the panel
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		drawGrid(g2);

		//Cycles through the array for each slot position on the board
		//Draws in a counter with appropriate colour and positioning
		for (int i = 0; i < 42; i++) {
			switch(slot[2][i]) {
				case -1:
					g2.setPaint(new Color(0, 0, 0, 0)); break;
				case 0:
					g2.setPaint(Color.red); break;
				case 1:
					g2.setPaint(Color.yellow); break;
			}
			g2.fillOval(slot[0][i], slot[1][i], 100, 100);
		}

		//Displays whose turn it is and who the winner is
		String text = "";
		int x = 0;
		switch(winner) {
			case -1:
				text = "Current Turn: " + currentTurn();
				x = 230; break;
			case 0:
				text = "Red wins";
				x = 325; break;
			case 1:
				text = "Yellow wins";
				x = 325; break;
		}
		g2.setPaint(Color.black);
		g2.setFont(new Font("Arial", Font.PLAIN, 48));
		g2.drawString(text, x, 775);
	}

	//Sets the array to the corresponding position values of the current gameState
	//Also sets the counter colour at each position
	public void updateBoard() {
		int counter = 0;
		for (int row = Connect4GameState.NUM_ROWS - 1; row >= 0; row--) {
			for (int col = 0; col < Connect4GameState.NUM_COLS; col++) {
				slot[0][counter] = xArr[col];
				slot[1][counter] = yArr[row];
				slot[2][counter] = gameState.getCounterAt(col, row);
				counter++;
			}
		}
		setWinner();
		this.repaint();
	}

	//Sets the winning colour or -1 if there is no winner
	private void setWinner() {
		winner = gameState.getWinner();
	}

	//Returns the player whose turn it is
	private String currentTurn() {
		switch (gameState.whoseTurn()) {
			case 0: return "Red";
			case 1: return "Yellow";
			default: return "Error";
		}
	}

}
