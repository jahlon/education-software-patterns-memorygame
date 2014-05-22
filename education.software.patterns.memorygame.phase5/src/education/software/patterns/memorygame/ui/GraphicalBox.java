package education.software.patterns.memorygame.ui;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;

import education.software.patterns.memorygame.model.Box;
import education.software.patterns.memorygame.themes.Theme;

/**
 * This class represent a graphical box in the game board
 * @author jehincapie
 */
public class GraphicalBox extends Canvas {
	
	/**
	 * The number of the box in the board
	 */
	private int number;
	
	/**
	 * The state of the box
	 */
	private BoxState state;
	
	private BoxState hiddenState;
	private BoxState uncoveredState;
	private BoxState discoveredState;
	
	/**
	 * Creates the canvas and call the repaint method in order to refresh the image
	 * @param parent
	 * @param style
	 * @param boxImage
	 * @param number
	 */
	public GraphicalBox(Composite parent, int style, int number, Box box, Theme theme) {
		super(parent, style);
		this.number = number;
		Image image = theme.getHiddenImage();
		hiddenState = new HiddenState(new BoxImage(image));
		uncoveredState = new UncoveredState(box.isSpecial() ? new SpecialBoxDecorator(new BoxImage(image)) : new BoxImage(image));
		discoveredState = new DiscoveredState(box.isSpecial() ? new SpecialBoxDecorator(new BoxImage(image)) : new BoxImage(image));
		state = hiddenState;
		
		addListener(SWT.Paint, new Listener() {

			@Override
			public void handleEvent(Event event) {
				repaint(event.gc);
			}
			
		});
	}
	
	/**
	 * Repaint the canvas every time an event occurs
	 * @param gc the graphical object
	 */
	public void repaint(GC gc) {
		state.repaint(gc);
	}
	
	/**
	 * Locks the box so it can't receive events
	 */
	public void lock() {
		state = uncoveredState;
		setEnabled(false);
	}
	
	/**
	 * Unlocks the box so it can receive events
	 */
	public void unlock() {
		state = hiddenState; 
		setEnabled(true);
	}
	
	/**
	 * 
	 */
	public void discover() {
		state = discoveredState;
		setEnabled(false);
	}
	
	/**
	 * Sets the image of the box
	 * @param image the new image
	 */
	public void setImage(Image image) {
		state.setImage(image);
	}
	
	/**
	 * Gets the number of the box in the board
	 * @return an integer number
	 */
	public int getNumber() {
		return number;
	}

}
