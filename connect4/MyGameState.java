package connect4;
import connect4.codeprovided.Connect4GameState;
import connect4.codeprovided.ColumnFullException;
import connect4.codeprovided.IllegalColumnException;
import connect4.codeprovided.IllegalRowException;

/** MyGameState.java
*
* @author Omar Iltaf
* A class that represents the current state of a Connect4 game,
* including the configuration of the board and whose turn it is.
*
*/
public class MyGameState extends Connect4GameState {

	private int[][] gameBoard = new int[NUM_COLS][NUM_ROWS];
	private int currentTurn;

	//Constructors
	public MyGameState() {
		//Empty constructor
	}

	public MyGameState(int[][] gameBoard, int currentTurn) {
		this.gameBoard = gameBoard;
		this.currentTurn = currentTurn;
	}

	//Initialises board array and sets current turn
	public void startGame() {
		for (int col = 0; col < NUM_COLS; col++) {
			for (int row = 0; row < NUM_ROWS; row++) {
				gameBoard[col][row] = EMPTY;
			}
		}
		currentTurn = RED;
	}

	//Drops in a counter into a specified column
	//Colour is dependent on the current turn
	public void move(int col) throws ColumnFullException, IllegalColumnException {
		if (col < 0 || NUM_COLS - 1 < col) {
			throw new IllegalColumnException(col);
		}

		if (gameBoard[col][NUM_ROWS - 1] != EMPTY) {
			throw new ColumnFullException(col);
		}

		for (int row = 0; row < NUM_ROWS; row++) {
			if (gameBoard[col][row] == EMPTY) {
				gameBoard[col][row] = currentTurn;

				if (currentTurn == RED) {
					currentTurn = YELLOW;
				}
				else {
					currentTurn = RED;
				}

				break;
			}
		}
	}

	//Returns whose turn it is
	public int whoseTurn() {
		return currentTurn;
	}

	//Returns a counter colour at a specified slot on the board
	public int getCounterAt(int col, int row)
			throws IllegalColumnException, IllegalRowException {
		if (col < 0 || NUM_COLS - 1 < col) {
			throw new IllegalColumnException(col);
		}

		if (row < 0 || NUM_ROWS - 1 < row) {
			throw new IllegalRowException(row);
		}

		return gameBoard[col][row];
	}

	//Checks if the top row of the board is empty
	public boolean isBoardFull() {
		for (int[] col : gameBoard) {
			if (col[NUM_ROWS - 1] == EMPTY) {
				return false;
			}
		}
		return true;
	}

	//Checks if the top row of a specified column is empty
	public boolean isColumnFull(int col) throws IllegalColumnException {
		if (col < 0 || NUM_COLS - 1 < col) {
			throw new IllegalColumnException(col);
		}

		return gameBoard[col][NUM_ROWS - 1] != EMPTY;
	}

	//Checks for vertical win
	private int verticalWin() {
		for (int col = 0; col < NUM_COLS; col++) {
			for (int row = 0; row < NUM_ROWS - 3; row++) {
				if (gameBoard[col][row] != EMPTY) {
					boolean vertWin = gameBoard[col][row] == gameBoard[col][row + 1] &&
							gameBoard[col][row] == gameBoard[col][row + 2] &&
							gameBoard[col][row] == gameBoard[col][row + 3];

					if (vertWin) {
						return gameBoard[col][row];
					}
				}
			}
		}
		return EMPTY;
	}

	//Checks for horizontal win
	private int horizontalWin() {
		for (int row = 0; row < NUM_ROWS; row++) {
			for (int col = 0; col < NUM_COLS - 3; col++) {
				if (gameBoard[col][row] != EMPTY) {
					boolean horizWin = gameBoard[col][row] == gameBoard[col + 1][row] &&
							gameBoard[col][row] == gameBoard[col + 2][row] &&
							gameBoard[col][row] == gameBoard[col + 3][row];

					if (horizWin) {
						return gameBoard[col][row];
					}
				}
			}
		}
		return EMPTY;
	}

	//Checks for an upward sloping diagonal win
	private int diagonalUpWin() {
		for (int col = 0; col < NUM_COLS - 3; col++) {
			for (int row = 0; row < NUM_ROWS - 3; row++) {
				if (gameBoard[col][row] != EMPTY) {
					boolean diagUpWin = gameBoard[col][row] == gameBoard[col + 1][row + 1] &&
							gameBoard[col][row] == gameBoard[col + 2][row + 2] &&
							gameBoard[col][row] == gameBoard[col + 3][row + 3];

					if (diagUpWin) {
						return gameBoard[col][row];
					}
				}
			}
		}
		return EMPTY;
	}

	//Checks for a downward sloping diagonal win
	private int diagonalDownWin() {
		for (int col = 0; col < NUM_COLS - 3; col++) {
			for (int row = NUM_ROWS - 1; row > 2; row--) {
				if (gameBoard[col][row] != EMPTY) {
					boolean diagDownWin = gameBoard[col][row] == gameBoard[col + 1][row - 1] &&
							gameBoard[col][row] == gameBoard[col + 2][row - 2] &&
							gameBoard[col][row] == gameBoard[col + 3][row - 3];

					if (diagDownWin) {
						return gameBoard[col][row];
					}
				}
			}
		}
		return EMPTY;
	}

	//Checks the board for all possible winning counter positions
	public int getWinner() {

		if (verticalWin() != EMPTY) {
			return verticalWin();
		}
		else if (horizontalWin() != EMPTY) {
			return horizontalWin();
		}
		else if (diagonalUpWin() != EMPTY) {
			return diagonalUpWin();
		}
		else if (diagonalDownWin() != EMPTY) {
			return diagonalDownWin();
		}
		else {
			//Returned if there is currently no winner
			return EMPTY;
		}
	}

	//Checks if the game can be continued or not
	public boolean gameOver() {
		return isBoardFull() || (getWinner() != EMPTY);
	}

	//Returns a deep-copy of the current instance
	public Connect4GameState copy() {
		int[][] gameBoardCopy = new int[NUM_COLS][NUM_ROWS];
		for (int col = 0; col < NUM_COLS; col++) {
			for (int row = 0; row < NUM_ROWS; row++) {
				gameBoardCopy[col][row] = gameBoard[col][row];
			}
		}

		return new MyGameState(gameBoardCopy, this.currentTurn);
	}

}
