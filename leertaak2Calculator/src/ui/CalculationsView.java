package ui;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * 
 * @author Jasper Lankhorst
 * @author Wouter Nijenhuis
 * @version 1.0
 *
 */
@SuppressWarnings("serial")
public class CalculationsView extends JPanel implements ActionListener
{
	private JLabel lblTotalCalculations = new JLabel("Total Calculations: ");
	private JLabel totalCalculations = new JLabel("0");;
	
	public CalculationsView()
	{
		FlowLayout layout = new FlowLayout();

		setLayout(layout);

		add(lblTotalCalculations);
		add(totalCalculations);
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		CalculatorModel d = (CalculatorModel) e.getSource();
		String calculations = d.getTotalCalculations() + "";
		this.totalCalculations.setText(calculations);
	}
}
