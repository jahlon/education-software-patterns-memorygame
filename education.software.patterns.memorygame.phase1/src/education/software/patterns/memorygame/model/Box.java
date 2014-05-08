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
	 * Stores the path of the image that is shown when the box is visible
	 */
	private String image;
	/**
	 * Stores the path of the image that is shown when the box is hidden
	 */
	private String hiddenImage;
	
	/**
	 * Creates an instance of the box
	 * @param number the index of the box whithin the set of boxes
	 * @param value the value (contents) of the box)
	 * @param image the image that is going to be shown when the box is visible
	 */
	public Box( int number, int value, String image ) {
		this.number = number;
		this.value = value;
		this.image = image;
		hiddenImage = "/ui/images/help.png";
		status = HIDDEN;	
	}
	
	/**
	 * Selects a box: it means that the box is now visible
	 */
	public void select() {
		status = VISIBLE;
	}
	
	/**
	 * Dicovers a box: it means that the box and its pair have been discoverd 
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
	 * Gets the number (index) of the box whithin the set.
	 * @return an integer containing thie index of the box
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
	 * Gets the path of the image shown when the box is visible.
	 * @return
	 */
	public String getImage() {
		return image;
	}
	
	/**
	 * Gets the path of the image shown when the box is hidden.
	 * @return
	 */
	public String getHiddenImage() {
		return hiddenImage;
	}
	
	/**
	 * Gets the value of the box
	 * @return an integer with the content of the box
	 */
	public int getValue() {
		return value;
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
