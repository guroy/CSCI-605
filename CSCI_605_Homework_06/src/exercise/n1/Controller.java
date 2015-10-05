package exercise.n1;

/**
* Re-design and re-implement HW 2.1 using the the MVC paradigm
*
*
* @version   $Id: Controller.java,v 1.0 2015/09/29 $
* @author    Gurvan Lecuyer
* @author    Guillaume Roy
*
* Revisions: 
*
* 	Initial revision
*
*/

public class Controller {
	CalculatorModel calculator;

	boolean updatedModel;
	boolean updatedView;

	public Controller() {
		calculator = new CalculatorModel();
		updatedModel = false;
		updatedView = false;
	}
	
	public void processCalculation() {
		if (updatedModel) {
			calculator.computeExpression();
			updatedModel = false;
			updatedView = true;
		}
	}

}
