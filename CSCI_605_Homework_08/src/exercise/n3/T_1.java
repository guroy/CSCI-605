package exercise.n3;

/**
* What are all the possible outputs of T_1.java and T_2.java?
*
*
* @version   $Id: T_1.java,v 1.0 2015/10/15 $
* @author    Gurvan Lecuyer
* @author    Guillaume Roy
*
*
*/

public class T_1 extends Thread	{
	static int x = 1;
	String info = "";

	public T_1 (String info) {
		this.info = info;
		x++;
	}

	public void run () {
		// break 0	**used to explain when the current thread change
		x++;
		// break 1	**used to explain when the current thread change
		String output = x + " " + info + ": " + x;
		// break 2	**used to explain when the current thread change
		System.out.println(output);
	}

	public static void main (String args []) {
		new T_1("a").start();
		new T_1("b").start();
		
		// Possible cases : (with examples)
		/*
		 * 4 a: 4	Create thread a and b (x equals 3). Run a first, until line break 2. Gives hand
		 * 5 b: 5	to thread b until break 1. Gives hand back to a. Displays a and ends. Displays b
		 * 			and ends.
		 * 
		 * 4 a: 5	Create thread a and b (x equals 3). Run a first, until reading info value. Gives hand
		 * 5 b: 5	to thread b until break 2. Gives hand back to a until the end. Finally b until the end.
		 * 
		 * 5 a: 5	Create thread a and b (x equals 3). Run a first, until line break 1. Gives hand
		 * 5 b: 5	to thread b until break 1. Gives hand back to a. Displays a and ends. Display b
		 * 			and ends.
		 * 
		 * 5 a: 5	Create thread a and b (x equals 3). Run a first, until line break 0. Gives hand
		 * 4 b: 4	to thread b. Run b until break 2. Gives hand back to a. Displays a and ends.
		 * 			Gives hand back to b. increment x. Displays b and ends.
		 * 
		 * 5 b: 5	Create thread a and b (x equals 3). Run a first, until reading info value. Gives hand
		 * 4 a: 5	to thread b until break 1. Hand back to a until break 2. Hand to b until the end.
		 * 			Hand to a until the end.
		 * 
		 * 5 b: 5	Create thread a and b (x equals 3). Run a first, until line break 2. Gives hand
		 * 5 a: 5	to thread b. Displays b and ends. Gives hand back to a. Displays a and ends.
		 * 
		 * 5 b: 5	Create thread a and b (x equals 3). Run a first, until line break 2. Gives hand
		 * 4 a: 4	to thread b. Displays b and ends. Gives hand back to a. Displays a and ends.
		 * 
		 * 4 b: 4	Create thread a and b (x equals 3). Run a first, until line break 0. Gives hand
		 * 5 a: 5	to thread b. Run b until the end. Gives hand back to a. Displays a and ends.
		 */
	}	
}
