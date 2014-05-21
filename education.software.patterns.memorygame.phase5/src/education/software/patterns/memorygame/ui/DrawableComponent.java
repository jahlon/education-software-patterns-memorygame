package education.software.patterns.memorygame.ui;

import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Image;

public interface DrawableComponent {
	
	void repaint(GC gc);
	void setImage(Image image);

}
