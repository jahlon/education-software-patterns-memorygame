package education.software.patterns.memorygame.themes;

public class MonstersThemeFactory extends ThemeFactory {

	@Override
	public Theme createTheme() {
		return new MonstersTheme();
	}

}
