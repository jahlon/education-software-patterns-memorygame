package education.software.patterns.memorygame.ui;

import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.GC;

public class HiddenState extends BoxState {

	public HiddenState(DrawableComponent image) {
		super(image);
	}

	@Override
	public void repaint(GC gc) {
		Color c = gc.getBackground();
		gc.setBackground(c);
		gc.fillRectangle(gc.getClipping());
		boxImage.paint(gc);
	}

}
