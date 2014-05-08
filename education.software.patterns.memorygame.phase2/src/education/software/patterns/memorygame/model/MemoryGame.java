package education.software.patterns.memorygame.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * This class represents a game
 * @author jehincapie
 */
public class MemoryGame {
	
	/**
	 * This enumeration defines the different difficulties of the game
	 * @author jehincapie
	 */
	public enum GameDifficulty {
		HARD,
		MEDIUM,
		EASY
	}
	
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
	 * Creates a Recall game initializing the board with random numbers
	 * @param difficulty the difficulty of the new game
	 */
	public MemoryGame(GameDifficulty difficulty) {
		List<Integer> tNumbers = new ArrayList<Integer>();
		
		int numberOfBoxes = getNumberofBoxes(difficulty);
		
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
		
		board = new Board(numbers);
		score = 0;
		timer = new TimerAdapter(getNumberOfSeconds(difficulty));
	}
	
	/**
	 * Gets the number of boxes for the game, according to the difficulty
	 * @param difficulty the difficulty of the game being created
	 * @return an integer indicating the number of boxes
	 */
	private int getNumberofBoxes(GameDifficulty difficulty) {
		switch(difficulty) {
			case HARD:
				return 30;
			case MEDIUM:
				return 24;
			case EASY:
				return 16;
			default:
				return 0;
		}
	}
	
	/**
	 * Gets the number of second for the game's timer, according to the difficulty
	 * @param difficulty the difficulty of the game being created
	 * @return an integer indicating the number of seconds
	 */
	private int getNumberOfSeconds(GameDifficulty difficulty) {
		switch(difficulty) {
			case HARD:
				return 30;
			case MEDIUM:
				return 40;
			case EASY:
				return 60;
			default:
				return 0;
		}
	}
	
	/**
	 * Since a play in the game includes two moves, this method is called when the first move is performed
	 * @param boxNumber the number of the box that was selected
	 */
	public void makeFirstMove(int boxNumber) {
		if(!timer.isRunning() && timer.getRemainingTime() > 0) {
			timer.start();
		}
		board.selectFirstBox(boxNumber);
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
	}
	
	/**
	 * Get the timer of the game
	 * @return the timer
	 */
	public GameCountdownTimer getTimer() {
		return timer;
	}
	
}
