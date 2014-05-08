package education.software.patterns.memorygame.themes;

public class DefaultThemeFactory extends ThemeFactory {

	@Override
	public Theme createTheme() {
		return new DefaultTheme();
	}

}
