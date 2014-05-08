package education.software.patterns.memorygame.ui;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.wb.swt.SWTResourceManager;

import swing2swt.layout.BorderLayout;
import education.software.patterns.memorygame.MemoryGameApp;
import education.software.patterns.memorygame.model.Board;
import education.software.patterns.memorygame.model.Box;
import education.software.patterns.memorygame.model.MemoryGame.GameDifficulty;

/**
 * This class represents the container for all the controls of the view
 * It is a shell in terms of SWT
 * @author chucho
 */
public class MemoryGameAppShell extends Shell {
	
	/**
	 * The composite that represents the game board
	 */
	private GameBoardComposite gameBoardComposite;
	/**
	 * An instance of the RecallApp (the controller)
	 */
	private MemoryGameApp controller;
	/**
	 * The composite that represent the bottom panel to show information
	 */
	private InfoPanel infoPanel;

	/**
	 * Create the shell.
	 * @param display SWT object that manages the connection between SWT and the underlying operating system
	 * @param controller the instance of the controller
	 */
	public MemoryGameAppShell(Display display, MemoryGameApp controller) {
		super(display, SWT.SHELL_TRIM);
		this.controller = controller;
		createContents();
	}

	/**
	 * Create contents of the shell.
	 */
	protected void createContents() {
		setImage(SWTResourceManager.getImage(MemoryGameApp.class, "/ui/images/grid.png"));
		setMinimumSize(new Point(850, 650));
		setSize(850, 650);
		setText("Pairs Game");
		setLayout(new BorderLayout(0, 0));
		
		Menu menu = new Menu(this, SWT.BAR);
		setMenuBar(menu);
		
		MenuItem mntmGame = new MenuItem(menu, SWT.CASCADE);
		mntmGame.setText("Game");
		
		Menu menu_1 = new Menu(mntmGame);
		mntmGame.setMenu(menu_1);
		
		MenuItem mntmNewGame_1 = new MenuItem(menu_1, SWT.CASCADE);
		mntmNewGame_1.setImage(SWTResourceManager.getImage(MemoryGameAppShell.class, "/ui/images/grid.png"));
		mntmNewGame_1.setText("New game");
		
		Menu menu_2 = new Menu(mntmNewGame_1);
		mntmNewGame_1.setMenu(menu_2);
		
		MenuItem mntmHard = new MenuItem(menu_2, SWT.NONE);
		mntmHard.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				controller.createNewGame(GameDifficulty.HARD);
			}
		});
		mntmHard.setText("Hard");
		
		MenuItem mntmMedium = new MenuItem(menu_2, SWT.NONE);
		mntmMedium.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				controller.createNewGame(GameDifficulty.MEDIUM);
			}
		});
		mntmMedium.setText("Medium");
		
		MenuItem mntmEasy = new MenuItem(menu_2, SWT.NONE);
		mntmEasy.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				controller.createNewGame(GameDifficulty.EASY);
			}
		});
		mntmEasy.setText("Easy");
		
		
		
		new MenuItem(menu_1, SWT.SEPARATOR);
		
		MenuItem mntmExit = new MenuItem(menu_1, SWT.NONE);
		mntmExit.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				controller.exitApplication();
			}
		});
		mntmExit.setImage(SWTResourceManager.getImage(MemoryGameApp.class, "/ui/images/door_in.png"));
		mntmExit.setText("Exit");
		
		gameBoardComposite = new GameBoardComposite(this, SWT.NONE, controller);
		
		Composite composite = new Composite(this, SWT.NONE);
		composite.setLayoutData(BorderLayout.SOUTH);
		composite.setLayout(new FillLayout(SWT.HORIZONTAL));
		
		infoPanel = new InfoPanel(composite, SWT.NONE);

	}
	
	/**
	 * Shows an error message
	 * @param message a String containing the message to be shown
	 */
	public void showErrorMessage(String message) {
		MessageDialog.openError(this, "Error", message);
	}
	
	/**
	 * Sends a load message to the board composite to load 
	 * @param board the model object containing the information
	 */
	public void loadGameBoard(Board board) {
		gameBoardComposite.reload(board);
	}
	
	/**
	 * Sends a message to the board to refresh the graphical box corresponding the box object
	 * @param box the model object containing the information of the box
	 */
	public void refreshBox(Box box) {
		gameBoardComposite.refreshBox(box);
	}
	
	/**
	 * Sends a message to the board to lock a specific box
	 * @param box the model object containing the information of the box to be locked
	 */
	public void lockBox(Box box) {
		gameBoardComposite.lockBox(box);
	}
	
	/**
	 * Sends a message to the board to unlock a specific box
	 * @param box the model object containing the information of the box to be unlocked
	 */
	public void unlockBox(Box box) {
		gameBoardComposite.unlockBox(box);
	}
	
	/**
	 * Sends a message to the board to show a message that appears momentaneously over the game board
	 * @param msg String containing the message to be shown
	 */
	public void showGameMessage(String msg) {
		gameBoardComposite.showGameMessage(msg);
	}
	
	/**
	 * Locks the entire board, so users can't interact with it
	 */
	public void lockBoard() {
		gameBoardComposite.setEnabled(false);	
	}
	
	/**
	 * Unlocks the entire board so user can interact with it
	 */
	public void unlockBoard() {
		gameBoardComposite.setEnabled(true);	
	}
	
	/**
	 * Sends a message to the information panel to update the score
	 * @param score the new score
	 */
	public void updateScore(int score) {
		infoPanel.updateScore(score);
	}
	
	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}
}
