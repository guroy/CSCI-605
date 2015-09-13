package exercise.n5;

import java.util.Vector;

/**
 * Homework 3 exercise 5, comment code
 *
 *
 * @version   $Id: ConstantOrNot.java,v 1.0 2015/09/13 $
 * @author    Gurvan Lecuyer
 * @author    Guillaume Roy
 *
 */

class ConstantOrNot {

	private final int aInt = 1;
	private final String aString = "abc";
	private final Vector aVector = new Vector();

	public void doTheJob() {
		// aInt = 3; why would this fail?
		// aString = aString + "abc"; why would this fail?
		aVector.add("abc");             // why does this work?
	}

	public static void main( String args[] ) {
		new ConstantOrNot().doTheJob();

	}
}
