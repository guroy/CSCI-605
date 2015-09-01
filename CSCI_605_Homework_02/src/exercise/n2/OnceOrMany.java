package exercise.n2;

/**
* Comment the code homework
*
*
* @version   $Id: OnceOrMany.java,v 1.0 2015/09/01 $
* @author    Gurvan Lecuyer
* @author    Guillaume Roy
*
* Revisions: 
*
* 	Initial revision
*
*/

class OnceOrMany {

	public static boolean singelton(String literal, String aNewString)    {
		return ( literal == aNewString );
	}
	
	public static void main( String args[] ) {
		String aString = "xyz";
		System.out.println("1.  xyz == aString: " +     "xyz" == aString   );
		System.out.println("2.  xyz == aString: " +   ( "xyz" == aString ) );
		
		String newString = new String("xyz");
		System.out.println("xyz == new String(xyz)\n    " + ("xyz" == newString) );
		
		System.out.println("1: " + singelton("xyz", "xyz"));
		System.out.println("2: " + singelton("xyz", new String("xyz") ));
		System.out.println("3: " + singelton("xyz", "xy" + "z"));
		System.out.println("4: " + singelton("x" + "y" + "z", "xyz"));
		System.out.println("5: " + singelton("x" + "y" + new String("z"), "xyz"));
		System.out.println("6: " + singelton("x" + ( "y" + "z"), "xyz"));
		System.out.println("7: " + singelton('x' + ( "y" + "z"), "xyz"));
	}
}
