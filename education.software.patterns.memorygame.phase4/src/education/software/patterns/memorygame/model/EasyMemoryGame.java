package education.software.patterns.memorygame.model;

public class EasyMemoryGame extends MemoryGame {

	@Override
	protected int getNumberofBoxes() {
		return 16;
	}

	@Override
	protected int getNumberOfSeconds() {
		return 60;
	}

	@Override
	protected int getNumberOfRows() {
		return 4;
	}

	@Override
	protected int getNumberOfColumns() {
		return 4;
	}

}
