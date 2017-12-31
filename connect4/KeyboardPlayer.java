package connect4;
import connect4.codeprovided.Connect4GameState;
import connect4.codeprovided.Connect4Player;
import connect4.codeprovided.ColumnFullException;
import connect4.codeprovided.IllegalColumnException;
import sheffield.EasyReader;

/** KeyboardPlayer.java
*
* @author Omar Iltaf
* Allows for a human player to play Connect4
* via the keyboard input.
*
*/
public class KeyboardPlayer extends Connect4Player {

	//Constructor
	public KeyboardPlayer() {
		//Empty constructor
	}

	//Allows this player to make a move by keyboard input
	public void makeMove(Connect4GameState gameState) {
		boolean bool = true;
		do {
			try {
				EasyReader keyboard = new EasyReader();
				System.out.println("Please enter a column number, 0 to 6 followed by return.");
				int input = keyboard.readInt();
				gameState.move(input);
				bool = false;
			} catch(ColumnFullException e) {
				//System.out.println("ColumnFullException " + e);
			} catch(IllegalColumnException e) {
				//System.out.println("IllegalColumnException " + e);
			}
		} while(bool);
	}

}
