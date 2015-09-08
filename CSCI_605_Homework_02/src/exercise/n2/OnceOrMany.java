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

class OnceOrMany 
{

	public static boolean singelton(String literal, String aNewString)    
	{
		return ( literal == aNewString );
	}
	
	public static void main( String args[] ) 
	{
		String aString = "xyz";
		
		// 1.
		//this line is false because of the priority of the execution
		//the line bellow is equivalent to:
		//System.out.println("1.  xyz == aString: xyz" == aString   );
		//this is obviously false
		System.out.println("1.  xyz == aString: " +     "xyz" == aString   );
		
		//2.
		//this line give a high priority  to the expression inside the parenthesis which return true
		//the true is append to the previous string to return the final expression
		System.out.println("2.  xyz == aString: " +   ( "xyz" == aString ) );
		
		//newString. 
		//for this question, newString is created in the actual context of the method
		//xyz is a reference in the string constant pool
		//the 2 object are different so the == operator return false, with the method equals of String
		//the result would be true
		String newString = new String("xyz");
		newString += "abc";
		System.out.println("xyz == new String(xyz)\n    " + ("xyz" == newString) );
		
		//1.
		//comparison between two string located in the string constant pool, the string are the same
		//return true 
		System.out.println("1: " + singelton("xyz", "xyz"));
		//2.
		//Comparison between a new object in the context and a string in the string constant pool
		//same case than the 3rd question, return false
		System.out.println("2: " + singelton("xyz", new String("xyz") ));
		//3.
		//comparison of a string in the string constant pool and a concatenation of two string
		//in the string constant pool, the concatenation is equivalent of the left expression
		//return true
		System.out.println("3: " + singelton("xyz", "xy" + "z"));
		//4.
		//same case than the previous one so the line return true
		System.out.println("4: " + singelton("x" + "y" + "z", "xyz"));
		//5.
		//In this case, there is a concatenation of two constant string from the pool with an object from
		//the context of the method, this concatenation is store in the context and not in the string constant pool
		//this object is compared with a string in the string constant pool, so return false
		System.out.println("5: " + singelton("x" + "y" + new String("z"), "xyz"));
		//6.
		//the priority due to the parenthesis doesn't affect the string that results, both expression are
		// in the string constant pool, return true
		System.out.println("6: " + singelton("x" + ( "y" + "z"), "xyz"));
		//7.
		// the expression is composed of a char appended to a string that give a string in the string  constant pool
		// compared with the same string located in the string constant pool so return true
		System.out.println("7: " + singelton('x' + ( "y" + "z"), "xyz"));
	}
}
