package education.software.patterns.memorygame.ui;

import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.graphics.Rectangle;

public class BoxImage implements DrawableComponent {
	
	private Image image;
	
	public BoxImage(Image image) {
		this.image = image;
	}
	
	public void setImage(Image image) {
		this.image = image;
	}
	
	@Override
	public void repaint(GC gc) {
		// Determine how big the drawing area is
        Rectangle rect = gc.getClipping();

        // Get information about the image
        ImageData data = image.getImageData();

        // Calculate drawing values
        int imgX = (rect.width - data.width) / 2;
        int imgY = (rect.height - data.height) / 2;

        // Draw the image
        gc.drawImage(image, imgX, imgY);

	}

}
