package education.software.patterns.memorygame.ui;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.graphics.Rectangle;
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
	private Image image;
	
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
	public GraphicalBox(Composite parent, int style, Image image, int number) {
		super(parent, style);
		this.image = image;
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
	protected void repaint(GC gc) {
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
		
		// Determine how big the drawing area is
        Rectangle rect = getClientArea();

        // Get information about the image
        ImageData data = image.getImageData();

        // Calculate drawing values
        int imgX = (rect.width - data.width) / 2;
        int imgY = (rect.height - data.height) / 2;

        // Draw the image
        gc.drawImage(image, imgX, imgY);
		
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
		this.image = image;
	}
	
	/**
	 * Gets the number of the box in the board
	 * @return an integer number
	 */
	public int getNumber() {
		return number;
	}

}
