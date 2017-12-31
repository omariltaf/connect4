package connect4;
import connect4.codeprovided.Connect4GameState;
import connect4.codeprovided.Connect4Player;
import connect4.codeprovided.Connect4Displayable;

/** Connect4.java
*
* @author Omar Iltaf
* Handles the instances of the classes required
* to play a Connect4 game.
*
*/
public class Connect4 {

	private Connect4GameState gameState;
	private Connect4Player red;
	private Connect4Player yellow;
	private final Connect4Displayable display;

	//Constructor
	public Connect4(Connect4GameState gameState, Connect4Player red,
			Connect4Player yellow,	Connect4Displayable display) {
		this.gameState = gameState;
		this.red = red;
		this.yellow = yellow;
		this.display = display;
	}

	//Assigns the required player type to the red player
	private boolean newRedPlayer(String newPlayer) {
		Connect4Frame frame = (Connect4Frame) display;
		switch(newPlayer) {
			case "Red Human Player":
				this.red = frame.new ButtonHandler();
				return true;
			case "Red Random Player":
				this.red = new RandomPlayer();
				return true;
			case "Red Intelligent Player":
				this.red = new IntelligentPlayer();
				return true;
			default:
				return false;
		}
	}

	//Assigns the required player type to the yellow player
	private boolean newYellowPlayer(String newPlayer) {
		Connect4Frame frame = (Connect4Frame) display;
		switch(newPlayer) {
			case "Yellow Human Player":
				this.yellow = frame.new ButtonHandler();
				return true;
			case "Yellow Random Player":
				this.yellow = new RandomPlayer();
				return true;
			case "Yellow Intelligent Player":
				this.yellow = new IntelligentPlayer();
				return true;
			default:
				return false;
		}
	}

	//Controls the flow of the game
	public void play(String gameType) {
		if (gameType.equals("-gui")) {
			guiPlay();
		}
		else {
			consolePlay();
		}
	}

	//Handles game play in GUI
	public void guiPlay() {
		boolean bool = true;
		while (bool) {
			bool = false;
			gameState.startGame();
			while (!gameState.gameOver()) {
				display.displayBoard();
				if (gameState.whoseTurn() == Connect4GameState.RED) {
					red.makeMove(gameState);
				}
				else {
					yellow.makeMove(gameState);
				}
				String newPlayer = Connect4Frame.newPlayer;
				if (newRedPlayer(newPlayer) || newYellowPlayer(newPlayer)) {
					bool = true;
					Connect4Frame.newPlayer = "nochange";
					break;
				}
			}
		}
		display.displayBoard();
		switch (gameState.getWinner()) {
			case Connect4GameState.RED: System.out.println("R wins"); break;
			case Connect4GameState.YELLOW: System.out.println("Y wins"); break;
			default: System.out.println("Tie game"); break;
		}
	}

	//Handles game play in console
	public void consolePlay() {
		gameState.startGame();
		while (!gameState.gameOver()) {
			display.displayBoard();
			if (gameState.whoseTurn() == Connect4GameState.RED) {
				red.makeMove(gameState);
			}
			else {
				yellow.makeMove(gameState);
			}
		}
		display.displayBoard();
		switch (gameState.getWinner()) {
			case Connect4GameState.RED: System.out.println("R wins"); break;
			case Connect4GameState.YELLOW: System.out.println("Y wins"); break;
			default: System.out.println("Tie game"); break;
		}
	}

}
