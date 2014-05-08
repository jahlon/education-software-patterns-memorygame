package education.software.patterns.memorygame.model;

import java.util.ArrayList;
import java.util.List;

/**
 * This class represents the board of the recall game
 * @author jehincapie
 */
public class Board {
	
	/**
	 * The path where the images of the game are stored
	 */
	private final String IMAGES_PATH = "./themes/default/";
	
	/**
	 * The set of boxes that make up the panel.
	 */
	private List<Box> boxes;
	
	/**
	 * Counts the number of pairs that have been found in the board
	 */
	private int matchesMade;
	
	/**
	 * An array containing the numbers of the boxes after they were shuffled
	 */
	private int[] numbers;
	
	/**
	 * Indicates the box that was selected in the first move of a play
	 */
	private Box firstBoxSelected;
	
	/**
	 * Indicates the box that was selected in the second move of a play
	 */
	private Box secondBoxSelected;
	
	/**
	 * Creates an instance of the Board class
	 * @param numbers an array of shuffled numbers that are going to be assigned to the boxes
	 */
	public Board(int[] numbers) {
		this.numbers = numbers;
		boxes = new ArrayList<Box>();
		matchesMade = 0;
		initializeBoard();
	}
	
	/**
	 * Selects the box corresponding to the first move of a play
	 * @param number the number of the box
	 */
	public void selectFirstBox(int number) {
		firstBoxSelected = findBox(number);
		firstBoxSelected.select();
	}
	
	/**
	 * Selects the box corresponding to the second move of a play
	 * @param number the number of the box
	 * @return true if a pair was found, false otherwise
	 */
	public boolean selectSecondBox( int number ) {		
		secondBoxSelected = findBox(number);
		secondBoxSelected.select();
		return checkMatch();
	}
	
	/**
	 * Removes the selection of the boxes that were selected in the play
	 */
	public void hideSelectedBoxes() {
		firstBoxSelected.hide();
		secondBoxSelected.hide();
	}
	
	/**
	 * Checks if the board is complete
	 * @return true if all the pairs have been found, false otherwise
	 */
	public boolean isComplete() {
		return matchesMade == (numbers.length / 2);
	}
	
	/**
	 * Checks if a pair has been found with the play
	 * @return true if a pair has been made, false otherwise
	 */
	private boolean checkMatch() {
		if(firstBoxSelected.isPaired(secondBoxSelected)) {
			firstBoxSelected.discover();
			secondBoxSelected.discover();
			matchesMade++;
			return true;
		}
		return false;
	}
	
	/**
	 * Initialize the boxes of the board
	 */
	private void initializeBoard() {
		int numBox = 1;
		
		for (int i=0; i < numbers.length; i++) {
			StringBuffer sb = new StringBuffer(IMAGES_PATH);
			sb.append(numbers[i]).append(".png");
			Box box = new Box(numBox++, numbers[i], sb.toString());
			boxes.add(box);
		}
	}
	
	/**
	 * Finds the box corresponding to a number
	 * @param number the number of the box
	 * @return The box corresponding the the number or null if not box was found
	 */
	private Box findBox( int number ) {
		for(Box box : boxes) {
			if( box.getNumber() == number ) {
				return box;
			}
		}
		return null;
	}
	
	/**
	 * Gets the set of boxes
	 * @return A list containing the set of boxes
	 */
	public List<Box> getBoxes() {
		return boxes;
	}
	
	/**
	 * Gets the number of rows in the board
	 * @return an integer with the number of rows
	 */
	public int getRows() {
		switch(numbers.length) {
			case 16:
				return 4;
			case 24:
				return 4;
			case 30:
				return 5;
			default:
				return -1;
		}
	}
	
	/**
	 * Gets the number of columns in the board
	 * @return an integer with the number of columns
	 */
	public int getColumns() {
		switch(numbers.length) {
			case 16:
				return 4;
			case 24:
				return 6;
			case 30:
				return 6;
			default:
				return -1;
		}
	}
	
	/**
	 * Gets the box that was selected with the first move
	 * @return a Box object
	 */
	public Box getFirsBoxSelected() {
		return firstBoxSelected;
	}
	
	/**
	 * Gets the box  that was selected with the second move
	 * @return a Box object
	 */
	public Box getSecondBoxSelected() {
		return secondBoxSelected;
	}

}
