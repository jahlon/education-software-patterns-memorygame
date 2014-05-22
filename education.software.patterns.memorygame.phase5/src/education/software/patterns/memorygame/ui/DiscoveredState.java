package education.software.patterns.memorygame.ui;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.widgets.Display;

public class DiscoveredState extends BoxState {

	public DiscoveredState(DrawableComponent image) {
		super(image);
	}

	@Override
	public void repaint(GC gc) {
		Color c = Display.getDefault().getSystemColor(SWT.COLOR_GREEN);
		gc.setBackground(c);
		gc.fillRectangle(gc.getClipping());
		boxImage.paint(gc);
	}

}
