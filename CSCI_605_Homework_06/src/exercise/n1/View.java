package exercise.n1;

import java.util.Scanner;

/**
* Re-design and re-implement HW 2.1 using the the MVC paradigm
*
*
* @version   $Id: View.java,v 1.0 2015/09/29 $
* @author    Gurvan Lecuyer
* @author    Guillaume Roy
*
* Revisions: 
*
* 	Initial revision
*
*/

public class View {
	Controller control;

	public View() {
		control = new Controller();
	}
	
	public void askUserInput() {
		System.out.println("Please, enter calculus:");
		Scanner reader = new Scanner(System.in);
		String myInput = "";
		myInput = reader.nextLine();
		
		System.out.println(myInput);
		control.updatedModel = true;
		reader.close();
	}
	
	public void displayResult() {
		if(control.updatedView)
		System.out.print("Result is: ");
		System.out.println(control.calculator.result);
		control.updatedView = false;
	}

}
