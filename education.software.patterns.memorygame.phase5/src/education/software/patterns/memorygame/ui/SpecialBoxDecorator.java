package education.software.patterns.memorygame.ui;

import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.wb.swt.SWTResourceManager;

public class SpecialBoxDecorator extends BoxImageDecorator {

	public SpecialBoxDecorator(DrawableComponent baseImage) {
		super(baseImage);
	}

	@Override
	public void paint(GC gc) {
		baseImage.paint(gc);
		
		// Determine how big the drawing area is
        Rectangle rect = gc.getClipping();
        
        Image decorationImage = getImage();
        
        // Get information about the image
        ImageData data = decorationImage.getImageData();

        // Calculate drawing values
        int imgX = rect.width - data.width;
        int imgY = 0;

        // Draw the image
        gc.drawImage(decorationImage, imgX, imgY);

	}

	@Override
	protected Image getImage() {
		return SWTResourceManager.getImage(getClass(), "/ui/images/star.png");
	}

	@Override
	public void setImage(Image image) {
		baseImage.setImage(image);
	}

}
