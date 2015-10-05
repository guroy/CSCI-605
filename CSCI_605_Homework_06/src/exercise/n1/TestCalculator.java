package exercise.n1;

public class TestCalculator {

	public TestCalculator() {
		// TODO Auto-generated constructor stub
	}
	
	public static void main(String args []) {
		View myView = new View();
		myView.askUserInput();
		myView.control.processCalculation();
		myView.displayResult();
	}

}
