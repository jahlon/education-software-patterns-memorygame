package education.software.patterns.memorygame.themes;


public abstract class ThemeFactory {
	
	public static final int DEFAULT_THEME = 1;
	public static final int FLOWERS_THEME = 2;
	public static final int DOGS_THEME = 3;
	
	public static ThemeFactory getFactory( int theme ) throws FactoryNotSupportedException {
		switch(theme) {
			case DEFAULT_THEME:
				return new DefaultThemeFactory();
			case FLOWERS_THEME:
				return new FlowersThemeFactory();
			case DOGS_THEME:
				return new DogsThemeFactory();
			default:
				throw new FactoryNotSupportedException("Theme not supported");
					
		}
	}
	
	public abstract Theme createTheme();
	
}
