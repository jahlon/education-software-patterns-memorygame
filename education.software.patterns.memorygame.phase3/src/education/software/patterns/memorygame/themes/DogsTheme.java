package education.software.patterns.memorygame.themes;

import org.eclipse.swt.graphics.Image;
import org.eclipse.wb.swt.SWTResourceManager;

public class DogsTheme extends Theme {

	@Override
	public Image getImage(int number) {
		StringBuffer imgPath = new StringBuffer("./themes/dogs/")
		.append(Integer.toString(number)).append(".png");

		return SWTResourceManager.getImage(imgPath.toString());
	}

}
