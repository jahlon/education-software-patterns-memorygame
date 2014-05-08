package education.software.patterns.memorygame.ui;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;

import education.software.patterns.memorygame.MemoryGameApp;
import education.software.patterns.memorygame.model.Board;
import education.software.patterns.memorygame.model.Box;
import education.software.patterns.memorygame.themes.Theme;

/**
 * This class represents the graphic board in which the game is played
 * @author chucho
 */
public class GameBoardComposite extends Composite {
	
	/**
	 * Represents the code for non background
	 */
	public static final int BG_NONE = 0;
	
	/**
	 * Represents the code for green background of the board boxes
	 */
	public static final int BG_GREEN = 1;
	
	/**
	 * The list of boxes that make up the game board.
	 */
	private List<GraphicalBox> gBoxes;
	
	/**
	 * Indicates the number of moves made in the board in each turn
	 */
	private int moves;
	
	/**
	 * This object acts as a controller for the event generated in this composite
	 */
	private MemoryGameApp controller;
	
	/**
	 * Create the composite.
	 * @param parent
	 * @param style 
	 * @param controller An instance of the RecallApp that acts as a controller
	 */
	public GameBoardComposite(Composite parent, int style, MemoryGameApp controller) {
		super(parent, style);
		this.controller = controller;
		setBackground(Display.getCurrent().getSystemColor(SWT.COLOR_WHITE));
		setLayout(new GridLayout(1, false));
		moves = 0;
	}
	
	/**
	 * Initializes the boxes of the board
	 * @param board the model object containing the board information
	 * @param theme the Theme of the application
	 */
	protected void initializeBoard(Board board, Theme theme) {
		gBoxes = new ArrayList<GraphicalBox>();
		GridLayout gridLayout = new GridLayout(board.getColumns(), true);
		gridLayout.marginTop = 5;
		gridLayout.marginRight = 5;
		gridLayout.marginLeft = 5;
		gridLayout.marginBottom = 5;
		setLayout(gridLayout);
		
		List<Box> boxes = board.getBoxes();
		for (Box box : boxes) {
			GraphicalBox gb = new GraphicalBox(this, 
												SWT.BORDER | SWT.CENTER, 
												box.getStatus() == Box.HIDDEN? theme.getHiddenImage() : theme.getImage(box.getValue()), 
												gBoxes.size()+1);
			GridData gData = new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1);
			gData.verticalIndent = 5;
			gData.horizontalIndent = 5;
			gb.setLayoutData(gData);
			gBoxes.add(gb);
			gb.addListener(SWT.MouseDown, new Listener() {
				
				@Override
				public void handleEvent(Event event) {
					moves++;
					int boxNumber = ((GraphicalBox)event.widget).getNumber();
					if( moves % 2 == 1) {
						controller.makeFirstMove(boxNumber);
					} else {
						controller.makeSecondMove(boxNumber);
					}
				}
			});
		}
		layout();
	}
	
	/**
	 * Reloads the boxes of the board
	 * @param board the model object containing the board information
	 * @param theme the Theme of the application
	 */
	public void reload(Board board, Theme theme) {
		moves = 0;
		if(gBoxes != null) {
			for (GraphicalBox box : gBoxes) {
				box.dispose();
			}
		}
		redraw();
		initializeBoard(board, theme);
	}
	
	/**
	 * Finds a GraphicalBox object corresponding to a Box object
	 * @param box the model object containing the information of the box
	 * @return A GraphicalBox instance, null if no object is found
	 */
	protected GraphicalBox findBox(Box box) {
		return gBoxes.get(box.getNumber() - 1);
	}
		
	/**
	 * Repaints the graphical box corresponding to box specified as a parameter
	 * @param box the model object containing the information of the box
	 * @param theme the Theme of the application
	 */
	public void refreshBox(Box box, Theme theme) {
		GraphicalBox gBox = findBox(box);
		gBox.setImage(box.getStatus() == Box.HIDDEN ? theme.getHiddenImage() : theme.getImage(box.getValue()));
		gBox.redraw();	
		if(box.getStatus() == Box.VISIBLE || box.getStatus() == Box.DISCOVERED) {
			gBox.setEnabled(false);
		} else {
			gBox.setEnabled(true);
		}
	}
	
	/**
	 * Locks the graphical box corresponding to the box specified as a parameter
	 * @param box the model object containing the information of the box
	 */
	public void lockBox(Box box) {
		GraphicalBox gBox = findBox(box);
		gBox.lock(BG_GREEN);
		gBox.redraw();
	}

	/**
	 * Unlocks the graphical box corresponding to the box specified as a parameter
	 * @param box the model object containing the information of the box
	 */
	public void unlockBox(Box box) {
		GraphicalBox gBox = findBox(box);
		gBox.unlock();
		
	}
	
	/**
	 * Show a white region in the center of the board with the specified message. The region is visible only for 2 seconds
	 * @param msg String containing the message to be shown
	 */
	public void showGameMessage(String msg) {
		GameMessageDialog gameMsg = new GameMessageDialog(getShell());
		gameMsg.setMessage(msg);
		
		int x = (getShell().getClientArea().width - gameMsg.getSize().x)/2 + getShell().getLocation().x;
		int y = (getShell().getClientArea().height - gameMsg.getSize().y)/2 + getShell().getLocation().y;
		gameMsg.setLocation(x, y);
		gameMsg.open();
		
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		gameMsg.dispose();
	}
	
	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}

}
