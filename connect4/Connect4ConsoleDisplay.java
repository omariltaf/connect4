package connect4;
import connect4.codeprovided.Connect4GameState;
import connect4.codeprovided.Connect4Displayable;

/** Connect4ConsoleDisplay.java
*
* @author Omar Iltaf
* Displays the current board state to the console.
*
*/
public class Connect4ConsoleDisplay implements Connect4Displayable {

	private final Connect4GameState gameState;

	//Constructor
	public Connect4ConsoleDisplay(Connect4GameState gameState) {
		this.gameState = gameState;
	}

	//Displays the game board for the current state
	public void displayBoard() {
		String slot;
		for (int row = Connect4GameState.NUM_ROWS - 1; row >= 0; row--) {
			System.out.print("|");
			for (int col = 0; col < Connect4GameState.NUM_COLS; col++) {
				switch (gameState.getCounterAt(col, row)) {
					case Connect4GameState.EMPTY: slot = "  "; break;
					case Connect4GameState.RED: slot = " R"; break;
					case Connect4GameState.YELLOW: slot = " Y"; break;
					default: slot = "  "; break;
				}
				System.out.print(slot);
			}
			System.out.println(" |");
		}
		System.out.println(" ---------------");
		System.out.println("  0 1 2 3 4 5 6  ");
	}

}
