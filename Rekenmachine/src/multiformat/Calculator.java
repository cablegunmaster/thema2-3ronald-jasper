/*
 * (C) Copyright 2005 Davide Brugali, Marco Torchiano
 *
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA
 * 02111-1307  USA
 */
package multiformat;

import java.util.EmptyStackException;
import java.util.Stack;

/**
 * The multiformat calculator
 */
public class Calculator
{
	private Stack<Rational> rationalStack = new Stack<Rational>();

	// The current format of the calculator
	private Format format = new FixedPointFormat();

	// The current numberbase of the calculator
	private Base base = new DecimalBase();

	public void addOperand(String newOperand) throws FormatException
	{
		System.out.println("2. Calculator basenaam: " +base.getName() + " Operand: " + newOperand + "Format: " + format );
		rationalStack.add(format.parse(newOperand, base));
	}

	public void add()
	{
		try
		{
			Rational op0 = rationalStack.pop();
			Rational op1 = rationalStack.pop();
			Rational op = op0.plus(op1);
			addOperand(operandToString(op));
		}
		catch(EmptyStackException e)
		{
			System.err.print("Stack is leeg..");			
		}
		catch (FormatException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void subtract()
	{
		try
		{
			Rational op0 = rationalStack.pop();
			Rational op1 = rationalStack.pop();
			Rational op = op0.minus(op1);
			addOperand(operandToString(op));
		}
		catch(EmptyStackException e)
		{
			System.err.print("Stack is leeg..");			
		}
		catch (FormatException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void multiply()
	{
		try
		{
			Rational op0 = rationalStack.pop();
			Rational op1 = rationalStack.pop();
			Rational op = op0.mul(op1);
			addOperand(operandToString(op));
		}
		catch(EmptyStackException e)
		{
			System.err.print("Stack is leeg..");			
		}
		catch (FormatException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void divide()
	{
		try
		{
			Rational op0 = rationalStack.pop();
			Rational op1 = rationalStack.pop();
			Rational op = op0.div(op1);
			addOperand(operandToString(op));
		}
		catch(EmptyStackException e)
		{
			System.err.print("Stack is leeg..");			
		}
		catch (FormatException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public String operandToString(Rational rational)
	{
		System.out.println("Rational: " + rational.denominator + " "+ rational.numerator + "Base: " + base.getName());
		System.out.println(this.getFormat());
		return format.toString(rational, base);
	}

	public String getLastOperant()
	{
		return operandToString(rationalStack.peek());
	}
	
	public String getLastOperantWithoutRemoval()
	{
		String value = null;
		try{
		value = operandToString(rationalStack.lastElement());
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
		return value;
	}
	
	public Stack<Rational> getStack(){
		return rationalStack;
	}

	public void setBase(Base newBase)
	{
		base = newBase;
	}

	public Base getBase()
	{
		return base;
	}

	public void setFormat(Format newFormat)
	{
		format = newFormat;
	}

	public Format getFormat()
	{
		return format;
	}
	
	public void clearStack()
	{
		rationalStack = new Stack<Rational>();
	}
}