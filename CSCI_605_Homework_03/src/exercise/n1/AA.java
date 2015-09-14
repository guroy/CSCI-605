package exercise.n1;

/**
 * Explain the AA class
 *
 *
 * @version   $Id: AA.java,v 1.0 2015/09/01 $
 * @author    Gurvan Lecuyer
 * @author    Guillaume Roy
 *
 */
public class AA extends A
{

	int aInt = 1;		

	AA() {
		aInt = 11;
	}
	public int intPlusPlus() {
		return ++aInt;
	}
	public String toString() {
		return this.getClass().getName() + ": " + aInt;
	}

	public static void main(String args[]) {
		//Creation of a new instance of the class AA
		AA aAA = new AA();

		//initialization of an object A with an object of type AA by cast aA and aAA point on the same object
		A   aA = (A)aAA;

		//incrementation of aAA
		aAA.intPlusPlus();
		//incrementation of aAA (aA point on aAA)
		aA.intPlusPlus();

		//display use the method display of the class AA because aA point on aAA
		System.out.println(aA);
		//same that above
		System.out.println(aAA);
		//get the value contains in aA.aInt so 11
		System.out.println("aA: " + aA.aInt);
	}
}