package exercise.n1;

/**
 * Explain the AAA class
 *
 *
 * @version   $Id: AAA.java,v 1.0 2015/09/01 $
 * @author    Gurvan Lecuyer
 * @author    Guillaume Roy
 *
 */
public class AAA extends AA {

	int aInt = 1;		

	AAA() {
		aInt = 11;
	}
	public int intPlusPlus()	{
		return ++aInt;
	}

	public static void main(String args[]) {
		//Creation of a new object of class AAA
		AAA aAAA = new AAA();
		//Initialization of the object of class AA  with an object of type AAA with a cast
		AA   aAA = (AA)aAAA;
		//initialization of an object A with an object of type AA by cast aA and aAA point on the same object
		A     aA = (A)aAA;
		//incrementation of aAAA using the method of the class AAA
		aAAA.intPlusPlus();
		//incrementation of aAAA using the method of the class AAA
		aAA.intPlusPlus();
		//incrementation of aAAA using the method of the class AAA
		aA.intPlusPlus();

		System.out.println("aA:        "  + aA);
		System.out.println("aAA:       " + aAA);
		System.out.println("aAAA:      " + aAA);
		System.out.println("aAAA:.aInt " + aAAA.aInt);
	}
}
