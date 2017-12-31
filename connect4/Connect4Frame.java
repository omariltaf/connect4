package connect4;
import connect4.codeprovided.Connect4GameState;
import connect4.codeprovided.Connect4Player;
import connect4.codeprovided.IllegalColumnException;
import connect4.codeprovided.ColumnFullException;
import connect4.codeprovided.Connect4Displayable;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Container;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Dimension;
import java.awt.Color;

/** Connect4Frame.java
*
* @author Omar Iltaf
* Sets up the Frame for the GUI.
*
*/
public class Connect4Frame extends JFrame implements Connect4Displayable {

	//Non-static member class for event-handling
	public class ButtonHandler extends Connect4Player implements ActionListener {

		//Constructor
		public ButtonHandler() {
			//Empty Constructor
		}

		public void makeMove(Connect4GameState gameState) {

		}

		//Event-handling
		public void actionPerformed(ActionEvent e) {
			String buttonPressed = e.getActionCommand();
			boolean changePlayer = buttonPressed == "Red Human Player" ||
					buttonPressed == "Red Random Player" ||
					buttonPressed == "Red Intelligent Player" ||
					buttonPressed == "Yellow Human Player" ||
					buttonPressed == "Yellow Random Player" ||
					buttonPressed == "Yellow Intelligent Player";
			if (changePlayer) {
				newPlayer = buttonPressed;
			}
			else {
				newPlayer = "nochange";
				try {
					int col = Integer.valueOf(buttonPressed);
					gameState.move(col);
				} catch(ColumnFullException e1) {
					//System.out.println("ColumnFullException " + e1);
				} catch(IllegalColumnException e1) {
					//System.out.println("IllegalColumnException " + e1);
				}
			}
		}
	}

	public static String newPlayer = "";
	private final Connect4Panel boardPanel;
	private final Connect4GameState gameState;
	private final ButtonHandler handler = new ButtonHandler();

	//Constructor
	public Connect4Frame(Connect4GameState gameState) {
		this.gameState = gameState;
		setTitle("Connect 4 GUI Display");
		setSize(1250, 1000);

		Container contentPane = this.getContentPane();
		boardPanel = new Connect4Panel(gameState);
		//contentPane.setLayout(new BorderLayout());

		contentPane.add(boardPanel, BorderLayout.CENTER);

		JPanel rowChoiceButtons = new JPanel(new GridLayout(1, 7));
		for (int i = 0; i < 7; i++) {
			makeButton(rowChoiceButtons, String.valueOf(i), new ButtonHandler(),
					Color.gray, new Dimension(250, 100));
		}
		contentPane.add(rowChoiceButtons, BorderLayout.SOUTH);

		Dimension buttonSize = new Dimension(170, 100);
		JPanel redChoices = new JPanel(new GridLayout(3, 1));
		makeButton(redChoices, "Red Human Player", handler, Color.red, buttonSize);
		makeButton(redChoices, "Red Random Player", handler, Color.red, buttonSize);
		makeButton(redChoices, "Red Intelligent Player", handler, Color.red, buttonSize);
		contentPane.add(redChoices, BorderLayout.WEST);

		JPanel yellowChoices = new JPanel(new GridLayout(3, 1));
		makeButton(yellowChoices, "Yellow Human Player", handler, Color.yellow, buttonSize);
		makeButton(yellowChoices, "Yellow Random Player", handler, Color.yellow, buttonSize);
		makeButton(yellowChoices, "Yellow Intelligent Player", handler, Color.yellow, buttonSize);
		contentPane.add(yellowChoices, BorderLayout.EAST);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}

	//Creates a button of appropriate attributes and links it to an event handler
	//Also adds it to a panel
	private void makeButton(JPanel rowChoiceButtons, String name,
			ActionListener target, Color color, Dimension size) {
		JButton button = new JButton(name);
		button.setPreferredSize(size);
		button.setBackground(color);
		button.addActionListener(target);
		rowChoiceButtons.add(button);
	}

	//Displays the current board state
	public void displayBoard() {
		boardPanel.updateBoard();
	}

}
