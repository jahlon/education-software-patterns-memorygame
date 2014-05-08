package education.software.patterns.memorygame.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * This class represents a game
 * @author jehincapie
 */
public abstract class MemoryGame {
	
	/**
	 * The board of the game
	 */
	private Board board;
	
	/**
	 * The timer of the game
	 */
	private GameCountdownTimer timer;
	
	/**
	 * Keeps the score of the game 
	 */
	private int score;
	
	/**
	 * Indicates if the game has started or not
	 */
	private boolean started;
	
	/**
	 * Creates a Recall game initializing the board with random numbers
	 * @param difficulty the difficulty of the new game
	 */
	protected MemoryGame() {
		initializeGame();
	}

	private void initializeGame() {
		List<Integer> tNumbers = new ArrayList<Integer>();
		
		int numberOfBoxes = getNumberofBoxes();
		
		for(int i = 1; i <= numberOfBoxes; i++) {
			int number = ( i % (numberOfBoxes/2) ) + 1;
			tNumbers.add(number);
		}
		
		Collections.shuffle(tNumbers);
		
		int[] numbers = new int[tNumbers.size()];
		int i=0;
		for (Integer n : tNumbers) {
			numbers[i++] = n.intValue();
		}
		
		board = new Board(numbers, getNumberOfRows(), getNumberOfColumns());
		score = 0;
		timer = new TimerAdapter(getNumberOfSeconds());
		started = false;
	}
	
	/**
	 * Gets the number of boxes for the game, according to the difficulty
	 * @return an integer indicating the number of boxes
	 */
	protected abstract int getNumberofBoxes();
	
	/**
	 * Gets the number of second for the game's timer, according to the difficulty
	 * @return an integer indicating the number of seconds
	 */
	protected abstract int getNumberOfSeconds();
	
	/**
	 * Gets the number of rows that the board of the game will have
	 * @return an integer indicating the number of rows
	 */
	protected abstract int getNumberOfRows();
	
	/**
	 * Gets the number of columns that the board of the game will have
	 * @return an integer indicating the number of columns
	 */
	protected abstract int getNumberOfColumns();
	
	/**
	 * Since a play in the game includes two moves, this method is called when the first move is performed
	 * @param boxNumber the number of the box that was selected
	 */
	public void makeFirstMove(int boxNumber) {
		if(!timer.isRunning() && timer.getRemainingTime() > 0) {
			timer.start();
		}
		board.selectFirstBox(boxNumber);
		started = true;
	}

	/**
	 * Since a play in the game includes two moves, this method is called when the second move is performed
	 * @param boxNumber the number of the box that was selected
	 * @return true if a pair was made, false otherwise.
	 */
	public boolean makeSecondMove(int boxNumber) {
		boolean pairMade = board.selectSecondBox(boxNumber);
		if(pairMade)
			score++;
		return pairMade;
	}
	
	/**
	 * Checks whether all the pairs in the board have been discovered or not
	 * @return true if all the pairs have been discovered, false otherwise
	 */
	public boolean isBoardComplete() {
		return board.isComplete();
	}
	
	/**
	 * Gets the board of the game
	 * @return the Board object
	 */
	public Board getBoard() {
		return board;
	}
	
	/**
	 * Gets the score of the game
	 * @return the current score
	 */
	public int getScore() {
		return score;
	}
	
	/**
	 * Gets the number of seconds remaining
	 * @return the number of seconds
	 */
	public int getSecondsRemaining() {
		return timer.getRemainingTime();
	}
	
	/**
	 * Stops the timer of the game
	 */
	public void finish() {
		timer.stop();
		score += timer.getRemainingTime();
		started = false;
	}
	
	/**
	 * Get the timer of the game
	 * @return the timer
	 */
	public GameCountdownTimer getTimer() {
		return timer;
	}
	
	/**
	 * Indicates if the game has started
	 * @return true if the game has started, false otherwise
	 */
	public boolean hasStarted() {
		return started;
	}
	
}
