package education.software.patterns.memorygame.ui;

import java.util.Observable;
import java.util.Observer;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.wb.swt.SWTResourceManager;

import swing2swt.layout.FlowLayout;

/**
 * This class represents the panel in which information about the score and the time is shown.
 * 
 * It implements the Observer interface so it can be registered with the timer in order to be notified.
 * 
 * @author Chucho
 *
 */
public class InfoPanel extends Composite implements Observer {
	/**
	 * Label that shows the time
	 */
	private Label lblTime;
	
	/**
	 * Label that shows the score
	 */
	private Label lblGameScore;

	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	public InfoPanel(Composite parent, int style) {
		super(parent, SWT.BORDER);
		setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_BACKGROUND));
		setFont(SWTResourceManager.getFont("Segoe UI", 25, SWT.BOLD));
		GridLayout gridLayout = new GridLayout(2, true);
		gridLayout.horizontalSpacing = 15;
		setLayout(gridLayout);
		
		Composite composite = new Composite(this, SWT.NONE);
		composite.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		composite.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Label lblRemainingTime = new Label(composite, SWT.NONE);
		lblRemainingTime.setFont(SWTResourceManager.getFont("Segoe UI", 25, SWT.BOLD));
		lblRemainingTime.setText("Time:");
		
		lblTime = new Label(composite, SWT.NONE);
		lblTime.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
		lblTime.setFont(SWTResourceManager.getFont("Segoe UI", 25, SWT.BOLD));
		lblTime.setText("0");
		
		Composite composite_1 = new Composite(this, SWT.NONE);
		composite_1.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		composite_1.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Label lblScore = new Label(composite_1, SWT.NONE);
		lblScore.setFont(SWTResourceManager.getFont("Segoe UI", 25, SWT.BOLD));
		lblScore.setText("Score:");
		
		lblGameScore = new Label(composite_1, SWT.NONE);
		lblGameScore.setForeground(SWTResourceManager.getColor(SWT.COLOR_DARK_GREEN));
		lblGameScore.setFont(SWTResourceManager.getFont("Segoe UI", 25, SWT.BOLD));
		lblGameScore.setText("0");

	}

	/**
	 * Updates the score label
	 * @param newScore integer containing the new score
	 */
	public void updateScore(int newScore) {
		lblGameScore.setText(Integer.toString(newScore));
		lblGameScore.pack();
	}
	
	/**
	 * Updates the remaining time label
	 * @param time int with the remaining time
	 */
	public void updateTime(final int time) {
		if(!isDisposed()) {
			getDisplay().syncExec(new Runnable() {
	
				@Override
				public void run() {
					lblTime.setText(Integer.toString(time));	
					lblTime.pack();
				}
			});
		}
	}
	
	@Override
	public void update(Observable observable, Object arg) {
		int time = (int)arg;
		updateTime(time);
	}
	
	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}
}
