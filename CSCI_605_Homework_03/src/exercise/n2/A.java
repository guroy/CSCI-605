package exercise.n2;

/**
 * Homework 3 exercise 2
 *
 *
 * @version   $Id: A.java,v 1.0 2015/09/13 $
 * @author    Gurvan Lecuyer
 * @author    Guillaume Roy
 *
 */

public class A {

	int aInt = 1;

	A() {
		aInt = 11;
	}
	public int intPlusPlus()      {
		return ++aInt;
	}
	public String toString()      {
		return this.getClass().getName() + ": " + aInt;
	}

	public static void main(String args[]) {
		A aA = new A();
		aA.intPlusPlus();
		System.out.println(aA);
	}
}
