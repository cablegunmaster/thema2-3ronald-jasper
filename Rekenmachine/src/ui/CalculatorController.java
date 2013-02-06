package ui;

import java.awt.KeyEventDispatcher;
import java.awt.KeyboardFocusManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.Stack;

import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.LayoutStyle;

/**
 * 
 * @author Ronald Kok
 * @author Jasper Lankhorst
 * @version 1.0
 * 
 */
@SuppressWarnings("serial")
public class CalculatorController extends JPanel implements ActionListener, KeyEventDispatcher
{
	private CalculatorModel cm;

	// Variables declaration - do not modify
	private JComboBox baseBox = new JComboBox(new DefaultComboBoxModel(new String[]
	{ "Dec", "Bin", "Hex", "Oct" }));
	private JComboBox formatBox = new JComboBox(new DefaultComboBoxModel(new String[]
	{ "Fixed Point", "Rational", "Floating Point" }));
	// Clear/backspace
	private JButton ceButton = new JButton("C/E");
	private JButton backspaceButton = new JButton("<--");
	// Result
	private JButton isButton = new JButton("=");
	// Operants
	private JButton divideButton = new JButton("/");
	private JButton multiplyButton = new JButton("*");
	private JButton minusButton = new JButton("-");
	private JButton plusButton = new JButton("+");
	// Numbers
	private JButton commaButton = new JButton(",");
	private JButton zeroButton = new JButton("0");
	private JButton oneButton = new JButton("1");
	private JButton twoButton = new JButton("2");
	private JButton threeButton = new JButton("3");
	private JButton fourButton = new JButton("4");
	private JButton fiveButton = new JButton("5");
	private JButton sixButton = new JButton("6");
	private JButton sevenButton = new JButton("7");
	private JButton eightButton = new JButton("8");
	private JButton nineButton = new JButton("9");

	private Stack<String> operator;
	private Stack<String> numbers;

	// End of variables declaration

