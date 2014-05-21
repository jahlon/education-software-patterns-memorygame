package education.software.patterns.memorygame.ui;

import org.eclipse.swt.graphics.Image;

public abstract class BoxImageDecorator implements DrawableComponent {

	protected DrawableComponent baseImage;
	
	public BoxImageDecorator(DrawableComponent baseImage) {
		this.baseImage = baseImage;
	}
	
	protected abstract Image getImage();

}
