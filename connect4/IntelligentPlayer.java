package connect4;
import connect4.codeprovided.Connect4Player;
import connect4.codeprovided.IllegalColumnException;
import connect4.codeprovided.ColumnFullException;
import connect4.codeprovided.Connect4GameState;
import connect4.codeprovided.Connect4Displayable;

/** IntelligentPlayer.java
*
* @author Omar Iltaf
* Allows for a computer player to play Connect4.
* The computer's selection choice will be intelligent.
*
*/
public class IntelligentPlayer extends Connect4Player {

	//Constructor
	public IntelligentPlayer() {

	}

	//Looks ahead one step
	//Checks if game can be won in a single turn
	private int lookAheadOneStep(Connect4GameState gameState) {
		int turn = 0;
		for (int i = 0; i < Connect4GameState.NUM_COLS; i++) {
			Connect4GameState gameStateCopy = gameState.copy();
			try {
				gameStateCopy.move(i);
				turn = gameState.whoseTurn();
			} catch(ColumnFullException e) {
				//System.out.println("ColumnFullException " + e);
			} catch(IllegalColumnException e) {
				//System.out.println("IllegalColumnException " + e);
			}
			if (gameStateCopy.getWinner() == turn) {
				return i;
			}
		}
		return 404;
	}

	//Looks ahead two steps
	//Checks if game can be won in two turns
	private int lookAheadTwoStep(Connect4GameState gameState) {
		//First turn column selection
		for (int i = 0; i < Connect4GameState.NUM_COLS; i++) {
			Connect4GameState gameStateCopy = gameState.copy();
			int turn = 0;
			try {
				turn = gameState.whoseTurn();
				gameStateCopy.move(i);
				int rand = (int)(Math.random() * (Connect4GameState.NUM_COLS));
				//Second turn column selection
				for (int j = 0; j < Connect4GameState.NUM_COLS; j++) {
					Connect4GameState gameStateCopy2 = gameStateCopy.copy();
					try {
						//Assumes opposing player selects column at random
						gameStateCopy2.move(rand);
						gameStateCopy2.move(j);
					} catch(ColumnFullException e) {
						//System.out.println("ColumnFullException " + e);
					} catch(IllegalColumnException e) {
						//System.out.println("IllegalColumnException " + e);
					}
					if (gameStateCopy2.getWinner() == turn) {
						return i;
					}
				}
			} catch(ColumnFullException e) {
				//System.out.println("ColumnFullException " + e);
			} catch(IllegalColumnException e) {
				//System.out.println("IllegalColumnException " + e);
			}
		}
		return 404;
	}

	//Makes an intelligent column choice
	private int intelligentChoice(Connect4GameState gameState) {
		int oneStep = lookAheadOneStep(gameState);
		int twoStep = lookAheadTwoStep(gameState);
		if (oneStep != 404) {
			return oneStep;
		}
		else if (twoStep != 404) {
			return twoStep;
		}

		//If not two steps away from a win, a column is selected randomly
		int rand = (int)(Math.random() * (Connect4GameState.NUM_COLS));
		return rand;
	}

	//Allows this player to make a move by intelligent selection
	public void makeMove(Connect4GameState gameState) {
		boolean bool = true;
		do {
			try {
				int choice = intelligentChoice(gameState);
				gameState.move(choice);
				System.out.println("Intelligent Computer dropped counter in column " + choice);
				bool = false;
			} catch(ColumnFullException e) {
				//System.out.println("ColumnFullException " + e);
			} catch(IllegalColumnException e) {
				//System.out.println("IllegalColumnException " + e);
			}
		} while(bool);
	}

}
