package exercise.n1;

import java.util.Arrays;
import java.util.Scanner;
import java.util.Vector;

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
		String myInput = reader.nextLine();
		control.calculator.myArguments.clear();
		for(int i=0; i < myInput.length(); i++)
		{
			//System.out.println("element of input at " + i + " " +myInput.charAt(i));
			String buf = "";
			if(myInput.charAt(i) != ' ')
			{
				buf += myInput.charAt(i);
				control.calculator.myArguments.add(buf);
			}
		}
		System.out.println(control.calculator.myArguments);
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
