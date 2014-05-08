package education.software.patterns.memorygame.ui;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Shell;

/**
 * This class represents a region for showing a game-related message
 * @author chucho
 */
public class GameMessageDialog extends Shell {

	/**
	 * The message to be shown
	 */
	private String message;
	
	/**
	 * Create the dialog.
	 * @param parent
	 * @param style
	 */
	public GameMessageDialog(Shell parent) {
		super(parent, SWT.NO_TRIM | SWT.APPLICATION_MODAL);
		createContents();
	}
	
	/**
	 * Sets the message that is going to be shown
	 * @param message String containing the message
	 */
	public void setMessage(String message) {
		this.message = message;
	}
	
	/**
	 * Repaint the area according to the client are of its parent
	 * @param gc SWT graphical object
	 */
	private void repaint(GC gc) {
		gc.setForeground(getDisplay().getSystemColor(SWT.COLOR_BLACK));
		gc.setBackground(getDisplay().getSystemColor(SWT.COLOR_WHITE));
		Rectangle rect = getClientArea();
		rect.height -= 1;
		rect.width -= 1;
		gc.fillRectangle(rect);
		gc.drawRectangle(rect);
		
		gc.setForeground(getDisplay().getSystemColor(SWT.COLOR_GREEN));
		
		Font f = new Font(getDisplay(), "Tahoma", 30, SWT.BOLD);
		gc.setFont(f);
		Point textSize = gc.textExtent(message);
		
		gc.drawText(message, 
				(rect.width - textSize.x) / 2,
				(rect.height - textSize.y) / 2, true);
		f.dispose();
	}

	/**
	 * Create contents of the dialog.
	 */
	private void createContents() {
		
		setSize(411, 135);
		setText(getText());
		
		addPaintListener(new PaintListener() {
			public void paintControl(PaintEvent e) {
				repaint(e.gc);
			}
		});

	}
	
	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}

}
