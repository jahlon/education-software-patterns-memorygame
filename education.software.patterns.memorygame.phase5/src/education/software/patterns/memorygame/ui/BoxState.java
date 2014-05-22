package education.software.patterns.memorygame.ui;

import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Image;

public abstract class BoxState {
	
	protected DrawableComponent boxImage;
	
	public BoxState(DrawableComponent image) {
		this.boxImage = image;
	}
	
	public void setImage(Image image) {
		boxImage.setImage(image);
	}
	
	public abstract void repaint(GC gc);
	
}
