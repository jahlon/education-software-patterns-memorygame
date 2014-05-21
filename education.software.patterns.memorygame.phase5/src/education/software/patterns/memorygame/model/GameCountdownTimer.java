package education.software.patterns.memorygame.model;

/**
 * This interface provides the methods that the game need regarding time management
 * @author Chucho
 *
 */
public interface GameCountdownTimer {
	
	/**
	 * Sets the time the countdown starts from
	 * @param time integer with the number of seconds for the countdown
	 */
	public void setTime(int time);
	
	/**
	 * Start the count down
	 */
	public void start();
	
	/**
	 * Pauses the timer
	 */
	public void pause();
	
	/**
	 * Resumes the count down after the timer has been paused
	 */
	public void resume();
	
	/**
	 * Stops the timer
	 */
	public void stop();
	
	/**
	 * Gets the remaining time in the timer
	 * @return an integer with the remaining time
	 */
	public int getRemainingTime();
	
	/**
	 * Check if the timer is running
	 * @return true if the rimer is running, false otherwise
	 */
	public boolean isRunning();
	
}
