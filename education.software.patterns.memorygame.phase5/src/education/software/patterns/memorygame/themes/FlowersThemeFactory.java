package education.software.patterns.memorygame.themes;

public class FlowersThemeFactory extends ThemeFactory {

	@Override
	public Theme createTheme() {
		return new FlowersTheme();
	}

}
