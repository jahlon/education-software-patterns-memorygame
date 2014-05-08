package education.software.patterns.memorygame.themes;

import org.eclipse.swt.graphics.Image;
import org.eclipse.wb.swt.SWTResourceManager;

public abstract class Theme {
	
	public Image getHiddenImage() {
		return SWTResourceManager.getImage(getClass(), "/ui/images/help.png");
	}
	
	public abstract Image getImage(int number);
	
}
