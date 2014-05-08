package education.software.patterns.memorygame.model;

public class HardMemoryGame extends MemoryGame {

	@Override
	protected int getNumberofBoxes() {
		return 30;
	}

	@Override
	protected int getNumberOfSeconds() {
		return 30;
	}

	@Override
	protected int getNumberOfRows() {
		return 5;
	}

	@Override
	protected int getNumberOfColumns() {
		return 6;
	}

}
