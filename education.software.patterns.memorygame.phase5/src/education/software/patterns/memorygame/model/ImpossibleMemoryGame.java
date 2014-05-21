package education.software.patterns.memorygame.model;

public class ImpossibleMemoryGame extends MemoryGame {

	@Override
	protected int getNumberofBoxes() {
		return 42;
	}

	@Override
	protected int getNumberOfSeconds() {
		return 30;
	}

	@Override
	protected int getNumberOfRows() {
		return 7;
	}

	@Override
	protected int getNumberOfColumns() {
		return 6;
	}

}
