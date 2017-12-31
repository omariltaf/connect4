package connect4;
import connect4.codeprovided.Connect4GameState;
import connect4.codeprovided.Connect4Player;

import connect4.codeprovided.Connect4Displayable;

/** PlayConnect4.java
*
* @author Omar Iltaf
* Used to run the program and play a game of Connect4.
* Creates all the required instances.
*
*/
public abstract class PlayConnect4 {
	public static void main(String[] args) {

		if (args[0].equals("-nogui")) {
			Connect4GameState gameState = new MyGameState();
			Connect4Displayable display = new Connect4ConsoleDisplay(gameState);

			//Available players - Keyboard Human, Random Computer and Intelligent Computer
			Connect4Player red = new RandomPlayer();
			//Connect4Player red = new IntelligentPlayer();
			//Connect4Player red = new KeyboardPlayer();

			//Connect4Player yellow = new RandomPlayer();
			//Connect4Player yellow = new IntelligentPlayer();
			Connect4Player yellow = new KeyboardPlayer();

			Connect4 game = new Connect4(gameState, red, yellow, display);
			game.play(args[0]);
		}
		else if (args[0].equals("-gui")) {
			Connect4GameState gameState = new MyGameState();
			Connect4Displayable display = new Connect4Frame(gameState);

			//Available players - GUI Human, Random Computer and Intelligent Computer
			//Players can be changed in game
			Connect4Player red = new RandomPlayer();

			Connect4Frame frame = (Connect4Frame) display;
			Connect4Player yellow = frame.new ButtonHandler();

			Connect4 game = new Connect4(gameState, red, yellow, display);
			game.play(args[0]);
		}
		else {
			System.out.println("Please run with a correct command: (-nogui/-gui)");
		}
	}

}