	public CalculatorController(CalculatorModel cm)
	{
		this.cm = cm;

		// Key Listeners
		KeyboardFocusManager manager = KeyboardFocusManager.getCurrentKeyboardFocusManager();
		manager.addKeyEventDispatcher(this);

		// Action Listeners
		ceButton.addActionListener(this);
		backspaceButton.addActionListener(this);

		isButton.addActionListener(this);

		plusButton.addActionListener(this);
		minusButton.addActionListener(this);
		divideButton.addActionListener(this);
		multiplyButton.addActionListener(this);

		commaButton.addActionListener(this);
		zeroButton.addActionListener(this);
		oneButton.addActionListener(this);
		twoButton.addActionListener(this);
		threeButton.addActionListener(this);
		fourButton.addActionListener(this);
		fiveButton.addActionListener(this);
		sixButton.addActionListener(this);
		sevenButton.addActionListener(this);
		eightButton.addActionListener(this);
		nineButton.addActionListener(this);

		// Combox Action Listeners
		baseBox.addActionListener(this);
		formatBox.addActionListener(this);

		GroupLayout layout = new GroupLayout(this);
		layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(
				layout.createSequentialGroup()
						.addContainerGap()
						.addGroup(
								layout.createParallelGroup(GroupLayout.Alignment.TRAILING, false)
										// .addComponent(calculatorResult,
										// GroupLayout.Alignment.LEADING,
										// GroupLayout.DEFAULT_SIZE,
										// GroupLayout.DEFAULT_SIZE,
										// Short.MAX_VALUE)
										.addGroup(
												GroupLayout.Alignment.LEADING,
												layout.createSequentialGroup().addComponent(formatBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
														.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE).addComponent(baseBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
										.addGroup(
												GroupLayout.Alignment.LEADING,
												layout.createSequentialGroup()
														.addGroup(
																layout.createParallelGroup(GroupLayout.Alignment.LEADING)
																		.addGroup(
																				layout.createSequentialGroup()
																						.addGroup(
																								layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
																										.addGroup(
																												layout.createSequentialGroup().addComponent(oneButton).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(twoButton)
																														.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(threeButton))
																										.addGroup(
																												layout.createSequentialGroup().addComponent(sevenButton).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(eightButton)
																														.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE).addComponent(nineButton))
																										.addGroup(
																												layout.createSequentialGroup().addComponent(fourButton).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(fiveButton)
																														.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(sixButton)))
																						.addGap(6, 6, 6)
																						.addGroup(
																								layout.createParallelGroup(GroupLayout.Alignment.TRAILING, false)
																										.addComponent(divideButton, GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
																										.addComponent(minusButton, GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
																										.addComponent(multiplyButton, GroupLayout.Alignment.LEADING)))
																		.addGroup(
																				layout.createSequentialGroup().addComponent(zeroButton, GroupLayout.PREFERRED_SIZE, 63, GroupLayout.PREFERRED_SIZE)
																						.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE).addComponent(commaButton, GroupLayout.PREFERRED_SIZE, 60, GroupLayout.PREFERRED_SIZE)
																						.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(plusButton)))
														.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
														.addGroup(
																layout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(backspaceButton, GroupLayout.DEFAULT_SIZE, 49, Short.MAX_VALUE).addComponent(ceButton, GroupLayout.DEFAULT_SIZE, 49, Short.MAX_VALUE)
																		.addComponent(isButton, GroupLayout.DEFAULT_SIZE, 49, Short.MAX_VALUE)))).addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
		layout.setVerticalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(
				layout.createSequentialGroup()
						.addGap(18, 18, 18)
						.addGroup(
								layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(formatBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
										.addComponent(baseBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGap(18, 18, 18)
						.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(multiplyButton).addComponent(sevenButton).addComponent(eightButton).addComponent(nineButton).addComponent(ceButton))
						.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
						.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(divideButton).addComponent(fourButton).addComponent(fiveButton).addComponent(sixButton).addComponent(backspaceButton))
						.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
						.addGroup(
								layout.createParallelGroup(GroupLayout.Alignment.LEADING)
										.addGroup(
												GroupLayout.Alignment.TRAILING,
												layout.createSequentialGroup()
														.addGroup(
																layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(oneButton).addComponent(twoButton).addComponent(threeButton)
																		.addComponent(minusButton, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
														.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(plusButton).addComponent(zeroButton).addComponent(commaButton)))
										.addComponent(isButton, GroupLayout.Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 52, GroupLayout.PREFERRED_SIZE)).addContainerGap()));
		setLayout(layout);

		add(baseBox);
		add(formatBox);

		add(isButton);

		add(plusButton);
		add(minusButton);
		add(divideButton);
		add(multiplyButton);

		add(ceButton);
		add(backspaceButton);

		add(commaButton);
		add(zeroButton);
		add(oneButton);
		add(twoButton);
		add(threeButton);
		add(fourButton);
		add(fiveButton);
		add(sixButton);
		add(sevenButton);
		add(eightButton);
		add(nineButton);

	}

	public void actionPerformed(ActionEvent e)
	{
		Object source = e.getSource();
		if (source == ceButton)
		{
			cm.clearCalculatorResult();
		}
		else if (source == backspaceButton)
		{
			cm.removeLastChar();
		}
		else if (source == isButton)
		{
			cm.calculate();
		}
		else if (source == plusButton)
		{
			useOperant("+");
		}
		else if (source == minusButton)
		{
			useOperant("-");
		}
		else if (source == divideButton)
		{
			useOperant("/");
		}
		else if (source == multiplyButton)
		{
			useOperant("*");
		}
		else if (source == commaButton)
		{
			cm.addComma();
		}
		else if (source == zeroButton)
		{
			changeNumber("0");
		}
		else if (source == oneButton)
		{
			changeNumber("1");
		}
		else if (source == twoButton)
		{
			changeNumber("2");
		}
		else if (source == threeButton)
		{
			changeNumber("3");
		}
		else if (source == fourButton)
		{
			changeNumber("4");
		}
		else if (source == fiveButton)
		{
			changeNumber("5");
		}
		else if (source == sixButton)
		{
			changeNumber("6");
		}
		else if (source == sevenButton)
		{
			changeNumber("7");
		}
		else if (source == eightButton)
		{
			changeNumber("8");
		}
		else if (source == nineButton)
		{
			changeNumber("9");
		}
		else if (source == baseBox)
		{
			cm.useBase(baseBox.getSelectedItem().toString());
		}
		else if (source == formatBox)
		{
			cm.useFormat(formatBox.getSelectedItem().toString());
		}
	}

	private void changeNumber(String number)
	{
		cm.changeNumber(number);
	}

	private void useOperant(String operant)
	{
		cm.useOperant(operant);
	}

	@Override
	public boolean dispatchKeyEvent(KeyEvent e)
	{
		switch (e.getID())
		{
		case KeyEvent.KEY_PRESSED:
		{
			char c = e.getKeyChar();
			if (c == '(')
			{
				cm.addParenthesis(true);
			}
			else if (c == ')')
			{
				cm.addParenthesis(false);
			}
			else if (c == '+' || c == '-' || c == '*' || c == '/')
			{
				cm.useOperant(c + "");
			}
			else if (c == '\b')
			{
				cm.removeLastChar();
			}
			else if (c == '\n')
			{
				cm.calculate();
			}
			else if (c == '.'|| c == ',')
			{
				cm.addComma();
			}

			try
			{
				int val = Integer.parseInt(c + "");
				cm.changeNumber(val + "");
			}
			catch (NumberFormatException nfe)
			{

			}
			break;
		}
		}
		return false;
	}
}
