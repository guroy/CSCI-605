package exercise.n1;
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
	String aString = aStackInterfaceOld.pop();
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
