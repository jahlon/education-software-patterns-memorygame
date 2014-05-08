package education.software.patterns.memorygame.ui;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.wb.swt.SWTResourceManager;

import swing2swt.layout.FlowLayout;

/**
 * This class represents the panel in which information about the score is shown
 * @author Chucho
 *
 */
public class InfoPanel extends Composite {
	/**
	 * Label to show the score
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
		GridLayout gridLayout = new GridLayout(1, true);
		gridLayout.horizontalSpacing = 15;
		setLayout(gridLayout);
		
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
	}
	
	
	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}
}
