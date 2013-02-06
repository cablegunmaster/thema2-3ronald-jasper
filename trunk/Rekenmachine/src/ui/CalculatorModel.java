package ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import multiformat.BinaryBase;
import multiformat.Calculator;
import multiformat.DecimalBase;
import multiformat.FixedPointFormat;
import multiformat.FloatingPointFormat;
import multiformat.FormatException;
import multiformat.HexBase;
import multiformat.OctaanBase;
import multiformat.RationalFormat;

public class CalculatorModel
{
	private ArrayList<ActionListener> actionListenerList = new ArrayList<ActionListener>();
	private String calculatorResult;
	private boolean parenthesisOpen = false;
	private Calculator calc = new Calculator();
	private long totalCalculations;

	public CalculatorModel()
	{
		calculatorResult = "0";
	}

	public String getCalculatorResult()
	{
		return calculatorResult;
	}

	public void setCalculatorResult(String result)
	{
		calculatorResult = result;
	}

	public void addActionListener(ActionListener l)
	{
		actionListenerList.add(l);
	}

	public void removeActionListener(ActionListener l)
	{
		if (actionListenerList.contains(l))
			actionListenerList.remove(l);
	}

	private void processEvent(ActionEvent e)
	{
		// Hieronder gebruiken we het nieuwe Java "foreach" statement.
		// Lees het als: "for each ActionListener in actionListenerList do ..."
		// Je kunt ook een for-lus of een iterator gebruiken, maar foreach is
		// het elegantste.
		for (ActionListener l : actionListenerList)
			l.actionPerformed(e);
	}

	public void addComma()
	{
		String[] tmp = calculatorResult.split(" ");

		String lastNumber = tmp[tmp.length - 1];
		if (!isNumeric(lastNumber))
			return;

		String lastChar = calculatorResult.substring(calculatorResult.length() - 1);
		if (!lastChar.equals(" ") && !lastChar.equals("."))
		{
			calculatorResult = calculatorResult + ".";
		}
		processEvent(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, null));
	}

	public void useOperant(String operant)
	{
		String lastChar = calculatorResult.substring(calculatorResult.length() - 1);
		// String getOperant = getOperantIfExist(calculatorResult);
		// if (getOperant == null)
		if (!lastChar.equals(" "))
		{
			calculatorResult = calculatorResult + " " + operant + " ";
		}
		processEvent(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, null));
	}

	public void changeNumber(String number)
	{
		if (calculatorResult.length() <= 95)
		{
			calculatorResult = calculatorResult.equals("0") ? number : calculatorResult + number;
		}
		else
			calculatorResult = "Stackoverflow";
		processEvent(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, null));
	}

	public void addParenthesis(boolean left)
	{
		String lastChar = calculatorResult.substring(calculatorResult.length() - 1);
		if (left && !parenthesisOpen)
		{
			if (isNumeric(lastChar))
			{
				calculatorResult = "( " + calculatorResult;
			}
			else
			{
				calculatorResult += "( ";
			}
			parenthesisOpen = true;
		}
		else if (!left && parenthesisOpen && !lastChar.equals(" "))
		{
			calculatorResult += " )";
			parenthesisOpen = false;
		}
		processEvent(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, null));
	}

	public void clearCalculatorResult()
	{
		calc.clearStack();
		calculatorResult = "0";
		parenthesisOpen = false;
		processEvent(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, null));
		operants = 0;
	}

	public void removeLastChar()
	{
		if (calculatorResult.length() > 0)
		{
			calculatorResult = calculatorResult.substring(0, calculatorResult.length() - 1).trim();

			if (calculatorResult == "0" || calculatorResult.length() == 0)
			{
				clearCalculatorResult();
			}
			processEvent(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, null));
		}
	}

	/**
	 * This method will calculate
	 */
	public void calculate()
	{
		if (!calculatorResult.contains("("))
		{
			// String[] tmp1 = calculatorResult.split(" [-*+/] ");
			String[] tmp1 = calculatorResult.split(" ");
			int operants = 0;

			for (int i = (tmp1.length - 1); i >= 0; i--)
			{
				String val = tmp1[i];
				if (isNumeric(val))
				{
					try
					{
						calc.addOperand(val);
					}
					catch (FormatException e)
					{
						System.out.println("Wrong operand: " + e.getMessage());
					}
				}
			}
			
			//
			for (int i = 0; i < tmp1.length; i++)
			{
				String val = tmp1[i];
				String operant = getOperantIfExist(val);
				if (operant != null)
				{
					if (operant == "*")
					{
						calc.multiply();
					}
					else if (operant == "/")
					{
						calc.divide();
					}
					else if (operant == "+")
					{
						calc.add();
					}
					else if (operant == "-")
					{
						calc.subtract();
					}
					operants++;
				}
			}
			totalCalculations++;
			calculatorResult = calc.getLastOperantWithoutRemoval();
			processEvent(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, null));
		}
		else
		{
			String[] tmp1 = calculatorResult.split("\\(.+?\\)");
			for (String val : tmp1)
			{
				System.out.println(val);
			}
		}
	}
	
	/**
	 * This method will calculate
	 */
	public void calculate2()
	{
		if (!calculatorResult.contains("("))
		{
			// String[] tmp1 = calculatorResult.split(" [-*+/] ");
			String[] tmp1 = calculatorResult.split(" ");
			int operants = 0;

			for (int i = (tmp1.length - 1); i >= 0; i--)
			{
				String val = tmp1[i];
				if (isNumeric(val))
				{
					try
					{
						calc.addOperand(val);
					}
					catch (FormatException e)
					{
						System.out.println("Wrong operand: " + e.getMessage());
					}
				}
			}
			
			//
			for (int i = 0; i < tmp1.length; i++)
			{
				String val = tmp1[i];
				String operant = getOperantIfExist(val);
				if (operant != null)
				{
					if (operant == "*")
					{
						calc.multiply();
					}
					else if (operant == "/")
					{
						calc.divide();
					}
					else if (operant == "+")
					{
						calc.add();
					}
					else if (operant == "-")
					{
						calc.subtract();
					}
					operants++;
				}
			}
			totalCalculations++;
			calculatorResult = calc.getLastOperantWithoutRemoval();
			processEvent(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, null));
		}
		else
		{
			String[] tmp1 = calculatorResult.split("\\(.+?\\)");
			for (String val : tmp1)
			{
				System.out.println(val);
			}
		}
	}

	public void useBase(String base)
	{
		if (base == "Dec")
		{
			calc.setBase(new DecimalBase());
		}
		else if (base == "Hex")
		{
			calc.setBase(new HexBase());
		}
		else if (base == "Oct")
		{
			calc.setBase(new OctaanBase());
		}
		else if (base == "Bin")
		{
			calc.setBase(new BinaryBase());
		}
	}

	public void useFormat(String format)
	{
		if (format == "Floating Point")
		{
			calc.setFormat(new FloatingPointFormat());
		}
		else if (format == "Fixed Point")
		{
			calc.setFormat(new FixedPointFormat());
		}
		else if (format == "Rational")
		{
			calc.setFormat(new RationalFormat());
		}
	}

	private static String[] operants = new String[]
	{ "+", "-", "*", "/" };

	private static String getOperantIfExist(String currentText)
	{
		for (String operant : operants)
		{
			if (operant.equals(currentText))
				return operant;
		}
		return null;
	}

	private static boolean isNumeric(String val)
	{
		try
		{
			Integer.parseInt(val);
		}
		catch (NumberFormatException nfe)
		{
			return false;
		}
		return true;
	}

	public long getTotalCalculations()
	{
		return totalCalculations;
	}
}
