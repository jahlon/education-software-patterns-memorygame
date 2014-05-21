package education.software.patterns.memorygame.model;

public class MediumMemoryGame extends MemoryGame {

	@Override
	protected int getNumberofBoxes() {
		return 24;
	}

	@Override
	protected int getNumberOfSeconds() { 
		return 40;
	}

	@Override
	protected int getNumberOfRows() {
		return 4;
	}

	@Override
	protected int getNumberOfColumns() {
		return 6;
	}

}
