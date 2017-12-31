package connect4;
import connect4.codeprovided.Connect4GameState;
import connect4.codeprovided.Connect4Player;
import connect4.codeprovided.ColumnFullException;
import connect4.codeprovided.IllegalColumnException;

/** RandomPlayer.java
*
* @author Omar Iltaf
* Allows for a computer player to play Connect4.
* The computer's selection choice will be random.
*
*/
public class RandomPlayer extends Connect4Player {

	//Constructor
	public RandomPlayer() {
		//Empty constructor
	}

	//Allows this player to make a move by random selection
	public void makeMove(Connect4GameState gameState) {
		boolean bool = true;
		do {
			try {
				int rand = (int)(Math.random() * (Connect4GameState.NUM_COLS));
				gameState.move(rand);
				System.out.println("Random Computer dropped counter in column " + rand);
				bool = false;
			} catch(ColumnFullException e) {
				//System.out.println("ColumnFullException " + e);
			} catch(IllegalColumnException e) {
				//System.out.println("IllegalColumnException " + e);
			}
		} while(bool);
	}

}
