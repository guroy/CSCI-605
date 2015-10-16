package exercise.n1;

/**
* Implement a parallel version of the sieve of Eratosthenes algorithm.
*
*
* @version   $Id: SieveOfEratosthenesThread.java,v 1.0 2015/10/15 $
* @author    Gurvan Lecuyer
* @author    Guillaume Roy
*
*
*/


public class SieveOfEratosthenesThread extends Thread {
	private int index;
	private int MAX;
	private final boolean numbers[];
	
	public SieveOfEratosthenesThread(int index, int MAX, boolean numbers[]) {
		this.index = index;
		this.MAX = MAX;
		this.numbers = new boolean[MAX];
	}
	
	public void run() {
		int counter = 2;
		while ( index * counter < MAX )	{		// this is the part for the parallel part
			numbers[index * counter] = false;	// this is the part for the parallel part
			counter++;				// this is the part for the parallel part
		}
	}

}
