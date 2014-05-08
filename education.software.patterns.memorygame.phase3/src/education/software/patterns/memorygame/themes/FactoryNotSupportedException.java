package education.software.patterns.memorygame.themes;

public class FactoryNotSupportedException extends RuntimeException {

	private static final long serialVersionUID = -2829712380263197000L;

	public FactoryNotSupportedException(String msg) {
		super(msg);
	}

}
