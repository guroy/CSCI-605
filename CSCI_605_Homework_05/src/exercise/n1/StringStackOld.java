package exercise.n1;

/**
 * Homework 5.1
 *
 *
 * @version   $Id: StringStackOld.java,v 1.0 2015/09/23 $
 * @author    Gurvan Lecuyer
 * @author    Guillaume Roy
 *
 */


// this implementation implements the methods,
// but the methods are null methods;
public class StringStackOld implements StackInterfaceOld {
    
    public void push( Object item )	{	       }
    public Object pop() 		{ return null; }
    public Object peek() 		{ return "hi"; }
    public boolean isEmpty() 		{ return true; }

    public static void main(String args[])	{
	StackInterfaceOld aStackInterfaceOld = new StringStackOld();
	aStackInterfaceOld.push("hello");	// why is here no warning?
	/*
	 * The line above respect the implementation of push() method, with correct attribute types
	 */
	@SuppressWarnings("unused")
	String aString = (String) aStackInterfaceOld.pop();
	/*
	 * We can't store a standard undefined Object in a defined Object (such as a String).
	 * If we cast with a (String), the code can be compiled and aString's value will be null
	 */
    }
/*
javac StringStackOld.java			// explain this error
StringStackOld.java:11: incompatible types	// explain what a cast would do
found   : java.lang.Object			// regarding possible compiler error detection
required: java.lang.String
	String aString = aStackInterfaceOld.pop();
	                                       ^
1 error

*/

}
