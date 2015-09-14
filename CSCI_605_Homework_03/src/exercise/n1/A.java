package exercise.n1;

/**
 * Explain the A class
 *
 *
 * @version   $Id: A.java,v 1.0 2015/09/01 $
 * @author    Gurvan Lecuyer
 * @author    Guillaume Roy
 *
 */
public class A 
{

	int aInt = 1;		

	A() {
		aInt = 11;
	}
	public int intPlusPlus() {
		return ++aInt;
	}
	public String toString() {
		return this.getClass().getName() + ": " + aInt;
	}

	public static void main(String args[]) {
		A aA = new A();
		aA.intPlusPlus();
		System.out.println(aA);
	}
}