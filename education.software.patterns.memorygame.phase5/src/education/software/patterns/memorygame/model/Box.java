package education.software.patterns.memorygame.model;

/**
 * This class represents a box of the board
 * @author jehincapie
 */
public class Box {
	/**
	 * A constant representing the hidden state of the box
	 */
	public static final int HIDDEN = 1;
	/**
	 * A constant representing the discovered state of the box
	 */
	public static final int DISCOVERED = 2;
	/**
	 * A constant representing the visible state of the box
	 */
	public static final int VISIBLE = 3;
	/**
	 * Stores the current state of the box: HIDDEN, DISCOVERED or VISIBLE
	 */
	private int status;
	/**
	 * Stores the number of the box: the number is an index indicating the order of creation of the boxes
	 */
	private int number;
	/**
	 * Stores the value of the box: the value represents the content of the box. It is used to check whether two boxes are equal or not.
	 * The content of the boxes is represented with numbers, and images are associated to those numbers.
	 */
	private int value;
	
	/**
	 * 
	 */
	private boolean special;
	
	/**
	 * Creates an instance of the box
	 * @param number the index of the box within the set of boxes
	 * @param value the value (contents) of the box)
	 * @param special boolean indicating if the box is special
	 */
	public Box( int number, int value, boolean special ) {
		this.number = number;
		this.value = value;
		this.special = special;
		status = HIDDEN;	
	}
	
	/**
	 * Selects a box: it means that the box is now visible
	 */
	public void select() {
		status = VISIBLE;
	}
	
	/**
	 * Discover this box: it means that the box and its pair have been discovered 
	 */
	public void discover() {
		status = DISCOVERED;
	}
	
	/**
	 * Hides a box: it means that the box is now hidden
	 */
	public void hide() {
		status = HIDDEN;
	}
	/**
	 * Gets the number (index) of the box within the set.
	 * @return an integer containing the index of the box
	 */
	public int getNumber() {
		return number;
	}
	
	/**
	 * Gets the current status of the box
	 * @return an integer corresponding to one of the constants: HIDDEN, DISCOVERED OR VISIBLE.
	 */
	public int getStatus() {
		return status;
	}
	
	
	/**
	 * Gets the value of the box
	 * @return an integer with the content of the box
	 */
	public int getValue() {
		return value;
	}
	
	/**
	 * Indicates whether the box is special or not
	 * @return true if the box is special, false otherwise
	 */
	public boolean isSpecial() {
		return special;
	}
	
	/**
	 * Checks whether the box is paired to another
	 * @param b the box which the current instance will be compared with.
	 * @return true if the current instance has the same value (content or image) as the parameter, false otherwise
	 */
	public boolean isPaired(Box b) {
		if(b.getValue() == value) {
			return true;
		}
		return false;
	}
}
