package test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import multiformat.Calculator;
import multiformat.FormatException;
import multiformat.OctaanBase;
import multiformat.Rational;

import org.junit.Before;
import org.junit.Test;

import ui.CalculatorModel;

/**
 * JUnit Testcase to test Rational. Note that this class uses 'annotations' (the
 * 
 * @...). This is a Java 1.5 feature.
 * 
 * @author gebruiker
 * 
 */
public class TestRational
{
	Rational r;
	Calculator calc = new Calculator();

	@Before
	public void setUp()
	{
		r = new Rational();
	}

	@Test
	public void testSimplify()
	{
		r.setNumerator(25);
		r.setDenominator(5);
		r.simplify();

		assertTrue(5.0 == r.getNumerator());
		assertTrue(1.0 == r.getDenominator());

		r.setNumerator(10);
		r.setDenominator(0.5);
		r.simplify();

		assertTrue(10.0 == r.getNumerator());
		assertTrue(0.5 == r.getDenominator());
	}

	@Test
	public void testCanonical()
	{
		r.setNumerator(12.5);
		r.setDenominator(1.0);
		r.canonical();

		assertTrue(125.0 == r.getNumerator());
		assertTrue(10.0 == r.getDenominator());

		r.setNumerator(12.5);
		r.setDenominator(0.01);
		r.canonical();

		assertTrue(125.0 == r.getNumerator());
		assertTrue(0.1 == r.getDenominator());
	}

	@Test
	public void testCanonicalAndSimplify()
	{
		r.setNumerator(12.5);
		r.setDenominator(1.0);
		r.canonical();
		r.simplify();

		assertTrue(25.0 == r.getNumerator());
		assertTrue(2.0 == r.getDenominator());
	}

	@Test
	public void testDivideBy()
	{
		r.setNumerator(0.0);
		r.setDenominator(1.0);
		r.canonical();
		r.simplify();

		Rational r2 = new Rational();
		r2.setNumerator(12.5);
		r2.setDenominator(1.0);
		r2.canonical();
		r2.simplify();
		Rational result = r.div(r2);

		assertFalse(result.getNumerator() == 1.0 && result.getDenominator() == 1.0);
	}

	/**
	 * In deze test word er gekeken of het octaan systeem werkt of het hopeloos
	 * verknalt is.
	 * 
	 */
	@Test
	public void testOctaan()
	{
		calc.setBase(new OctaanBase());
		try
		{
			calc.addOperand("5.0");
			calc.addOperand("5.0");
			calc.add();
		}
		catch (FormatException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// Omdat in het octaan stelsel er 8 getallen zijn: 0-7
		// Is 8 dus 10. Tel je hier nog twee bij op zit je op 12
		assertTrue(calc.getLastOperantWithoutRemoval().equals("12.0"));
	}

	@Test
	public void Testoptellen()
	{
		String uitrekenen = "5 + 8 + 2";
		CalculatorModel cm = new CalculatorModel();
		cm.setCalculatorResult(uitrekenen);
		cm.calculate();
		System.out.println(cm.getCalculatorResult());
		assertTrue(cm.getCalculatorResult().equals("15"));
	}
}
