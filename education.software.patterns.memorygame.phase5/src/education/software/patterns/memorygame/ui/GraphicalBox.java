package education.software.patterns.memorygame.ui;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;

/**
 * This class represent a graphical box in the game board
 * @author jehincapie
 */
public class GraphicalBox extends Canvas {
	
	/**
	 * Represents the image that is being painted in the canvas
	 */
	private DrawableComponent boxImage;
	
	/**
	 * The number of the box in the board
	 */
	private int number;
	
	/**
	 * The background color of the box
	 */
	private int bgColor;
	
	/**
	 * Creates the canvas and call the repaint method in order to refresh the image
	 * @param parent
	 * @param style
	 * @param image
	 * @param number
	 */
	public GraphicalBox(Composite parent, int style, Image image, int number, boolean special) {
		super(parent, style);
		this.boxImage = special ? new SpecialBoxDecorator(new BoxImage(image)) : new BoxImage(image);
		this.number = number;
		
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
		if(!getEnabled()) {
			Color c = null;
			if(bgColor == GameBoardComposite.BG_GREEN) {
				c = getDisplay().getSystemColor(SWT.COLOR_GREEN);
			} else {
				c = gc.getBackground();
			}
			gc.setBackground(c);
			gc.fillRectangle(getClientArea());
		}
		
		boxImage.repaint(gc);
		
	}
	
	/**
	 * Locks the box so it can receive events
	 * @param bgColor a color that is going to be set as background when the box is locked
	 */
	public void lock(int bgColor) {
		this.bgColor = bgColor;
		setEnabled(false);
	}
	
	/**
	 * Unlocks the box so it can receive events
	 */
	public void unlock() {
		setEnabled(true);
	}
	
	/**
	 * Sets the image of the box
	 * @param image the new image
	 */
	public void setImage(Image image) {
		boxImage.setImage(image);
	}
	
	/**
	 * Gets the number of the box in the board
	 * @return an integer number
	 */
	public int getNumber() {
		return number;
	}

}
