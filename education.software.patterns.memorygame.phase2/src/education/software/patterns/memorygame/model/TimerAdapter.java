package education.software.patterns.memorygame.model;

import java.util.Observable;
import java.util.Timer;
import java.util.TimerTask;

/**
 * This class adapts the Timer object provided by java to the interface GameCountdownTimer.
 * 
 * It also extends the Observable in order to allow observer to register to be notified 
 * when a second has passed in the timer.
 * 
 * @author Chucho
 *
 */
public class TimerAdapter extends Observable implements GameCountdownTimer {
	
	/**
	 * The java Timer that is being adapted
	 */
	private Timer timer;
	
	/**
	 * The time remaining in the clock
	 */
	private int remainingTime;
	
	/**
	 * A flag indicating if the timer is paused
	 */
	private boolean paused = false;
	
	/**
	 * A flag indicating if the timer is running
	 */
	private boolean running;
	
	/**
	 * Creates an instance of TimerAdapter
	 * @param secs the number of second for the timer
	 */
	public TimerAdapter(int secs) {
		remainingTime = secs;
		timer = new Timer();
		running = false;
	}
	
	@Override
	public void setTime(int time) {
		timer.cancel();
		remainingTime = time;
	}

	@Override
	public void start() {
		paused = false;
		running = true;
		timer.schedule(new TimerTask(){

			@Override
			public void run() {
				if(!paused) {
					remainingTime--;
					if(remainingTime == 0) {
						cancel();
					}
					setChanged();
					notifyObservers(remainingTime);
				}
				
			}
			
		},1000,1000);

	}

	@Override
	public void pause() {
		paused = true;
	}

	@Override
	public void stop() {
		timer.cancel();
		running = false;
	}

	@Override
	public int getRemainingTime() {
		return remainingTime;
	}

	@Override
	public boolean isRunning() {
		return running;
	}

	@Override
	public void resume() {
		paused = false;
	}

}
