package education.software.patterns.memorygame.themes;

public class DogsThemeFactory extends ThemeFactory {

	@Override
	public Theme createTheme() {
		return new DogsTheme();
	}

}
