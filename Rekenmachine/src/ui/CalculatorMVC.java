package ui;

import java.awt.BorderLayout;

import javax.swing.JApplet;

@SuppressWarnings("serial")
public class CalculatorMVC extends JApplet
{
	private CalculatorModel model;             // Model
	private CalculatorController controller;   // Controller
	private CalculatorView view1;
	private CalculationsView view2;
	
	/**
	 * @param args
	 *            the command line arguments
	 */
	public void init()
	{
		resize(251,290); // 238 without calculationsview
		
		new CalculatorMVC();

		// Maak het model
		model = new CalculatorModel();
        
        // Maak de controller en geef hem het model
		controller = new CalculatorController(model);
        getContentPane().add(controller,BorderLayout.CENTER);

        // Maak de views
        view1 = new CalculatorView();
        getContentPane().add(view1,BorderLayout.NORTH);
        
        view2 = new CalculationsView();
        getContentPane().add(view2,BorderLayout.SOUTH);
        
        // Registreer de views bij het model
        model.addActionListener(view1);
        model.addActionListener(view2);
	}
}
