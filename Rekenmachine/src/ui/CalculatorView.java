package ui;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;

@SuppressWarnings("serial")
/**
 * Show the Calculator graphics.
 * @author Ronald Kok
 * @author Jasper Lankhorst
 * @version 1.0
 */
public class CalculatorView extends JPanel implements ActionListener
{
	private JTextArea calculatorResult;

	public CalculatorView()
	{
		calculatorResult = new JTextArea();

		calculatorResult.setAlignmentX(SwingConstants.RIGHT);
		calculatorResult.setAlignmentY(SwingConstants.RIGHT);
		calculatorResult.setColumns(21);
		calculatorResult.setSize(250, 20);
		calculatorResult.setEditable(false);
		calculatorResult.setRows(4);
		calculatorResult.setText("0");
		calculatorResult.setLineWrap(true);

		FlowLayout layout = new FlowLayout();

		setLayout(layout);

		add(calculatorResult);
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		CalculatorModel d = (CalculatorModel) e.getSource();
		String calculatorResult = d.getCalculatorResult();
		this.calculatorResult.setText(calculatorResult);
	}
}
