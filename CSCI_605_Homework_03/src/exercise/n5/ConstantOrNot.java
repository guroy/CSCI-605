package exercise.n5;

import java.util.Vector;	// what does this line do?
							// it imports the code necessary to create and use Vector objects
							// from the java.util.Vector class


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
		// as aInt is defined as a final variable, it is supposed to have a constant value. Thus it cannot be modified.
		// aString = aString + "abc"; why would this fail?
		// aString is a literal String. It has a primitive type so it is supposed to have a constant value. Thus it cannot be modified.
		aVector.add("abc");	// why does this work?
							// aVector defines a final object. It is a reference to the object. As it is final, only the reference
							// can't be modified, but we can still change its attributes.
	}

	public static void main( String args[] ) {
		new ConstantOrNot().doTheJob();

	}
}
