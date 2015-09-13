package exercise.n2;

/**
 * Homework 3 exercise 2
 *
 *
 * @version   $Id: AAA.java,v 1.0 2015/09/13 $
 * @author    Gurvan Lecuyer
 * @author    Guillaume Roy
 *
 */

public class AAA extends AA {

	int aInt = 1;

	AAA() {
		aInt = 11;
	}
	public int intPlusPlus()      {
		return ++aInt;
	}

	public static void main(String args[]) {
		AAA aAAA = new AAA();
		AA   aAA = (AA)aAAA;
		A     aA = (A)aAA;
		aAAA.intPlusPlus();
		aAA.intPlusPlus();
		aA.intPlusPlus();
		System.out.println("aA:        "  + aA);
		System.out.println("aAA:       " + aAA);
		System.out.println("aAAA:      " + aAA);
		System.out.println("aAAA:.aInt " + aAAA.aInt);
	}
}
