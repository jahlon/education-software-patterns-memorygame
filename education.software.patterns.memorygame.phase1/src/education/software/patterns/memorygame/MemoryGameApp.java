package education.software.patterns.memorygame;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.widgets.Display;

import education.software.patterns.memorygame.model.Box;
import education.software.patterns.memorygame.model.MemoryGame;
import education.software.patterns.memorygame.model.MemoryGame.GameDifficulty;
import education.software.patterns.memorygame.ui.MemoryGameAppShell;

/**
 * This class is a kind of controller that manages communication between the model and the ui.
 * @author jehincapie
 *
 */
public class MemoryGameApp {
	
	/**
	 * The instance of the model (the entry point class)
	 */
	private MemoryGame game;
	
	/**
	 * The instance of the ui (view): The shell that contains all the controls
	 */
	private MemoryGameAppShell shell;
	
	/**
	 * Main execution thread
	 * @param args arguments that are passed to the application for its execution
	 */
	public static void main(String args[]) {
		try {
			Display display = Display.getDefault();
			MemoryGameApp controller = new MemoryGameApp(display);
			MemoryGameAppShell shell = controller.getShell();
			shell.open();
			shell.layout();
			while (!shell.isDisposed()) {
				if (!display.readAndDispatch()) {
					display.sleep();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Creates an instance of the application. It initializes the instance of the view
	 * @param display SWT object that manages the connection between SWT and the underlying operating system
	 */
	public MemoryGameApp(Display display) {
		shell = new MemoryGameAppShell(display, this);
	}
	
	/**
	 * Creates a new instance of the model (the RecallGame object)
	 * @param difficulty the difficulty of the game being created
	 */
	public void createNewGame(GameDifficulty difficulty) {
		if(game != null && !game.isBoardComplete()) {
			boolean confirmation = MessageDialog.openConfirm(shell, 
										"Pairs Game", 
										"Are you sure to cancel the current game and start a new one?");
			if(!confirmation) {
				return;
			}
		}
		
		game = new MemoryGame(difficulty);
		shell.loadGameBoard(game.getBoard());
		shell.updateScore(game.getScore());
	}
	
	/**
	 * Receives the event of the first move and redirects it to the model
	 * @param boxNumber the number of the selected box
	 */
	public void makeFirstMove(int boxNumber) {
		game.makeFirstMove(boxNumber);
		Box box = game.getBoard().getFirsBoxSelected();
		shell.refreshBox(box);
	}
	
	/**
	 * Receives the event of the second move and redirects it to the model. 
	 * Then it check the result (pair made, fail or board completed) and sends a message to the view to update accordingly
	 * @param boxNumber the number of the selected box
	 */
	public void makeSecondMove(int boxNumber) {
		boolean pairMade = game.makeSecondMove(boxNumber);
		final Box box = game.getBoard().getSecondBoxSelected();
		shell.refreshBox(box);
		
		if(pairMade) {
			shell.lockBox(game.getBoard().getFirsBoxSelected());
			shell.lockBox(box);
			shell.updateScore(game.getScore());
			
			if(game.isBoardComplete()) {
				Display.getDefault().timerExec(500, new Runnable() {
					
					@Override
					public void run() {
						shell.showGameMessage("Congratulations!");						
					}
				});
			}
		} else {
			game.getBoard().hideSelectedBoxes();
			shell.lockBoard();
			Display.getDefault().timerExec(500, new Runnable() {
				
				@Override
				public void run() {
					shell.unlockBox(game.getBoard().getFirsBoxSelected());
					shell.refreshBox(game.getBoard().getFirsBoxSelected());
					shell.refreshBox(box);
					shell.unlockBoard();
				}
			});
		}
		
	}
	
	/**
	 * Exits the application
	 */
	public void exitApplication() {
		shell.getDisplay().dispose();
		System.exit(0);
	}
	
	/**
	 * Gets the shell that represents the view
	 * @return Shell that contains of the controls of the view
	 */
	public MemoryGameAppShell getShell() {
		return shell;
	}

}
